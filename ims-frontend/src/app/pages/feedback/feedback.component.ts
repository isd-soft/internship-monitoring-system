import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {CandidateService} from "../../shared/service/candidate.service";
import {Status} from "../../shared/model/candidate";
import {Subscription} from "rxjs/internal/Subscription";
import {FeedbackService} from "../../shared/service/feedback.service";

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit {

  intent: 'add'|'update';
  feedbackForm: FormGroup = new FormGroup({});
  candidateStatusError = false;
  validationErrors: {} | null = {};
  status = Status
  candidatesOptions: {name: string, value: number}[] = [];
  subscription = new Subscription()

  constructor(   private formBuilder: FormBuilder,
                 private route: ActivatedRoute,
                 private router: Router,
                 private feedbackService: FeedbackService) { }

  ngOnInit(): void {

    const sub = this.route
      .data.subscribe(
        data => {  this.intent = data['intent'];
        }
      )

    this.feedbackForm = this.formBuilder.group({
      id: [''],
      feedback: [''],
      forCandidate: [''],
    });

    this.feedbackService.getAllCandidates().subscribe(candidates => {
      console.log(candidates)
      this.candidatesOptions = candidates.map(candidate => {return {value: candidate.id, name: `${candidate.name} ${candidate.surname}`}})
    })
  }

  onSubmit() {
    if (!this.feedbackForm.valid) { return }
    // if (this.intent === 'update') {
    //   this.candidateService
    //     .updateCandidateInIntership(this.feedbackForm.value)
    //     .subscribe({
    //       next: () => {
    //         this.candidateStatusError = false;
    //         this.router.navigate(["/"]);
    //       },
    //       error: (error) => {
    //         console.log(error);
    //         this.candidateStatusError = true;
    //         this.validationErrors = error?.error?.message;
    //       },
    //     });
    // }
    //
    if (this.intent === 'add') {
      const objToSend = this.feedbackForm.value;

      this.feedbackService
        .submitFeedback(objToSend.id)
        .subscribe({
          next: () => {
            this.candidateStatusError = false;
            this.router.navigate(["/"]);
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
    if (!this.feedbackForm.valid) {return; }
    const objToSend = {feedback: this.feedbackForm.value.feedback, forCandidate: this.feedbackForm.value.forCandidate };
    this.feedbackService.saveFeedback(objToSend).subscribe(feedback => this.feedbackForm.controls['id'].patchValue(feedback.id));
  }

  ngOnDestroy(): void {
    //Called once, before the instance is destroyed.
    //Add 'implements OnDestroy' to the class.
    this.subscription.unsubscribe();
  }

}
