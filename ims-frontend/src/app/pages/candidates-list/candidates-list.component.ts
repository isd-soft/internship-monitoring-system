import { Component, OnInit } from "@angular/core";
import { CandidateService } from "../../shared/service/candidate.service";
import { DataSource } from "@angular/cdk/collections";
import { Candidate } from "../../shared/model/candidate";
import { MatTableDataSource } from "@angular/material/table";
import { ActivatedRoute, Router } from "@angular/router";
import { take } from "rxjs";
import { MatDialog, MatDialogConfig } from "@angular/material/dialog";
import { AddCandidateComponent } from "../add-candidate/add-candidate.component";
import { FeedbackComponent } from "../feedback/feedback.component";
import { MarksModalComponent } from "src/app/candidates-table/marks-modal/marks-modal.component";
import { CandidateEvaluationService } from "src/app/shared/service/candidate-evaluation.service";
import { CandidateEvaluation } from "../../shared/model/candidate-evaluation";

@Component({
  selector: "app-candidates-list",
  templateUrl: "./candidates-list.component.html",
  styleUrls: ["./candidates-list.component.css"],
})
export class CandidatesListComponent implements OnInit {
  dataSource: DataSource<Candidate>;
  displayedColumns: string[] = [
    "name",
    "surname",
    "email",
    "cv",
    "comment",
    "status",
    "feedback",
    "actions",
  ];
  displayMode: "all" | "byInternship";
  internshipId: string;

  constructor(
    private candidateService: CandidateService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private dialog: MatDialog,
    private CandidateEvaluationSv: CandidateEvaluationService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.pipe(take(1)).subscribe((params) => {
      if (params["id"]) {
        this.displayMode = "byInternship";
        this.internshipId = params["id"];
        this.candidateService
          .getCandidatesByInternship(this.internshipId)
          .subscribe((candidates) => {
            this.dataSource = new MatTableDataSource(candidates);
          });
        return;
      }
      this.displayMode = "all";
      this.candidateService.getAllCandidates().subscribe((candidates) => {
        this.dataSource = new MatTableDataSource(candidates);
      });
    });
  }

  updateCandidateModal(candidate: Candidate) {
    const dialogRef = this.dialog.open(AddCandidateComponent, {
      data: {
        intent: "update",
        candidate: candidate,
        internship: this.internshipId,
      },
    });
    dialogRef.afterClosed().subscribe((result) => {
      this.updateTableData();
    });
  }

  openMarksDialog(id: string) {
    const dialogConfig = new MatDialogConfig();

    this.CandidateEvaluationSv.getCandidateEvaluationById(id).subscribe(
      (res) => {
        //console.log(res);
        dialogConfig.data = res;
        this.dialog.open(MarksModalComponent, dialogConfig);
      },
      (err) => {
        let candidateEvaluation = new CandidateEvaluation();

        dialogConfig.data = candidateEvaluation;

        this.dialog.open(MarksModalComponent, dialogConfig);
      }
    );
  }

  addCandidateModal() {
    const dialogRef = this.dialog.open(AddCandidateComponent, {
      data: {
        intent: "add",
        internship: this.internshipId,
      },
    });
    dialogRef.afterClosed().subscribe((result) => {
      this.updateTableData();
    });
  }

  showFeedbackModal(candidate: Candidate) {
    const dialogRef = this.dialog.open(FeedbackComponent, {
      data: { candidateId: candidate.id, internshipId: this.internshipId },
      height: '920px',
      width: '600px',
    });
    dialogRef.afterClosed().subscribe((result) => {
      this.updateTableData();
    });
    this.updateTableData();
  }

  deleteCandidateModal(candidate: Candidate) {
    this.candidateService
      .deleteCandidateFromIntership(candidate.id.toString())
      .subscribe(() => {
        this.updateTableData();
      });
  }

  downloadCV(cv: string) {
    this.candidateService.downloadCandidatesCV(cv);
  }

  private updateTableData() {
    if (this.internshipId) {
      this.candidateService
        .getCandidatesByInternship(this.internshipId)
        .subscribe((candidates) => {
          this.dataSource = new MatTableDataSource(candidates);
        });
      return;
    }
    this.candidateService.getAllCandidates().subscribe((candidates) => {
      this.dataSource = new MatTableDataSource(candidates);
    });
    return;
  }
}
