import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CandidateService} from "./candidate.service";
import {Observable} from "rxjs";
import {Candidate} from "../model/candidate";
import {Feedback} from "../model/feedback";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {

  constructor(private http: HttpClient, private candidateService: CandidateService) {
  }

  getAllCandidates(): Observable<Candidate[]>{
    return this.candidateService.getCandidates();
  }

  saveFeedback(feedBack: Feedback) {
    return this.http.post<any>(`${environment.apiUrl}feedback`, feedBack);
  }

  submitFeedback(id: string) {
    return this.http.post<any>(`${environment.apiUrl}message`, {id});
  }
}
