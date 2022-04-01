import { Component, Inject, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
import { TechQuestion } from "../../shared/model/techQuestion";
import { TechQuestionService } from "../../shared/service/tech-question.service";
import { TechQuestionListService } from "../../shared/service/tech-question-list.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-edit-tech-question-modal",
  templateUrl: "./edit-tech-question-modal.component.html",
  styleUrls: ["./edit-tech-question-modal.component.css"],
})
export class EditTechQuestionModalComponent implements OnInit {
  techQuestionForm: FormGroup = new FormGroup({});
  techQuestion: any = [];
  list: any = [];
  statusButton: string = "Edit";
  statusIntent: string = "Edit technical question";
  techQuestionStatusError = false;
  validationErrors: {} | null = {};
  statusOptions: { name: string; value: number }[] = [];

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: TechQuestion,
    private formBuilder: FormBuilder,
    private techQuestionService: TechQuestionService,
    private techQuestionListService: TechQuestionListService,
    private router: Router,
    private dialogRef: MatDialogRef<EditTechQuestionModalComponent>
  ) {}

  ngOnInit(): void {
    this.list = this.data;
    console.log(this.list);
    this.techQuestionForm = this.formBuilder.group({
      name: ["", Validators.required],
    });
    if (this.data) {
      this.statusButton = "Edit";
      this.statusIntent = "Edit technical question list";
      this.techQuestionForm.controls["name"].setValue(this.data.name);
    }
  }

  updateQuestionList() {
    this.techQuestionService
      .updateTechQuestion(this.data.id, this.techQuestionForm.value)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.dialogRef.close("update");
        },
        error: () => {},
      });
  }

  onSubmit() {
    if (!this.data) {
      if (this.techQuestionForm.valid) {
        this.techQuestionService
          .addTechQuestion(this.techQuestionForm.value)
          .subscribe({
            next: () => {
              this.techQuestionStatusError = false;
              this.router.navigate(["/"]);
            },
            error: (error) => {
              console.log(error);
              this.techQuestionStatusError = true;
              this.validationErrors = error?.error?.message;
            },
          });
      }
    } else {
      this.updateQuestionList();
    }
  }
}
