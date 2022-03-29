import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {Category, PreInterviewTest, Status} from "../../shared/model/internship";
import {User} from "../../shared/model/user";
import {TechQuestionList} from "../../shared/model/techQuestionList";
import {TechQuestionListService} from "../../shared/service/tech-question-list.service";
import {InternshipService} from "../../shared/service/internship.service";
import {AccountService} from "../../shared/service/account.service";

@Component({
  selector: 'app-add-internship',
  templateUrl: './add-internship.component.html',
  styleUrls: ['./add-internship.component.css']
})
export class AddInternshipComponent implements OnInit {
  internshipForm: FormGroup = new FormGroup({});
  status = Status;
  statuses: { name: string; value: string }[];
  defaultStatus = {name: Status.NEW, value: 'New'};
  category = Category;
  categories: { name: string; value: string }[];
  mentors: User[];
  preInterviewTest = PreInterviewTest;
  preInterviewTestList: { name: string; value: string }[];
  techQuestionsList: TechQuestionList[];
  validationErrors: {} | null = {};
  techQuestionListStatusError = false;

  constructor(public dialog: MatDialog,
              private formBuilder: FormBuilder,
              private techQuestionListService: TechQuestionListService,
              private internshipService: InternshipService,
              private userService: AccountService,
              private dialogRef: MatDialogRef<AddInternshipComponent>) {
    this.internshipForm = this.formBuilder.group({
      projectName: ['Upcoming Internship'],
      category: ['', Validators.required],
      mentorsId: [''],
      periodFrom: [''],
      periodTo: [''],
      internshipStatus: ['', Validators.required],
      preInterviewTestList: [''],
      techQuesListId: [''],
      gitHubUrl: [''],
      trelloBoardUrl: [''],
      deployedAppUrl: [''],
      presentationUrl: ['']
    });
    this.internshipForm.get('internshipStatus').setValue(this.defaultStatus.name);
    this.categories = Object.entries(this.category).map(([key, value]) => ({name: key, value: value}));
    this.statuses = Object.entries(this.status).map(([key, value]) => ({name: key, value: value}));
    this.preInterviewTestList = Object.entries(this.preInterviewTest).map(([key, value]) =>
      ({name: key, value: value}));
    this.getAllTechQuestionLists();
    this.getAllMentors()
  }

  ngOnInit() {

  }

  public getAllTechQuestionLists() {
    this.techQuestionListService
      .getAllTechQuestionList()
      .subscribe((res) => {
        this.techQuestionsList = res;
      });
  }

  public getAllMentors() {
    this.userService
      .getAll()
      .subscribe((res: User[]) => {
        this.mentors = res;
      });
  }

  get form() {
    return this.internshipForm.controls;
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];

    if (file) {
      const formData = new FormData();

      formData.append("file", file);

      this.internshipService.uploadPresentation(formData).subscribe((val) => {
        this.internshipForm.controls["presentationUrl"].patchValue(val.data);
      });
    }
  }


  createInternship() {
    console.log(this.internshipForm.value)
    if (this.internshipForm.valid) {
      this.internshipService.createInternship(this.internshipForm.value).subscribe({
        next: () => {
          alert("Internship created successfully");
          this.internshipForm.reset();
          this.dialogRef.close('save');
        },
        error: () => {
          alert("Error while creating internship")
        }
      })
    }
  }


}
