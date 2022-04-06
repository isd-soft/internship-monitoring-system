import {Component, Inject, OnInit} from '@angular/core';
import {CandidateEvaluationService} from "../../shared/service/candidate-evaluation.service";
import {CandidateService} from "../../shared/service/candidate.service";
import {MatTableDataSource} from "@angular/material/table";
import {DataSource} from "@angular/cdk/collections";
import {Candidate} from "../../shared/model/candidate";
import {ActivatedRoute} from "@angular/router";
import {take} from "rxjs";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {Internship} from "../../shared/model/internship";

@Component({
  selector: 'app-internship-results',
  templateUrl: './internship-results.component.html',
  styleUrls: ['./internship-results.component.css']
})
export class InternshipResultsComponent implements OnInit {
  dataSource: DataSource<Candidate>;
  headers: string[] = [];
  candidates: Candidate[] = [];

  constructor(private candidateEvaluationSv: CandidateEvaluationService,
              private candidateService: CandidateService,
              @Inject(MAT_DIALOG_DATA) public internship: Internship) {
  }

  ngOnInit(): void {
    this.getCandidatesByInternshipId();
    this.headers = this.candidates.map(x => x.id);
  }

  getCandidatesByInternshipId() {
    this.candidateService
      .getCandidatesByInternship(this.internship.id)
      .subscribe((candidates) => {
        this.candidates = candidates;
        this.dataSource = new MatTableDataSource(candidates);
        console.log(this.dataSource);
      });
  }


  getCandidateName(id: string) {
    const candidate = this.candidates.find(candidate => candidate.id === id);
    return candidate.name + " " + candidate.surname;
  }
}
