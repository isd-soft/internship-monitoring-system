import { Component, Inject, OnInit } from "@angular/core";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { TechQuestionListService } from "../../shared/service/tech-question-list.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-question-list-modal",
  templateUrl: "./question-list-modal.component.html",
  styleUrls: ["./question-list-modal.component.css"],
})
export class QuestionListModalComponent implements OnInit {
  techQuestionListForm: FormGroup = new FormGroup({});
  techQuestionListStatusError = false;
  dataSource: any = [];
  statusButton: string = "Save";
  statusIntent: string = "Add a Technical Question List";
  validationErrors: {} | null = {};
  statusOptions: { name: string; value: number }[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private techQuestionListService: TechQuestionListService,
    private router: Router,
    private dialogRef: MatDialogRef<QuestionListModalComponent>,
    @Inject(MAT_DIALOG_DATA) public editList: any
  ) {}

  ngOnInit(): void {
    console.log(this.editList);
    this.techQuestionListForm = this.formBuilder.group({
      name: [[""], Validators.required],
    });
    if (this.editList) {
      this.statusButton = "Edit";
      this.statusIntent = "Edit technical question list";
      this.techQuestionListForm.controls["name"].setValue(this.editList.name);
    }
  }

  updateQuestionList() {
    this.techQuestionListService
      .updateQuestionList(this.editList.id, this.techQuestionListForm.value)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.dialogRef.close("update");
        },
        error: () => {},
      });
  }

  getQuestionList() {
    return this.techQuestionListService
      .getAllTechQuestionList()
      .subscribe((res) => {
        this.dataSource = res;
      });
  }

  createTechnicalQuestionList() {
    if (!this.editList) {
      if (this.techQuestionListForm.valid) {
        this.techQuestionListService
          .addTechQuestionList(this.techQuestionListForm.value)
          .subscribe({
            next: () => {},
            error: (error) => {
              console.log(error);
              this.techQuestionListStatusError = true;
              this.validationErrors = error?.error?.message;
            },
          });
        this.dialogRef.close("save");
        this.getQuestionList();
      }
    } else {
      this.updateQuestionList();
    }
  }
}
