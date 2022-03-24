import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { TechMark } from "../shared/model/tech-mark";
import { TechMarkService } from "../shared/service/tech-mark.service";
import { TechQuestionService } from "../shared/service/tech-question.service";
import { CandidateService } from "../shared/service/candidate.service";

@Component({
  selector: "app-tech-mark",
  templateUrl: "./tech-mark.component.html",
  styleUrls: ["./tech-mark.component.css"],
})
export class TechMarkComponent implements OnInit {
  techMarkForm: FormGroup = new FormGroup({});
  techMark: TechMark[];
  techQuestions: any = [];
  candidates: any = [];
  techMarkStatusError = false;
  validationErrors: {} | null = {};
  statusOptions: { name: string; value: number }[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private techQuestionService: TechQuestionService,
    private candidateService: CandidateService,
    private techMarkService: TechMarkService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.getTechQuestions();
    this.getCandidates();
    this.techMarkForm = this.formBuilder.group({
      mark: ["", Validators.required],
      techQuestion: ["", Validators.required],
      candidate: ["", Validators.required],
    });
  }

  getTechQuestions() {
    return this.techQuestionService.getAllTechQuestion().subscribe((res) => {
      this.techQuestions = res;
    });
  }

  getCandidates() {
    return this.candidateService.getAllCandidates().subscribe((res) => {
      this.candidates = res;
    });
  }

  onSubmit() {
    if (this.techMarkForm.valid) {
      this.techMarkService.addTechMarks(this.techMarkForm.value).subscribe({
        next: () => {
          this.techMarkStatusError = false;
          this.router.navigate(["/"]);
        },
        error: (error) => {
          console.log(error);
          this.techMarkStatusError = true;
          this.validationErrors = error?.error?.message;
        },
      });
    }
  }
}
