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
      `${environment.apiUrl}ce/getallmarks`
    );
  }

  addMarksToCandidateById(
    candidateEvaluation: CandidateEvaluation
  ): Observable<any> {
    return this.http.post(
      `${environment.apiUrl}ce/addmarks`,
      candidateEvaluation
    );
  }
}
