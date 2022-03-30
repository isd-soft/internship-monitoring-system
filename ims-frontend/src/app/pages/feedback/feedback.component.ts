import {Component, Inject, OnInit} from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import {Candidate, Status} from "../../shared/model/candidate";
import { Subscription } from "rxjs/internal/Subscription";
import { FeedbackService } from "../../shared/service/feedback.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: "app-feedback",
  templateUrl: "./feedback.component.html",
  styleUrls: ["./feedback.component.css"],
})
export class FeedbackComponent implements OnInit {
  intent: "add" | "update";
  feedbackForm: FormGroup = new FormGroup({});
  candidateStatusError = false;
  validationErrors: {} | null = {};
  status = Status;
  candidatesOptions: { name: string; value: string }[] = [];
  subscription = new Subscription();

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private feedbackService: FeedbackService,
    @Inject(MAT_DIALOG_DATA) public data: { candidateId: string },
    private dialogRef: MatDialogRef<FeedbackComponent>
  ) {}

  ngOnInit(): void {

    this.feedbackForm = this.formBuilder.group({
      id: [""],
      feedback: [""],
      forCandidate: [""],
    });

    this.feedbackService.getFeedbackById(this.data.candidateId).subscribe((feedbacks) => {
      console.log(feedbacks);
    //  TODO fill the array of feedbacks
    });


  }

  onSubmit() {
    if (!this.feedbackForm.valid) {
      return;
    }
    if (this.data.candidateId) {
      // this.candidateService
      //   .updateCandidateInIntership(this.feedbackForm.value)
      //   .subscribe({
      //     next: () => {
      //       this.candidateStatusError = false;
      //       this.router.navigate(["/"]);
      //     },
      //     error: (error) => {
      //       console.log(error);
      //       this.candidateStatusError = true;
      //       this.validationErrors = error?.error?.message;
      //     },
      //   });
    }

    if (!this.data.candidateId) {
      const objToSend = this.feedbackForm.value;

      this.feedbackService.submitFeedback(objToSend).subscribe({
        next: () => {
          this.candidateStatusError = false;
          this.dialogRef.close();
        },
        error: (error) => {
          console.log(error);
          this.candidateStatusError = true;
          this.validationErrors = error?.error?.message;
        },
      });
    }
  }

  save() {
    if (!this.feedbackForm.valid) {
      return;
    }
    const objToSend = {
      feedback: this.feedbackForm.value.feedback,
      forCandidate: this.feedbackForm.value.forCandidate,
    };
    this.feedbackService
      .saveFeedback(objToSend)
      .subscribe((feedback) =>
        this.feedbackForm.controls["id"].patchValue(feedback.id)
      );
  }

  ngOnDestroy(): void {
    //Called once, before the instance is destroyed.
    //Add 'implements OnDestroy' to the class.
    this.subscription.unsubscribe();
  }
}
