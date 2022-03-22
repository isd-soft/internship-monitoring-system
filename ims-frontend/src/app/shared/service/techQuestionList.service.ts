import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Internship } from "../model/internship";
import { environment } from "../../../environments/environment";
import { TechQuestionList } from "../model/techQuestionList";

@Injectable({
  providedIn: "root",
})
export class TechQuestionListService {
  constructor(private http: HttpClient) {}

  getAlltechQuestionList(): Observable<TechQuestionList[]> {
    return this.http.get<TechQuestionList[]>(`${environment.apiUrl}tql`);
  }

  createInternship(TechQuestionList: TechQuestionList): Observable<any> {
    return this.http.post(`${environment.apiUrl}tql`, TechQuestionList);
  }
}
