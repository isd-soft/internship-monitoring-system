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

  getAllTechQuestionList(): Observable<any> {
    return this.http.get<any>(`${environment.apiUrl}techQuestionLists/getall`);
  }

  addTechQuestionList(
    techQuestionList: TechQuestionList
  ): Observable<TechQuestionList> {
    return this.http.post<TechQuestionList>(
      `${environment.apiUrl}techQuestionLists/add`,
      techQuestionList
    );
  }
}
