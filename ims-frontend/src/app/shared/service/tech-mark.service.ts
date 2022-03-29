import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "../../../environments/environment";
import { TechMark } from "../model/tech-mark";

@Injectable({
  providedIn: "root",
})
export class TechMarkService {
  constructor(private http: HttpClient) {}

  getAlltechMarks(): Observable<TechMark[]> {
    return this.http.get<TechMark[]>(`${environment.apiUrl}techMarks/getall`);
  }

  getTechMarksByCandidateId(id: string): Observable<TechMark[]> {
    return this.http.get<TechMark[]>(
      `${environment.apiUrl}techMarks/candidate/${id}`
    );
  }

  editTechMarkById(id: string, techMarks: TechMark): Observable<TechMark> {
    return this.http.put<TechMark>(
      `${environment.apiUrl}techMarks/${id}`,
      techMarks
    );
  }

  addTechMarks(techMark: TechMark): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}techMarks/add`, techMark);
  }
}
