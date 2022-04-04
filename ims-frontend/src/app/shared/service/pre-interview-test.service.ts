import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CandidateEvaluation} from "../model/candidate-evaluation";
import {environment} from "../../../environments/environment";
import {PreInterviewTest} from "../model/internship";
import {PreInterviewTestEvaluation} from "../model/pre-interview-test-evaluation";

@Injectable({
  providedIn: 'root'
})
export class PreInterviewTestService {

  constructor(private http: HttpClient) { }

  getCandidatePreInterviewTestById(id: string): Observable<PreInterviewTestEvaluation[]> {
    return this.http.get<PreInterviewTestEvaluation[]>(
      `${environment.apiUrl}candidateEvaluations/candidate/${id}`
    );
  }
}
