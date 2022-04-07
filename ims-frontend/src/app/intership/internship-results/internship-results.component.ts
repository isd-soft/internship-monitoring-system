import {Component, Inject, OnInit} from '@angular/core';
import {CandidateEvaluationService} from "../../shared/service/candidate-evaluation.service";
import {CandidateService} from "../../shared/service/candidate.service";
import {Candidate} from "../../shared/model/candidate";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {Internship, PreInterviewTest} from "../../shared/model/internship";
import {InternshipResultsService} from "../../shared/service/internship-results.service";
import {InternshipResults} from "../../shared/model/internship-results";
import {ApexChart, ApexNonAxisChartSeries, ApexResponsive} from "ng-apexcharts";

export type ChartOptions = {
  series: ApexNonAxisChartSeries;
  chart: ApexChart;
  responsive: ApexResponsive[];
  labels: any;
};
@Component({
  selector: 'app-internship-results.ts',
  templateUrl: './internship-results.component.html',
  styleUrls: ['./internship-results.component.css']
})
export class InternshipResultsComponent implements OnInit {
  candidates: Candidate[] = [];
  internshipResults: InternshipResults = new InternshipResults();
  public chartOptions: Partial<ChartOptions>;
  test = PreInterviewTest;
  tests: { name: string; value: string }[];


  constructor(private candidateEvaluationSv: CandidateEvaluationService,
              private candidateService: CandidateService,
              private internshipResultsService: InternshipResultsService,
              @Inject(MAT_DIALOG_DATA) public internship: Internship) {
  }

  ngOnInit(): void {
    this.getInternshipResultsById();
    this.getCandidatesByInternshipId();
    this.tests = Object.entries(this.test).map(([key, value]) => ({
      name: key,
      value: value,
    }));

  }
  public getCategoryObject(enumType: any) {
    return this.tests.find((test) => test.name === enumType);
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

  updateCandidateModal() {

  }

  deleteCandidateModal() {

  }

  openMarksDialog() {

  }

  showFeedbackModal() {

  }

  downloadCV() {

  }
}
