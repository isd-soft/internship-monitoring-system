import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {InternshipService} from "../shared/service/internship.service";
import {Category, Internship, StatusEnum} from "../shared/model/internship";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {MatDialog} from '@angular/material/dialog';
import {AddInternshipComponent} from "./add-internship/add-internship.component";
import {User} from "../shared/model/user";
import {AccountService} from "../shared/service/account.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ConfirmDialogComponent} from "./confirm-dialog/confirm-dialog.component";
import {InternshipResultsComponent} from "./internship-results/internship-results.component";

@Component({
  selector: "app-internship",
  templateUrl: "./internship.component.html",
  styleUrls: ["./internship.component.css"],
})
export class InternshipComponent implements AfterViewInit {
  internships: Internship[];
  displayedColumns: string[] = [
    "position",
    "projectName",
    "category",
    "period",
    "mentorsId",
    "internshipStatus",
    "candidates",
    "results",
    "actions",
    "links",
  ];
  dataSource: MatTableDataSource<Internship>;
  closeResult: string;
  mentors: User[] = [];
  status = StatusEnum;
  statuses: { name: string; value: string }[];
  category = Category;
  categories: { name: string; value: string }[];
  lastTouchedInternship: Internship;
  selectedRowPosition: -1;
  myInternships: boolean;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private _snackBar: MatSnackBar,
    private internshipService: InternshipService,
    private userService: AccountService,
    private dialog: MatDialog
  ) {
  }

  ngAfterViewInit(): void {
    this.getAllInternships();
    this.categories = Object.entries(this.category).map(([key, value]) => ({
      name: key,
      value: value,
    }));
    this.statuses = Object.entries(this.status).map(([key, value]) => ({
      name: key,
      value: value,
    }));
    this.getMentors();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  getAllInternships() {
    this.internshipService.getAllInternships().subscribe({
      next: (result) => {
        console.log(result);
        this.dataSource = new MatTableDataSource(result);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
      error: () =>
        console.log("An error has occurred while fetching data from database"),
    });
  }

  getMentorById(id: string) {
    return this.mentors.find((mentor: any) => mentor.id === id);
  }

  getMentors() {
    this.userService.getAll().subscribe((res: User[]) => {
      this.mentors = res;
    });
  }

  getStatusObject(enumType: any) {
    return this.statuses.find((status) => status.name === enumType);
  }

  getCategoryObject(enumType: any) {
    return this.categories.find((category) => category.name === enumType);
  }

  addInternship() {
    const dialogRef = this.dialog.open(AddInternshipComponent, {
      width: "75%",
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
      if (result === "save") {
        this.getAllInternships();
      }
    });
  }

  editInternship(row: any) {
    this.dialog
      .open(AddInternshipComponent, {
        width: "75%",
        data: row,
      })
      .afterClosed()
      .subscribe((result) => {
        console.log(`Dialog result: ${result}`);
        if (result === "update") {
          this.getAllInternships();
          // TODO - custom notification message
          this._snackBar.open("Edited successfully", "OK");
        }
      });
  }

  deleteInternship(row: Internship) {
    console.log(row.id);
    this.dialog.open(ConfirmDialogComponent, {
      // width: '75%',
      data: row
    }).afterClosed().subscribe(confirm => {
      if (confirm) {
        this.internshipService.deleteInternship(row.id).subscribe({
          next: (res) => {
            // TODO - custom notification message
            this._snackBar.open("Deleted successfully", "OK");
            this.getAllInternships();
          },
          error: () => {
            alert("Error while deleting internship");
          }
        });
      }
    });
  }

  openPresentation() {
  }

  getURL(url: string) {
    if(url.startsWith('http://') || url.startsWith('https://')){
      return url;
    }else {
      return 'https://' + url;
    }
  };

  filterLoggedUserInternships(myInternships: boolean) {
    if (!myInternships) {
      this.getAllInternships();
    } else {
      this.dataSource.filter = JSON.parse(localStorage.getItem("user")).id;
    }
  }

  getInternshipResults(row: Internship) {
    this.dialog.open(InternshipResultsComponent, {
      width: '90%',
      height: '90%',
      data: row
    }).afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
      this.getAllInternships();
    });
  }
}
