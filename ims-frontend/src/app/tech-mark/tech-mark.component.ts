import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { TechMark } from "../shared/model/tech-mark";
import { TechMarkService } from "../shared/service/tech-mark.service";

@Component({
  selector: "app-tech-mark",
  templateUrl: "./tech-mark.component.html",
  styleUrls: ["./tech-mark.component.css"],
})
export class TechMarkComponent implements OnInit {
  techMarkForm: FormGroup = new FormGroup({});
  techMark: TechMark[];
  techMarkStatusError = false;
  validationErrors: {} | null = {};
  statusOptions: { name: string; value: number }[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private techMarkService: TechMarkService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.techMarkForm = this.formBuilder.group({
      candidateEvaluation: [""],
      techQuestion: [""],
      mark: [],
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
