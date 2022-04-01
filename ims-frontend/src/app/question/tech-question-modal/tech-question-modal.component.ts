import { Component, Inject, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { TechQuestionListService } from "../../shared/service/tech-question-list.service";
import { Router } from "@angular/router";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
import { TechQuestionService } from "../../shared/service/tech-question.service";
import { TechQuestion } from "../../shared/model/techQuestion";

@Component({
  selector: "app-tech-question-modal",
  templateUrl: "./tech-question-modal.component.html",
  styleUrls: ["./tech-question-modal.component.css"],
})
export class TechQuestionModalComponent implements OnInit {
  techQuestionForm: FormGroup = new FormGroup({});
  techQuestion: any = [];
  list: any = [];
  techQuestionStatusError = false;
  validationErrors: {} | null = {};
  statusOptions: { name: string; value: number }[] = [];

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: TechQuestion,
    private formBuilder: FormBuilder,
    private techQuestionService: TechQuestionService,
    private techQuestionListService: TechQuestionListService,
    private router: Router,
    private dialogRef: MatDialogRef<TechQuestionModalComponent>
  ) {}

  ngOnInit(): void {
    this.list = this.data;
    console.log(this.list);
    this.techQuestionForm = this.formBuilder.group({
      name: ["", Validators.required],
      techQuestionList: [this.data.id],
    });
  }

  onSubmit() {
    if (this.techQuestionForm.valid) {
      this.techQuestionService
        .addTechQuestion(this.techQuestionForm.value)
        .subscribe({
          next: () => {
            this.techQuestionStatusError = false;
          },
          error: (error) => {
            console.log(error);
            this.techQuestionStatusError = true;
            this.validationErrors = error?.error?.message;
          },
        });
    }
    this.dialogRef.close("save");
  }
}
