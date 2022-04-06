import {Component, Inject, OnInit} from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import {Candidate, Status} from "../../shared/model/candidate";
import { Subscription } from "rxjs/internal/Subscription";
import { FeedbackService } from "../../shared/service/feedback.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Feedback, FeedbackWithUsername} from "../../shared/model/feedback";
import {MatTableDataSource} from "@angular/material/table";
import {DataSource} from "@angular/cdk/collections";

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

  myFeedback: Feedback;
  notMineFeedBacks: FeedbackWithUsername[] = []
  dataSource:DataSource<Feedback>;
  subscription = new Subscription();
  displayedColumns: string[] = ['userName', 'feedback'];

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private feedbackService: FeedbackService,
    @Inject(MAT_DIALOG_DATA) public data: { candidateId: string, internshipId: string },
    private dialogRef: MatDialogRef<FeedbackComponent>
  ) {}

  ngOnInit(): void {

    this.feedbackForm = this.formBuilder.group({
      id: [""],
      feedback: [""],
      candidateId: [""],
      userId: [""],
    });

    this.feedbackService.getFeedbackById(this.data.candidateId).subscribe((feedbacks) => {

      const myId = JSON.parse(localStorage.getItem('user')).id;
      this.feedbackForm.controls['candidateId'].patchValue(this.data.candidateId);
      this.feedbackForm.controls['userId'].patchValue(myId);
      feedbacks.forEach(feedback => {
        if (feedback.userId === myId) {
          // TODO
          this.feedbackForm.controls['feedback'].patchValue(feedback.feedback);
          this.feedbackForm.controls['id'].patchValue(feedback.id);
          return;
        }
        this.notMineFeedBacks.push(feedback);
      })
      this.dataSource = new MatTableDataSource(this.notMineFeedBacks);

    });


  }

  save() {
    if (!this.feedbackForm.valid) {
      return;
    }
    if (this.feedbackForm.controls['id'].value) {
      this.feedbackService
        .updateFeedback(this.feedbackForm.value)
        .subscribe({
          next: () => {
            this.candidateStatusError = false;
            this.dialogRef.close('update');
          },
          error: (error) => {
            console.log(error);
            this.candidateStatusError = true;
            this.validationErrors = error?.error?.message;
          },
        });
    }

    if (!this.feedbackForm.controls['id'].value) {
      const objToSend = this.feedbackForm.value;

      this.feedbackService.createFeedback(objToSend).subscribe({
        next: () => {
          this.candidateStatusError = false;
          this.dialogRef.close('create');
        },
        error: (error) => {
          console.log(error);
          this.candidateStatusError = true;
          this.validationErrors = error?.error?.message;
        },
      });
    }
  }

  onSubmit() {
    if (!this.feedbackForm.valid) {
      return;
    }
    const objToSend: Feedback&{userName: string} = {
      feedback: this.feedbackForm.value.feedback,
      candidateId: this.feedbackForm.value.candidateId,
      userId: this.feedbackForm.value.userId,
      userName: JSON.parse(localStorage.getItem('user')).username
    };
    this.feedbackService
      .saveFeedback(this.data.internshipId , objToSend)
      .subscribe((feedback) => {
          this.feedbackForm.controls["id"].patchValue(feedback.id)
          this.dialogRef.close();
        }
      );
  }

  deleteFeedback(){
    this.feedbackService.deleteFeedback(this.feedbackForm.controls['id'].value).subscribe(() => {
      this.dialogRef.close();
    });
  }

  ngOnDestroy(): void {
    //Called once, before the instance is destroyed.
    //Add 'implements OnDestroy' to the class.
    this.subscription.unsubscribe();
  }
}
