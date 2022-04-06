import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {CandidateEvaluationService} from "../../shared/service/candidate-evaluation.service";
import {CandidateService} from "../../shared/service/candidate.service";
import {MatTableDataSource} from "@angular/material/table";
import {DataSource} from "@angular/cdk/collections";
import {Candidate} from "../../shared/model/candidate";
import {ActivatedRoute} from "@angular/router";
import {take} from "rxjs";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {Internship} from "../../shared/model/internship";
import {InternshipResultsService} from "../../shared/service/internship-results.service";
import {InternshipResults} from "../../shared/model/internship-results";
import {MatAccordion} from "@angular/material/expansion";

@Component({
  selector: 'app-internship-results.ts',
  templateUrl: './internship-results.component.html',
  styleUrls: ['./internship-results.component.css']
})
export class InternshipResultsComponent implements OnInit {
  dataSource: DataSource<Candidate>;
  headers: string[] = [];
  candidates: Candidate[] = [];
  internshipResults: InternshipResults;

  @ViewChild(MatAccordion) accordion: MatAccordion;

  constructor(private candidateEvaluationSv: CandidateEvaluationService,
              private candidateService: CandidateService,
              private internshipResultsService: InternshipResultsService,
              @Inject(MAT_DIALOG_DATA) public internship: Internship) {
  }

  ngOnInit(): void {
    this.getInternshipResultsById();
    this.getCandidatesByInternshipId();
    this.headers = this.candidates.map(x => x.id);
  }

  getInternshipResultsById() {
    this.internshipResultsService
      .getInternshipResultsById(this.internship.id)
      .subscribe((res) => {
        console.log(res);
        this.internshipResults = res;
      });
  }

  getCandidatesByInternshipId() {
    this.candidateService
      .getCandidatesByInternship(this.internship.id)
      .subscribe((candidates) => {
        this.candidates = candidates;
      });
  }

  getCandidateName(id: string) {
    const candidate = this.candidates.find(candidate => candidate.id === id);
    return candidate.name + " " + candidate.surname;
  }
}
