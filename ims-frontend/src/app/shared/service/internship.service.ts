import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Internship} from "../model/internship";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class InternshipService {

  constructor(private http: HttpClient) {
  }

  getAllInternships(): Observable<Internship[]> {
    return this.http.get<Internship[]>(`${environment.apiUrl}internships`);
  }
  getInternshipById(id: string): Observable<Internship> {
    return this.http.get<Internship>(`${environment.apiUrl}internships/${id}`);
  }
  createInternship(internship: Internship): Observable<any> {
    return this.http.post(`${environment.apiUrl}internships`, internship);
  }

  updateInternship(id: string, internship: Internship) {
    return this.http.put<Internship>(`${environment.apiUrl}internships/${id}`, internship);
  }

  deleteInternship(id: string){
    return this.http.delete<Internship>(`${environment.apiUrl}internships/${id}`);
  }

  uploadPresentation(file: FormData): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}fileUpload/`, file, {
      headers: new HttpHeaders({}),
    });
  }
}
