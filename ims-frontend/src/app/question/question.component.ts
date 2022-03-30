import { Component, OnInit } from "@angular/core";
import {
  animate,
  state,
  style,
  transition,
  trigger,
} from "@angular/animations";
import { TechQuestionService } from "../shared/service/tech-question.service";
import { TechQuestionListService } from "../shared/service/tech-question-list.service";
import { TechQuestionList } from "../shared/model/techQuestionList";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { TechQuestion } from "../shared/model/techQuestion";

@Component({
  selector: "app-question",
  templateUrl: "./question.component.html",
  styleUrls: ["./question.component.css"],
  animations: [
    trigger("detailExpand", [
      state("collapsed", style({ height: "0px", minHeight: "0" })),
      state("expanded", style({ height: "*" })),
      transition(
        "expanded <=> collapsed",
        animate("225ms cubic-bezier(0.4, 0.0, 0.2, 1)")
      ),
    ]),
  ],
})
export class QuestionComponent implements OnInit {
  techQuestionListForm: FormGroup = new FormGroup({});
  techQuestionForm: FormGroup = new FormGroup({});
  techQuestionList: TechQuestionList;
  techQuestion: TechQuestion[];
  dataSource = this.techQuestionListForm;
  columnsToDisplay = ["name"];

  constructor(
    private techQuestionService: TechQuestionService,
    private techQuestionListService: TechQuestionListService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.techQuestionForm = this.formBuilder.group({
      name: ["", Validators.required],
    });
  }

  getTechQuestions() {
    return this.techQuestionService.getAllTechQuestion().subscribe((res) => {
      this.techQuestion = res;
    });
  }

  getQuestionList() {
    return this.techQuestionListService
      .getAllTechQuestionList()
      .subscribe((res) => {
        this.techQuestionList = res;
      });
  }
}
