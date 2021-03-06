import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CandidateEvaluation} from "../model/candidate-evaluation";
import {environment} from "../../../environments/environment";
import {PreInterviewTest} from "../model/internship";
import {PreInterviewTestMark} from "../model/pre-interview-test-mark";
import {TechMark} from "../model/tech-mark";

@Injectable({
  providedIn: 'root'
})
export class PreInterviewTestMarkService {

  constructor(private http: HttpClient) { }

  getPreInterviewTestMarksByCandidateId(id: string): Observable<PreInterviewTestMark[]> {
    return this.http.get<PreInterviewTestMark[]>(
      `${environment.apiUrl}preInterviewTestMaks/${id}`
    );
  }

  editPreInterviewTestMarkById(id: string, marks: PreInterviewTestMark) : Observable<PreInterviewTestMark> {
    return this.http.put<PreInterviewTestMark>(
      `${environment.apiUrl}preInterviewTestMaks/${id}`,
      marks
    );
  }
}
