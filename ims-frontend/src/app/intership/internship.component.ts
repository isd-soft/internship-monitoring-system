import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {InternshipService} from "../shared/service/internship.service";
import {Internship} from "../shared/model/internship";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-intership',
  templateUrl: './internship.component.html',
  styleUrls: ['./internship.component.css']
})
export class InternshipComponent implements OnInit {
  internships: Internship[];
  closeResult: string;
  // internshipForm: FormGroup = new FormGroup({});
  // private formBuilder: FormBuilder
  constructor(private internshipService: InternshipService,
              private modalService: NgbModal) {
  }

  ngOnInit(): void {
    this.getAllInternships();
  }

  onSubmit() {
  }

  public getAllInternships() {
    this.internshipService.getAllInternships().subscribe({
      next: result => {
        console.log(result);
        this.internships = result
      },
      error: err => console.log("An error has occurred;")
    });
  }
  open({content}: { content: any }) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
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
