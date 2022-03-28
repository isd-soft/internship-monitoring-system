import {Component, Inject, OnInit} from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { AccountService } from "../../shared/service/account.service";
import { first } from "rxjs/operators";
import { Subscription } from "rxjs/internal/Subscription";
import { Candidate, Status } from "../../shared/model/candidate";
import { CandidateService } from "../../shared/service/candidate.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: "app-add-candidate",
  templateUrl: "./add-candidate.component.html",
  styleUrls: ["./add-candidate.component.css"],
})
export class AddCandidateComponent implements OnInit {
  candidateForm: FormGroup = new FormGroup({});
  candidateStatusError = false;
  validationErrors: {} | null = {};
  status = Status;
  statusOptions: { name: string; value: number }[] = [];
  subscription = new Subscription();

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private candidateService: CandidateService,
    @Inject(MAT_DIALOG_DATA) public data: { intent:"add" | "update", candidate?: Candidate },
    private dialogRef: MatDialogRef<AddCandidateComponent>
  ) {}

  ngOnInit(): void {
    this.statusOptions = this.buildStatusOptions();
    this.candidateForm = this.formBuilder.group({
      id: [""],
      email: [
        "",
        [
          Validators.required,
          Validators.email,
          Validators.pattern(/^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/),
        ],
      ],
      name: ["", [Validators.required]],
      surname: ["", [Validators.required]],
      comment: ["", [Validators.required]],
      status: ["", [Validators.required]],
      cv: ["", [Validators.required]],
      mark: ["", [Validators.required]],
      internship: ["", [Validators.required]],
    });
    if (this.data.intent === "update") {
      this.candidateForm.controls["id"].setValidators([Validators.required]);
    }

    if(this.data.candidate) {
      this.candidateForm.patchValue(this.data.candidate);
      const status = this.statusOptions.find(
        (option) => {
          const status = this.data?.candidate?.status as unknown as string;
          return option.name === status;
      })
      this.candidateForm.controls['status'].patchValue(status.value);
    }
  }

  onSubmit() {
    if (!this.candidateForm.valid) {
      return;
    }
    if (this.data.intent === "update") {
      this.candidateService
        .updateCandidateInIntership(this.candidateForm.value)
        .subscribe(() => {this.dialogRef.close('updated candidate!')})
        // .subscribe({
        //   next: () => {
        //     this.candidateStatusError = false;
        //     this.dialogRef.close('updated candidate!')
        //   },
        //   error: (error) => {
        //     console.log(error);
        //     this.candidateStatusError = true;
        //     this.validationErrors = error?.error?.message;
        //   },
        //   complete: () => {
        //     this.candidateStatusError = false;
        //     this.dialogRef.close('updated candidate!')
        //   },
        // });
    }

    if (this.data.intent === "add") {
      const objToSend = this.candidateForm.value;
      delete objToSend.id;

      this.candidateService
        .createCandidate(this.candidateForm.value)
        .subscribe(() => {
            this.dialogRef.close('updated candidate!');
          }
        // .subscribe({
        //   next: () => {
        //     this.candidateStatusError = false;
        //     this.dialogRef.close('created candidate!')
        //   },
        //   error: (error) => {
        //     console.log(error);
        //     this.candidateStatusError = true;
        //     this.validationErrors = error?.error?.message;
        //   },
        //   complete: () => {
        //     this.candidateStatusError = false;
        //     this.dialogRef.close('created candidate!')
        //   }
        //   }
        );
    }
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];

    if (file) {
      const formData = new FormData();

      formData.append("file", file);

      this.candidateService.uploadCandidatesCV(formData).subscribe((val) => {
        this.candidateForm.controls["cv"].patchValue(val.data);
      });
    }
  }

  ngOnDestroy(): void {
    //Called once, before the instance is destroyed.
    //Add 'implements OnDestroy' to the class.
    this.subscription.unsubscribe();
  }

  private buildStatusOptions(): { name: string; value: number }[] {
    let options = [];
    for (const enumMember in this.status) {
      if (parseInt(enumMember, 10) >= 0) {
        options.push({
          name: this.status[enumMember],
          value: parseInt(enumMember, 10),
        });
      }
    }
    return options;
  }
}
