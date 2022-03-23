import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { CandidateEvaluation } from "../shared/model/candidate-evaluation";
import { CandidateEvaluationService } from "../shared/service/candidate-evaluation.service";

@Component({
  selector: "app-candidate-evaluation",
  templateUrl: "./candidate-evaluation.component.html",
  styleUrls: ["./candidate-evaluation.component.css"],
})
export class CandidateEvaluationComponent implements OnInit {
  candidateEvaluationForm: FormGroup = new FormGroup({});
  candidateEvaluation: CandidateEvaluation[];
  CandidateEvaluationStatusError = false;
  validationErrors: {} | null = {};
  statusOptions: { name: string; value: number }[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private candidateEvaluationService: CandidateEvaluationService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.candidateEvaluationForm = this.formBuilder.group({
      english: [],
      softskills: [],
      practice: [],
      candidate: [""],
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
