import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "../../../environments/environment";
import { TechQuestion } from "../model/techQuestion";

@Injectable({
  providedIn: "root",
})
export class TechQuestionService {
  constructor(private http: HttpClient) {}

  getAllTechQuestion(): Observable<TechQuestion[]> {
    return this.http.get<TechQuestion[]>(`${environment.apiUrl}techQuestions`);
  }

  getTechQuestionByQuestionListId(id: string): Observable<TechQuestion[]> {
    return this.http.get<TechQuestion[]>(
      `${environment.apiUrl}techQuestions/techQuestionList/${id}`
    );
  }

  updateTechQuestion(id: string, techQuestion: TechQuestion) {
    return this.http.put<TechQuestion>(
      `${environment.apiUrl}techQuestions/${id}`,
      techQuestion
    );
  }

  deteleTechQuestionById(id: string): Observable<any> {
    return this.http.delete<any>(`${environment.apiUrl}techQuestions/${id}`);
  }

  addTechQuestion(techQuestion: TechQuestion): Observable<any> {
    return this.http.post<any>(
      `${environment.apiUrl}techQuestions`,
      techQuestion
    );
  }
}
