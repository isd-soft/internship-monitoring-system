import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatDialog} from "@angular/material/dialog";
import {Category, PreInterviewTest, Status} from "../../shared/model/internship";
import {User} from "../../shared/model/user";
import {TechQuestionListService} from "../../shared/service/techQuestionList.service";
import {TechQuestionList} from "../../shared/model/techQuestionList";
import {TechquestionlistComponent} from "../../techquestionlist/techquestionlist.component";

@Component({
  selector: 'app-add-internship',
  templateUrl: './add-internship.component.html',
  styleUrls: ['./add-internship.component.css']
})
export class AddInternshipComponent implements OnInit {
  internshipForm: FormGroup = new FormGroup({});
  status = Status;
  statuses: string[] = Object.values(this.status);
  defaultStatus = 'New';
  category = Category;
  categories: string[] = Object.values(this.category);
  preInterviewTest = PreInterviewTest;
  preInterviewTestList: string[] = Object.values(this.preInterviewTest);
  techQuestionslist: TechQuestionList[];

  constructor(public dialog: MatDialog,
              private formBuilder: FormBuilder,
              private techQuestionListService: TechQuestionListService,) {
    this.internshipForm = formBuilder.group({
      projectName: [''],
      category: [''],
      mentors: [''],
      periodFrom: [''],
      periodTo: [''],
      internshipStatus: [null, Validators.required],
      preInterviewTestList: [''],
      techQuesListName: [''],
      gitHubUrl: [''],
      trelloBoardUrl: [''],
      deployedAppUrl: [''],
      presentationUrl: ['']
    });
    this.internshipForm.get('internshipStatus').setValue(this.defaultStatus);
    this.getAllTechQuestionLists()
  }

  ngOnInit() {

  }
  public getAllTechQuestionLists() {
    this.techQuestionListService.getAlltechQuestionList().subscribe({
      next: (result) => {
        console.log(result);
        this.techQuestionslist = result;
      },
      error: (err) => console.log("Error"),
    });
  }
}
