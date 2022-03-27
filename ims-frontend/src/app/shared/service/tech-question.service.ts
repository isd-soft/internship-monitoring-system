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
    return this.http.get<TechQuestion[]>(
      `${environment.apiUrl}techQuestions/getall`
    );
  }

  addTechQuestion(techQuestion: TechQuestion): Observable<any> {
    return this.http.post<any>(
      `${environment.apiUrl}techQuestions/add`,
      techQuestion
    );
  }
}
