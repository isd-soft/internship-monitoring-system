import { Component, Inject, OnInit } from "@angular/core";
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from "@angular/forms";
import {
  MAT_DIALOG_DATA,
  MatDialog,
  MatDialogRef,
} from "@angular/material/dialog";
import {
  Category,
  PreInterviewTest,
  Status,
} from "../../shared/model/internship";
import { User } from "../../shared/model/user";
import { TechQuestionList } from "../../shared/model/techQuestionList";
import { TechQuestionListService } from "../../shared/service/tech-question-list.service";
import { InternshipService } from "../../shared/service/internship.service";
import { AccountService } from "../../shared/service/account.service";
import { MatSnackBar } from "@angular/material/snack-bar";

@Component({
  selector: "app-add-internship",
  templateUrl: "./add-internship.component.html",
  styleUrls: ["./add-internship.component.css"],
})
export class AddInternshipComponent implements OnInit {
  internshipForm: FormGroup = new FormGroup({});
  actionBtn: string = "Save";
  status = Status;
  statuses: { name: string; value: string }[];
  defaultStatus = { name: Status.NEW, value: "New" };
  category = Category;
  categories: { name: string; value: string }[];
  mentors: User[];
  preInterviewTest = PreInterviewTest;
  preInterviewTestList: { name: string; value: string }[];
  techQuestionsList: TechQuestionList[];
  validationErrors: {} | null = {};
  techQuestionListStatusError = false;
  intent: string = "Add New Internship";

  constructor(
    private _snackBar: MatSnackBar,
    public dialog: MatDialog,
    private formBuilder: FormBuilder,
    private techQuestionListService: TechQuestionListService,
    private internshipService: InternshipService,
    private userService: AccountService,
    @Inject(MAT_DIALOG_DATA) public editData: any,
    private dialogRef: MatDialogRef<AddInternshipComponent>
  ) {}

  get form() {
    return this.internshipForm.controls;
  }

  ngOnInit() {
    this.internshipForm = this.formBuilder.group({
      id: [null],
      projectName: ["Upcoming Internship", Validators.required],
      category: ["", Validators.required],
      mentorsId: [null],
      periodFrom: [null],
      periodTo: [null],
      internshipStatus: [null, Validators.required],
      preInterviewTestList: [null],
      techQuesListId: [null],
      gitHubUrl: [null],
      trelloBoardUrl: [null],
      deployedAppUrl: [null],
      presentationUrl: [null],
    });
    this.internshipForm
      .get("internshipStatus")
      .setValue(this.defaultStatus.name);
    this.categories = Object.entries(this.category).map(([key, value]) => ({
      name: key,
      value: value,
    }));
    this.statuses = Object.entries(this.status).map(([key, value]) => ({
      name: key,
      value: value,
    }));
    this.preInterviewTestList = Object.entries(this.preInterviewTest).map(
      ([key, value]) => ({ name: key, value: value })
    );
    this.getAllTechQuestionLists();
    this.getAllMentors();
    if (this.editData) {
      console.log(this.editData);
      this.intent = "Edit Internship";
      this.actionBtn = "Edit";
      this.internshipForm.controls["id"].setValue(this.editData.id);
      this.internshipForm.controls["projectName"].setValue(
        this.editData.projectName
      );
      this.internshipForm.controls["category"].setValue(this.editData.category);
      this.internshipForm.controls["mentorsId"].setValue(
        this.editData.mentorsId
      );
      this.internshipForm.controls["periodFrom"].setValue(
        this.editData.periodFrom
      );
      this.internshipForm.controls["periodTo"].setValue(this.editData.periodTo);
      this.internshipForm.controls["internshipStatus"].setValue(
        this.editData.internshipStatus
      );
      this.internshipForm.controls["preInterviewTestList"].setValue(
        this.editData.preInterviewTestList
      );
      this.internshipForm.controls["techQuesListId"].setValue(
        this.editData.techQuesListId
      );
      this.internshipForm.controls["gitHubUrl"].setValue(
        this.editData.gitHubUrl
      );
      this.internshipForm.controls["trelloBoardUrl"].setValue(
        this.editData.trelloBoardUrl
      );
      this.internshipForm.controls["deployedAppUrl"].setValue(
        this.editData.deployedAppUrl
      );
      this.internshipForm.controls["presentationUrl"].setValue(
        this.editData.presentationUrl
      );
    }
  }

   getAllTechQuestionLists() {
    this.techQuestionListService.getAllTechQuestionList().subscribe((res) => {
      this.techQuestionsList = res;
    });
  }

   getAllMentors() {
    this.userService.getAll().subscribe((res: User[]) => {
      this.mentors = res;
    });
  }

  getErrorMessage() {
    if (this.internshipForm.get("projectName").hasError("required")) {
      return "You must enter Project Name";
    }
    if (this.internshipForm.get("internshipStatus").hasError("required")) {
      return "You must enter Internship Status";
    }
    return this.internshipForm.get("category").hasError("required")
      ? "You must enter Internship Category"
      : "";
  }

  isFieldValid(field: string) {
    return (
      !this.form.internshipForm.get(field).valid &&
      this.internshipForm.get(field).touched
    );
  };


  createInternship() {
    if (!this.editData) {
      if (this.internshipForm.valid) {
        this.internshipService
          .createInternship(this.internshipForm.value)
          .subscribe({
            next: (res) => {
              console.log(res);
              // TODO - custom notification message
              this._snackBar.open("Internship was created successfully", "OK");
              this.internshipForm.reset();
              this.dialogRef.close("save");
            },
            error: () => {
              alert("Error while creating internship");
            },
          });
      } else {
        this.validateAllFormFields(this.internshipForm);
      }
    } else {
      this.updateInternship();
    }
  }

  updateInternship() {
    this.internshipService
      .updateInternship(this.editData.id, this.internshipForm.value)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.internshipForm.reset();
          this.dialogRef.close("update");
        },
        error: () => {
          alert("Error while updating internship");
        },
      });
  }

  reset() {
    this.internshipForm.reset();
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

  private validateAllFormFields(internshipForm: FormGroup) {
    Object.keys(internshipForm.controls).forEach((field) => {
      const control = internshipForm.get(field);
      if (control instanceof FormControl) {
        control.markAsTouched({ onlySelf: true });
      } else if (control instanceof FormGroup) {
        this.validateAllFormFields(control);
      }
    });
  }
}
