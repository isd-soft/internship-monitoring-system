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
    return this.http.get<TechMark[]>(`${environment.apiUrl}tm`);
  }

  addTechMarks(techMark: TechMark): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}tm/add`, techMark);
  }
}
