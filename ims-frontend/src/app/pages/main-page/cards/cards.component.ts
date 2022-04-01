import { Component } from "@angular/core";
import { CARD_DISCIPLINES } from "../../../../environments/disciplines";
import { Category, Internship, Status } from "../../../shared/model/internship";
import { User } from "../../../shared/model/user";
import { MatSnackBar } from "@angular/material/snack-bar";
import { InternshipService } from "../../../shared/service/internship.service";

@Component({
  selector: "app-cards",
  templateUrl: "./cards.component.html",
  styleUrls: ["./cards.component.css"],
})
export class CardsComponent {
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

  constructor(
    private _snackBar: MatSnackBar,
    private internshipService: InternshipService
  ) {}

  public getAllInternships() {
    this.internshipService.getAllInternships().subscribe({
      next: (result) => {
        console.log(result);
        this.dataSource = result;
      },
      error: () =>
        console.log("An error has occurred while fetching data from database"),
    });
  }
}
