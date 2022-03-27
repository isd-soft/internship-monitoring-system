import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpResponse } from "@angular/common/http";
import { User } from "../model/user";
import { environment } from "../../../environments/environment";
import { Observable } from "rxjs";
import { Candidate } from "../model/candidate";
import { CandidateEvaluation } from "../model/candidate-evaluation";

@Injectable({
  providedIn: "root",
})
export class CandidateService {
  constructor(private http: HttpClient) {}

  createCandidate(candidate: Candidate): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}candidates`, candidate);
  }

  getAllCandidates(): Observable<Candidate[]> {
    return this.http.get<Candidate[]>(`${environment.apiUrl}candidates/getall`);
  }

  uploadCandidatesCV(file: FormData): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}fileUpload/`, file, {
      headers: new HttpHeaders({}),
    });
  }

  deleteCandidateFromIntership(candidate: Candidate): Observable<any> {
    return this.http.delete<any>(`${environment.apiUrl}candidates/`, {
      body: candidate,
    });
  }

  updateCandidateInIntership(candidate: Candidate): Observable<any> {
    return this.http.put<any>(`${environment.apiUrl}candidates/`, candidate);
  }

  getCandidates(): Observable<Candidate[]> {
    return this.http.get<any>(`${environment.apiUrl}candidates/`);
  }

  getCandidatesByInternship(internshipId: string): Observable<Candidate[]> {
    return this.http.get<any>(`${environment.apiUrl}candidates/internship/${internshipId}/`);
  }
}
