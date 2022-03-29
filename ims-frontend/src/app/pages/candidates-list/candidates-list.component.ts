import { Component, OnInit } from '@angular/core';
import {CandidateService} from "../../shared/service/candidate.service";
import {DataSource} from "@angular/cdk/collections";
import {Candidate, Status} from "../../shared/model/candidate";
import {MatTableDataSource} from "@angular/material/table";
import {ActivatedRoute, Router} from "@angular/router";
import {take} from "rxjs";
import {MatDialog} from "@angular/material/dialog";
import {AddCandidateComponent} from "../add-candidate/add-candidate.component";

@Component({
  selector: 'app-candidates-list',
  templateUrl: './candidates-list.component.html',
  styleUrls: ['./candidates-list.component.css']
})
export class CandidatesListComponent implements OnInit {
  dataSource: DataSource<Candidate>
  displayedColumns: string[] = ['id', 'name', 'surname', 'email','cv', 'comment',
    'status', 'mark', 'actions'];
  displayMode: "all"|"byInternship";
  lastTouchedCandidate: Candidate;
  internshipId: string;

  constructor(private candidateService: CandidateService, private activatedRoute: ActivatedRoute, private router: Router, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.activatedRoute.params.pipe(take(1)).subscribe(params => {
      if (params['id']) {
        this.displayMode = 'byInternship'
        this.internshipId = params['id'];
        this.candidateService.getCandidatesByInternship(this.internshipId).subscribe(candidates => { this.dataSource = new MatTableDataSource(candidates)})
        return;
      }
      this.displayMode = 'all'
      this.displayedColumns.splice(this.displayedColumns.length -2, 0,'internship');
      this.candidateService.getAllCandidates().subscribe(candidates => { this.dataSource = new MatTableDataSource(candidates)})
    })

  }

  selectCandidate(elem: Candidate){
    this.lastTouchedCandidate = elem;
  }

  updateCandidateModal() {
    this.lastTouchedCandidate.internship = this.internshipId;
    const dialogRef = this.dialog.open(AddCandidateComponent, {data: {intent: 'update', candidate: this.lastTouchedCandidate}});
    dialogRef.afterClosed().subscribe(result => {
      this.updateTableData();
    });
  }

  addCandidateModal() {
    const dialogRef = this.dialog.open(AddCandidateComponent, {data: {intent: 'add'}});
    dialogRef.afterClosed().subscribe(result => {
      this.updateTableData();
    });
  }

  showFeedbackModal() {
  // this.router.navigate(['/candidate-update'])
    this.updateTableData();
  }

  deleteCandidateModal() {
  // this.router.navigate(['/candidate-update'])
    this.candidateService.deleteCandidateFromIntership(this.lastTouchedCandidate.id.toString()).subscribe(() => {
      this.updateTableData();
    })
  }

  private updateTableData() {
    if (this.internshipId) {
      this.candidateService.getCandidatesByInternship(this.internshipId).subscribe(candidates => { this.dataSource = new MatTableDataSource(candidates)})
      return;
    }
    this.candidateService.getAllCandidates().subscribe(candidates => { this.dataSource = new MatTableDataSource(candidates)});
    return
  }

}
