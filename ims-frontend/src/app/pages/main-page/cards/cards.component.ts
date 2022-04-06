import {Component, OnInit} from "@angular/core";
import { CARD_DISCIPLINES } from "../../../../environments/disciplines";
import { Category, Internship, Status } from "../../../shared/model/internship";
import { User } from "../../../shared/model/user";
import { MatSnackBar } from "@angular/material/snack-bar";
import { InternshipService } from "../../../shared/service/internship.service";
import {CandidateService} from "../../../shared/service/candidate.service";
import {AccountService} from "../../../shared/service/account.service";
import {Candidate} from "../../../shared/model/candidate";

@Component({
  selector: "app-cards",
  templateUrl: "./cards.component.html",
  styleUrls: ["./cards.component.css"],
})
export class CardsComponent implements OnInit{
  cards = CARD_DISCIPLINES;
  internships: Internship[];
  displayedColumns: string[] = [
    "position",
    "projectName",
    "category",
    "period",
    "mentorsId",
    "internshipStatus",
    "candidates",
    "actions",
    "links",
  ];
  // 'gitHubUrl', 'trelloBoardUrl', 'deployedAppUrl', 'presentationUrl',
  dataSource: any = [];
  closeResult: string;
  mentors: User[];
  status = Status;
  statuses: { name: string; value: string }[];
  category = Category;
  categories: { name: string; value: string }[];
  lastTouchedInternship: Internship;
  activeCardId: string|null = null;
  allMentors: User[] = [];
  allCandidatesByInternship: Candidate[] = [];

  constructor(
    private _snackBar: MatSnackBar,
    private internshipService: InternshipService,
    private accountService: AccountService,
    private candidateService: CandidateService
  ) {}

  public getAllInternships() {
    this.internshipService.getAllInternships().subscribe({
      next: (result) => {
        console.log(result);
        this.internships = result;
      },
      error: () =>
        console.log("An error has occurred while fetching data from database"),
    });
  }

  public getAllCandidates() {
    this.candidateService.getAllCandidates().subscribe({
      next: (result) => {
        console.log(result);
        this.allCandidatesByInternship = result;
      },
      error: () =>
        console.log("An error has occurred while fetching data from database"),
    });
  }

  public getAllMentors() {
    this.accountService.getAll().subscribe({
      next: (result) => {
        console.log(result);
        this.allMentors = result;
      },
      error: () =>
        console.log("An error has occurred while fetching data from database"),
    });
  }

  mouseOverCardAction(internship: Internship){
    this.activeCardId = internship.id;
    this.allCandidatesByInternship = [];
    if(internship.internshipStatus === 'Interviewing' || internship.internshipStatus === 'New') {return ;}
    this.candidateService.getCandidatesByInternship(internship.id).subscribe(candidates => {
      this.allCandidatesByInternship = candidates;
    })
  }

  mentorNameFromArray(id: string){
    const mentor = this.allMentors.find((mentor) => mentor.id === id);
    return mentor.name + ' ' + mentor.surname;
  }

  public getStatusObject(enumType: any): string {
    return this.statuses.find((status) => status.name === enumType).value;
  }

  ngOnInit(): void {
    this.getAllInternships();
    this.getAllMentors();
    this.statuses = Object.entries(this.status).map(([key, value]) => ({
      name: key,
      value: value,
    }));
  }
}
