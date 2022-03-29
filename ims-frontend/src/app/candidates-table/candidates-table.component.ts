import { Component, OnInit } from "@angular/core";
import { MatDialog, MatDialogConfig } from "@angular/material/dialog";
import { MarksModalComponent } from "./marks-modal/marks-modal.component";
import { CandidateEvaluationService } from "../shared/service/candidate-evaluation.service";

@Component({
  selector: "app-candidates-table",
  templateUrl: "./candidates-table.component.html",
  styleUrls: ["./candidates-table.component.css"],
})
export class CandidatesTableComponent implements OnInit {
  dataSource = [
    {
      id: "asd",
      name: "qeqeqeq",
    },
    {
      id: 2,
      name: "dfdfs",
    },
  ];
  displayedColumns: string[] = ["id", "name", "actions"];

  constructor(
    public dialog: MatDialog,
    private CandidateEvaluationSv: CandidateEvaluationService
  ) {}

  ngOnInit(): void {}

  openMarksDialog(id: string) {
    const dialogConfig = new MatDialogConfig();

    this.CandidateEvaluationSv.getCandidateEvaluationById(id).subscribe(
      (res) => {
        //console.log(res);
        dialogConfig.data = res;
        this.dialog.open(MarksModalComponent, dialogConfig);
      },
      (err) => {}
    );
  }
}
