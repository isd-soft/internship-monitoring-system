import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { CandidateService } from "./candidate.service";
import { Observable } from "rxjs";
import { Candidate } from "../model/candidate";
import {Feedback, FeedbackWithUsername} from "../model/feedback";
import { environment } from "../../../environments/environment";

@Injectable({
  providedIn: "root",
})
export class FeedbackService {
  constructor(
    private http: HttpClient,
    private candidateService: CandidateService
  ) {}

  getFeedbackById(id: string): Observable<FeedbackWithUsername[]> {
    return this.http.get<any>(`${environment.apiUrl}feedback/candidate/${id}`);
  }

  createFeedback(feedBack: Feedback) {
    return this.http.post<any>(`${environment.apiUrl}feedback`, feedBack);
  }

  updateFeedback(feedback: Feedback) {
    return this.http.put<any>(`${environment.apiUrl}feedback`, feedback);
  }

  saveFeedback(internshipId: string ,feedback: Feedback) {
    return this.http.post<any>(`${environment.apiUrl}message/${internshipId}`, feedback);
  }

  deleteFeedback(id: string) {
    return this.http.delete<any>(`${environment.apiUrl}feedback/${id}`);
  }
}
