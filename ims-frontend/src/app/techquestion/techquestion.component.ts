import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";

import { ActivatedRoute, Router } from "@angular/router";
import { TechQuestionService } from "../shared/service/tech-question.service";
import { TechQuestion } from "../shared/model/techQuestion";

@Component({
  selector: "app-techquestion",
  templateUrl: "./techquestion.component.html",
  styleUrls: ["./techquestion.component.css"],
})
export class TechquestionComponent implements OnInit {
  techQuestionForm: FormGroup = new FormGroup({});
  techQuestion: TechQuestion[];
  techQuestionStatusError = false;
  validationErrors: {} | null = {};
  statusOptions: { name: string; value: number }[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private techQuestionService: TechQuestionService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.techQuestionForm = this.formBuilder.group({
      name: [""],
      techQuestionList: [""],
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
