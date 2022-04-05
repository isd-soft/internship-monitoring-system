import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {Internship} from "../../shared/model/internship";

@Component({
  selector: 'app-confirm-dialog',
  templateUrl: './confirm-dialog.component.html',
  styleUrls: ['./confirm-dialog.component.css']
})
export class ConfirmDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public internship: Internship) { }

  ngOnInit(): void {
  }

}
