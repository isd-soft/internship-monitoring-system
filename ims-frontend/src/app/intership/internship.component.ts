import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {InternshipService} from "../shared/service/internship.service";
import {Internship} from "../shared/model/internship";

@Component({
  selector: 'app-intership',
  templateUrl: './internship.component.html',
  styleUrls: ['./internship.component.css']
})
export class InternshipComponent implements OnInit {
  internships : Internship[];
  internshipForm: FormGroup = new FormGroup({});

  constructor(private formBuilder: FormBuilder,
              private internshipService : InternshipService) {
  }

  ngOnInit(): void {
    this.getAllInternships();
  }
  onSubmit() {
    }

  public getAllInternships() {
    this.internshipService.getAllInternships().subscribe(
      res =>{
        this.internships = res;
      },
      err => {
        alert("An error has occurred;")
      }
    )
  }
}
// this.internshipForm = this.formBuilder.group({
//   projectName: ['Upcoming Internship'],
//   category: ['list'],
//   mentors: ['list'],
//   period: ['dd-mm-yyyy'],
//   status: ['New'],
//   preInterviewTests: ['x'],
//   technicalQuestionList: ['y'],
//   linkGithub: [''],
//   linkTrello: [''],
//   linkDeployedApp: [''],
//   linkPresentation: [''],
// });
