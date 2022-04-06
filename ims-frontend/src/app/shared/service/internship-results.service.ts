import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {InternshipResults} from "../model/internship-results";

@Injectable({
  providedIn: 'root'
})
export class InternshipResultsService {

  constructor(private http: HttpClient) { }

  getInternshipResultsById(internshipId: string): Observable<InternshipResults> {
    return this.http.get<InternshipResults>(`${environment.apiUrl}internships/${internshipId}/results`);
  }
}
