import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "../../../environments/environment";
import { CandidateEvaluation } from "../model/candidate-evaluation";

@Injectable({
  providedIn: "root",
})
export class CandidateEvaluationService {
  constructor(private http: HttpClient) {}

  getAllCandidateEvaluations(): Observable<CandidateEvaluation[]> {
    return this.http.get<CandidateEvaluation[]>(
      `${environment.apiUrl}candidateEvaluations`
    );
  }

  getCandidateEvaluationById(id: string): Observable<CandidateEvaluation[]> {
    return this.http.get<CandidateEvaluation[]>(
      `${environment.apiUrl}candidateEvaluations/candidate/${id}`
    );
  }

  editCandidateEvaluationById(
    id: string,
    candidateMarks: CandidateEvaluation
  ): Observable<CandidateEvaluation> {
    return this.http.put<CandidateEvaluation>(
      `${environment.apiUrl}candidateEvaluations/${id}`,
      candidateMarks
    );
  }

  addMarksToCandidateById(
    candidateEvaluation: CandidateEvaluation
  ): Observable<any> {
    return this.http.post(
      `${environment.apiUrl}candidateEvaluations/add`,
      candidateEvaluation
    );
  }
}
