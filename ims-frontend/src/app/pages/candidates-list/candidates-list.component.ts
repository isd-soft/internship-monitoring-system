import { Component, OnInit } from "@angular/core";
import { CandidateService } from "../../shared/service/candidate.service";
import { DataSource } from "@angular/cdk/collections";
import { Candidate } from "../../shared/model/candidate";
import { MatTableDataSource } from "@angular/material/table";
import { ActivatedRoute, Router } from "@angular/router";
import { take } from "rxjs";
import { MatDialog, MatDialogConfig } from "@angular/material/dialog";
import { AddCandidateComponent } from "../add-candidate/add-candidate.component";
import { MarksModalComponent } from "../../candidates-table/marks-modal/marks-modal.component";
import { CandidateEvaluationService } from "../../shared/service/candidate-evaluation.service";

@Component({
  selector: "app-candidates-list",
  templateUrl: "./candidates-list.component.html",
  styleUrls: ["./candidates-list.component.css"],
})
export class CandidatesListComponent implements OnInit {
  dataSource: DataSource<Candidate>;
  displayedColumns: string[] = [
    "id",
    "name",
    "surname",
    "email",
    "cv",
    "comment",
    "status",
    "mark",
    "actions",
  ];

  displayMode: "all" | "byInternship";
  lastTouchedCandidate: Candidate;
  internshipId: string;

  constructor(
    private CandidateEvaluationSv: CandidateEvaluationService,
    private candidateService: CandidateService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private dialog: MatDialog
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
      this.displayedColumns.splice(
        this.displayedColumns.length - 2,
        0,
        "internship"
      );
      this.candidateService.getAllCandidates().subscribe((candidates) => {
        this.dataSource = new MatTableDataSource(candidates);
      });
    });
  }

  selectCandidate(elem: Candidate) {
    this.lastTouchedCandidate = elem;
  }

  openMarksDialog() {
    const dialogConfig = new MatDialogConfig();

    this.CandidateEvaluationSv.getCandidateEvaluationById(
      this.lastTouchedCandidate.id.toString()
    ).subscribe(
      (res) => {
        //console.log(res);
        dialogConfig.data = res;
        this.dialog.open(MarksModalComponent, dialogConfig);
      },
      (err) => {}
    );
  }

  updateCandidateModal() {
    this.lastTouchedCandidate.internship = this.internshipId;
    const dialogRef = this.dialog.open(AddCandidateComponent, {
      data: {
        intent: "update",
        candidate: this.lastTouchedCandidate,
      },
    });
    dialogRef.afterClosed().subscribe((result) => {
      this.updateTableData();
    });
  }

  addCandidateModal() {
    const dialogRef = this.dialog.open(AddCandidateComponent, {
      data: { intent: "add" },
    });
    dialogRef.afterClosed().subscribe((result) => {
      this.updateTableData();
    });
  }

  showFeedbackModal() {
    // this.router.navigate(['/candidate-update'])
    this.updateTableData();
  }

  deleteCandidateModal() {
    // this.router.navigate(['/candidate-update'])
    this.candidateService
      .deleteCandidateFromIntership(this.lastTouchedCandidate.id.toString())
      .subscribe(() => {
        this.updateTableData();
      });
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
