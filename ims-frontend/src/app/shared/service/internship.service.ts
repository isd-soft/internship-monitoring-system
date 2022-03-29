import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Internship} from "../model/internship";
import {environment} from "../../../environments/environment";
import {User} from "../model/user";

@Injectable({
  providedIn: 'root'
})
export class InternshipService {

  constructor(private http: HttpClient) {
  }

  getAllInternships() : Observable<Internship[]> {
    return this.http.get<Internship[]>(`${environment.apiUrl}internships`);
  }

  createInternship(internship: Internship): Observable<any>{
    return this.http.post(`${environment.apiUrl}internships`, internship);
  }
  uploadPresentation(file: FormData): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}fileUpload/`, file, {
      headers: new HttpHeaders({}),
    });
  }
}
