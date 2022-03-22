import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AccountService} from "../../shared/service/account.service";
import {first} from "rxjs/operators";
import {Subscription} from "rxjs/internal/Subscription";
import {Candidate, Status} from "../../shared/model/candidate";
import {CandidateService} from "../../shared/service/candidate.service";

@Component({
  selector: 'app-add-candidate',
  templateUrl: './add-candidate.component.html',
  styleUrls: ['./add-candidate.component.css']
})
export class AddCandidateComponent implements OnInit {
  intent: 'add'|'update';
  candidateForm: FormGroup = new FormGroup({});
  candidateStatusError = false;
  validationErrors: {} | null = {};
  status = Status
  statusOptions: {name: string, value: number}[] = [];
  subscription = new Subscription()
  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private candidateService: CandidateService
  ) {}

  ngOnInit(): void {
    this.statusOptions = this.buildStatusOptions()
    const sub = this.route
      .data
      .subscribe(data => {
        this.intent = data['intent'];
        if (this.intent === 'update') {
          this.candidateForm.addControl('id', this.formBuilder.control('', Validators.required));
        }
      });
    this.subscription.add(sub);

    this.candidateForm = this.formBuilder.group({
      email: [[],
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
    });


  }

  onSubmit() {
    if (!this.candidateForm.valid) { return }
    if (this.intent === 'update') {
      this.candidateService
      .updateCandidateInIntership(this.candidateForm.value)
      .subscribe({
        next: () => {
          this.candidateStatusError = false;
          this.router.navigate(["/"]);
        },
        error: (error) => {
          console.log(error);
          this.candidateStatusError = true;
          this.validationErrors = error?.error?.message;
        },
      });
    }

    if (this.intent === 'add') {
      this.candidateService
      .addCandidateToIntership(this.candidateForm.value)
      .subscribe({
        next: () => {
          this.candidateStatusError = false;
          this.router.navigate(["/"]);
        },
        error: (error) => {
          console.log(error);
          this.candidateStatusError = true;
          this.validationErrors = error?.error?.message;
        },
      });
    }


  }

  private buildStatusOptions():  {name: string, value: number}[] {
    let options = [];
    for (const enumMember in this.status) {
      if (parseInt(enumMember, 10) >= 0) {
        options.push({name: this.status[enumMember], value: parseInt(enumMember, 10)})
      }
    }
    return options;
  }

  onFileSelected(event: any) {

    const file:File = event.target.files[0];

    if (file) {

      const formData = new FormData();

      formData.append('file', file);

      this.candidateService.uploadCandidatesCV(formData).subscribe(val => {
        this.candidateForm.controls['cv'].patchValue(val.data);
      });
    }
  }

  ngOnDestroy(): void {
    //Called once, before the instance is destroyed.
    //Add 'implements OnDestroy' to the class.
    this.subscription.unsubscribe();
  }
}
