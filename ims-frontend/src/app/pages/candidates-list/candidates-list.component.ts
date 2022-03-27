import { Component, OnInit } from '@angular/core';
import {CandidateService} from "../../shared/service/candidate.service";
import {DataSource} from "@angular/cdk/collections";
import {Candidate, Status} from "../../shared/model/candidate";
import {MatTableDataSource} from "@angular/material/table";
import {ActivatedRoute} from "@angular/router";
import {take} from "rxjs";

@Component({
  selector: 'app-candidates-list',
  templateUrl: './candidates-list.component.html',
  styleUrls: ['./candidates-list.component.css']
})
export class CandidatesListComponent implements OnInit {
  dataSource: DataSource<Candidate>
  displayedColumns: string[] = ['id', 'name', 'surname', 'email','cv', 'comment',
    'status', 'mark', ];

  constructor(private candidateService: CandidateService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.params.pipe(take(1)).subscribe(params => {
      this.candidateService.getCandidatesByInternship(params['id']).subscribe(candidates => { this.dataSource = new MatTableDataSource(candidates)})
    })

  }

}
