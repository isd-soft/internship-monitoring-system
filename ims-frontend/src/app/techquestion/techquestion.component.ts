import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

import { Router } from "@angular/router";
import { TechQuestionService } from "../shared/service/tech-question.service";
import { TechQuestion } from "../shared/model/techQuestion";
import { TechQuestionList } from "../shared/model/techQuestionList";
import { TechQuestionListService } from "../shared/service/tech-question-list.service";

@Component({
  selector: "app-techquestion",
  templateUrl: "./techquestion.component.html",
  styleUrls: ["./techquestion.component.css"],
})
export class TechquestionComponent implements OnInit {
  techQuestionForm: FormGroup = new FormGroup({});
  techQuestionListForm: FormGroup = new FormGroup({});
  techQuestion: TechQuestion[];
  techQuestionList: TechQuestionList[];
  techQuestionStatusError = false;
  validationErrors: {} | null = {};
  statusOptions: { name: string; value: number }[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private techQuestionService: TechQuestionService,
    private techQuestionListService: TechQuestionListService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getQuestionList();
    this.techQuestionListForm = this.formBuilder.group({
      name: [[""], Validators.required],
    });
    this.techQuestionForm = this.formBuilder.group({
      name: ["", Validators.required],
      // techQuestionList: ["", Validators.required],
    });
  }

  getQuestionList() {
    return this.techQuestionListService
      .getAllTechQuestionList()
      .subscribe((res) => {
        this.techQuestionList = res;
      });
  }

  onSubmit() {
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
  }
}
