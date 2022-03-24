import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { CandidateEvaluation } from "../shared/model/candidate-evaluation";
import { CandidateEvaluationService } from "../shared/service/candidate-evaluation.service";
import { CandidateService } from "../shared/service/candidate.service";

@Component({
  selector: "app-candidate-evaluation",
  templateUrl: "./candidate-evaluation.component.html",
  styleUrls: ["./candidate-evaluation.component.css"],
})
export class CandidateEvaluationComponent implements OnInit {
  candidateEvaluationForm: FormGroup = new FormGroup({});
  candidateEvaluation: CandidateEvaluation;
  candidate: any = [];
  CandidateEvaluationStatusError = false;
  validationErrors: {} | null = {};
  statusOptions: { name: string; value: number }[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private candidateEvaluationService: CandidateEvaluationService,
    private candidateService: CandidateService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.getCandidates();
    this.candidateEvaluationForm = this.formBuilder.group({
      englishMark: ["", Validators.required],
      softSkillMark: ["", Validators.required],
      practiceMark: ["", Validators.required],
      candidate: ["", Validators.required],
    });
  }

  getCandidates() {
    return this.candidateService.getAllCandidates().subscribe((res) => {
      this.candidate = res;
    });
  }

  onSubmit() {
    if (this.candidateEvaluationForm.valid) {
      this.candidateEvaluationService
        .addMarksToCandidateById(this.candidateEvaluationForm.value)
        .subscribe({
          next: () => {
            this.CandidateEvaluationStatusError = false;
            this.router.navigate(["/"]);
          },
          error: (error) => {
            console.log(error);
            this.CandidateEvaluationStatusError = true;
            this.validationErrors = error?.error?.message;
          },
        });
    }
  }
}
