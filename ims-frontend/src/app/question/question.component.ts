import { Component, OnInit } from "@angular/core";
import {
  animate,
  state,
  style,
  transition,
  trigger,
} from "@angular/animations";
import { TechQuestionService } from "../shared/service/tech-question.service";
import { TechQuestionListService } from "../shared/service/tech-question-list.service";
import { TechQuestion } from "../shared/model/techQuestion";
import { MatDialog } from "@angular/material/dialog";
import { QuestionListModalComponent } from "./question-list-modal/question-list-modal.component";
import { TechQuestionModalComponent } from "./tech-question-modal/tech-question-modal.component";
import { MatSnackBar } from "@angular/material/snack-bar";
import { EditTechQuestionModalComponent } from "./edit-tech-question-modal/edit-tech-question-modal.component";

@Component({
  selector: "app-question",
  templateUrl: "./question.component.html",
  styleUrls: ["./question.component.css"],
  animations: [
    trigger("detailExpand", [
      state("collapsed", style({ height: "0px", minHeight: "0" })),
      state("expanded", style({ height: "*" })),
      transition(
        "expanded <=> collapsed",
        animate("225ms cubic-bezier(0.4, 0.0, 0.2, 1)")
      ),
    ]),
  ],
})
export class QuestionComponent implements OnInit {
  techQuestionList: any = [];
  techQuestions: any = [];
  dataSource: any = [];
  data: any;
  columnsToDisplay = ["name"];

  expandedElement: TechQuestion[];

  constructor(
    private _snackBar: MatSnackBar,
    private techQuestionService: TechQuestionService,
    private techQuestionListService: TechQuestionListService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.getTechQuestionsByTechQuestionListId(this.dataSource.id);
    this.getQuestionList();
  }

  addNewList() {
    const dialogRef = this.dialog.open(QuestionListModalComponent);
    dialogRef.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
      if (result === "save") {
        this.getQuestionList();
      }
      this.getQuestionList();
    });
  }

  addNewTechQuestion(element: any) {
    const dialogRef = this.dialog.open(TechQuestionModalComponent, {
      data: element,
    });
    dialogRef.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
      if (result === "save") {
        this.getQuestionList();
      }
    });
  }

  getTechQuestionsByTechQuestionListId(id: string) {
    return this.techQuestionService
      .getTechQuestionByQuestionListId(id)
      .subscribe((res) => {
        this.techQuestions = res;
      });
  }

  deteleQuestionListById(id: string) {
    this.techQuestionListService.deteleTechQuestionListById(id).subscribe({
      next: (res) => {
        // TODO - custom notification message
        this._snackBar.open("Deleted successfully", "OK");
        this.getQuestionList();
      },
      error: () => {},
    });
  }

  sendId(element: any) {
    this.dialog.open(TechQuestionModalComponent, {
      data: element,
    });
  }

  updateQuestionList(element: any) {
    this.dialog
      .open(QuestionListModalComponent, {
        data: element,
      })
      .afterClosed()
      .subscribe((result) => {
        console.log(`Dialog result: ${result}`);
        if (result === "update") {
          this.getQuestionList();
          // TODO - custom notification message
          this._snackBar.open("Edited successfully", "OK");
        }
      });
  }

  updateTechQuestion(question: any) {
    this.dialog
      .open(EditTechQuestionModalComponent, {
        data: question,
      })
      .afterClosed()
      .subscribe((result) => {
        console.log(`Dialog result: ${result}`);
        if (result === "update") {
          this.getQuestionList();
          // TODO - custom notification message
          this._snackBar.open("Edited successfully", "OK");
        }
      });
  }

  deteleQuestionById(id: string) {
    this.techQuestionService.deteleTechQuestionById(id).subscribe({
      next: (res) => {
        // TODO - custom notification message
        this._snackBar.open("Deleted successfully", "OK");
        this.getTechQuestionsByTechQuestionListId(id);
      },
      error: () => {
        alert("Error while deleting internship");
      },
    });
  }

  getQuestionList() {
    return this.techQuestionListService
      .getAllTechQuestionList()
      .subscribe((res) => {
        this.dataSource = res;
      });
  }
}
