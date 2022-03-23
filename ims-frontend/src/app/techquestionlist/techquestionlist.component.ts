import { Component, OnInit } from "@angular/core";
import { TechQuestionList } from "../shared/model/techQuestionList";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { TechQuestionListService } from "../shared/service/tech-question-list.service";

@Component({
  selector: "app-techquestionlist",
  templateUrl: "./techquestionlist.component.html",
  styleUrls: ["./techquestionlist.component.css"],
})
export class TechquestionlistComponent implements OnInit {
  techQuestionListForm: FormGroup = new FormGroup({});
  techQuestionLists: TechQuestionList[];
  techQuestionListStatusError = false;
  validationErrors: {} | null = {};
  statusOptions: { name: string; value: number }[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private techQuestionListService: TechQuestionListService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.techQuestionListForm = this.formBuilder.group({
      name: [""],
    });
  }

  onSubmit() {
    if (this.techQuestionListForm.valid) {
      this.techQuestionListService
        .addTechQuestionList(this.techQuestionListForm.value)
        .subscribe({
          next: () => {
            this.techQuestionListStatusError = false;
            this.router.navigate(["/"]);
          },
          error: (error) => {
            console.log(error);
            this.techQuestionListStatusError = true;
            this.validationErrors = error?.error?.message;
          },
        });
    }
  }
}
