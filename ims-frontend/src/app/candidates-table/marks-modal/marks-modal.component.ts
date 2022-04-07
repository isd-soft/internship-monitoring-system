import {Component, Inject, OnInit} from "@angular/core";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {FormBuilder, FormGroup} from "@angular/forms";
import {CandidateEvaluationService} from "../../shared/service/candidate-evaluation.service";
import {TechQuestionService} from "../../shared/service/tech-question.service";
import {CandidateService} from "../../shared/service/candidate.service";
import {TechMarkService} from "../../shared/service/tech-mark.service";
import {Subscription} from "rxjs/internal/Subscription";
import {TechMark} from "../../shared/model/tech-mark";
import {CandidateEvaluation} from "../../shared/model/candidate-evaluation";
import {MatSnackBar} from "@angular/material/snack-bar";
import {PreInterviewTestMarkService} from "../../shared/service/pre-interview-test-mark.service";
import {PreInterviewTestMark} from "../../shared/model/pre-interview-test-mark";

@Component({
  selector: "app-marks-modal",
  templateUrl: "./marks-modal.component.html",
  styleUrls: ["./marks-modal.component.css"],
})
export class MarksModalComponent implements OnInit {
  candidates: any = [];
  techQuestions: any = [];
  marksForm: FormGroup = new FormGroup({});
  marksArray: TechMark[] = [];
  preInterviewTestMarks: PreInterviewTestMark[] = [];
  public min : number = 0;
  public max : number = 100;


  constructor(
    @Inject(MAT_DIALOG_DATA) public data: CandidateEvaluation,
    private _snackBar: MatSnackBar,
    private formBuilder: FormBuilder,
    private candidateEvaluationSv: CandidateEvaluationService,
    private techQuestionService: TechQuestionService,
    private candidateService: CandidateService,
    private techMarkService: TechMarkService,
    private preInterviewTestMarkService : PreInterviewTestMarkService
  ) {}

  ngOnInit(): void {
    this.getTechQuestions();
    this.getCandidates();
    this.getTechMarksByCandidateId();
    this.getPreInterviewTestMarksByCandidateId();
    this.marksForm = this.formBuilder.group({
      englishMark: [{value: this.data.englishMark, disabled: false}],
      softSkillMark: [{value: this.data.softSkillMark, disabled: false}],
      practiceMark: [{value: this.data.practiceMark, disabled: false}],
      averageMark: [{value: this.data.averageMark, disabled: true}],
    });
  }

  getTechMarksByCandidateId(): Subscription {
    return this.techMarkService
      .getTechMarksByCandidateId(this.data.candidate)
      .subscribe((res) => {
        console.log(res)
        this.marksArray = res;
      });
  }
  getPreInterviewTestMarksByCandidateId(): Subscription {
    return this.preInterviewTestMarkService.getPreInterviewTestMarksByCandidateId(this.data.candidate)
      .subscribe((res) => {
        console.log(res);
        this.preInterviewTestMarks = res;
      });
  }
  getTechQuestions() {
    return this.techQuestionService.getAllTechQuestion().subscribe((res) => {
      console.log(res);
      this.techQuestions = res;
    });
  }

  getQuestionNameById(id: string) {
    const posibleQuestion = this.techQuestions.find(
      (techQuestion: any) => techQuestion.id === id
    );
    return posibleQuestion != undefined ? posibleQuestion.name : id;
  }

  getCandidates(): Subscription {
    return this.candidateService
      .getCandidates(this.data.candidate)
      .subscribe((res) => {
        this.candidates = res;
      });
  }

  submit(): void {
    this.candidateEvaluationSv
      .editCandidateEvaluationById(this.data.id, this.marksForm.value)
      .subscribe({
        next: (res) => {
          this.marksForm.get("englishMark").enable();
          this.marksForm.get("softSkillMark").enable();
          this.marksForm.get("practiceMark").enable();
        },
      });
    console.log(this.marksArray);
    this.marksArray.forEach((marks) => {
      this.techMarkService
        .editTechMarkById(marks.id, marks)
        .subscribe((res) => {
          marks = res;
        });
    });

    this._snackBar.open("Saved successfully", "Ok!");
  }

  submitPreInterviewForm() {
    console.log(this.marksArray);
    this.preInterviewTestMarks.forEach((marks) => {
      this.preInterviewTestMarkService
        .editPreInterviewTestMarkById(marks.id, marks)
        .subscribe((res) => {
          marks = res;
        });
    });

    this._snackBar.open("Saved successfully", "Ok!");
  }

  validateValue($event: any) {

  }
}
