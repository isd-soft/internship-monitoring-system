import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
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

  addTechQuestionList(techQuestionList: TechQuestionList): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}tql`, techQuestionList);
  }
}
