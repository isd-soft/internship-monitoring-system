import { Component, OnInit } from "@angular/core";
import { TechQuestionList } from "../shared/model/techQuestionList";
import { TechQuestionListService } from "../shared/service/techQuestionList.service";
import { ModalDismissReasons, NgbModal } from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: "app-techquestionlist",
  templateUrl: "./techquestionlist.component.html",
  styleUrls: ["./techquestionlist.component.css"],
})
export class TechquestionlistComponent implements OnInit {
  techquestionlist: TechQuestionList[];
  closeResult: string;

  constructor(
    private techQuestionListService: TechQuestionListService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {}

  onSubmit() {}

  public getAllTechQuestionLists() {
    this.techQuestionListService.getAlltechQuestionList().subscribe({
      next: (result) => {
        console.log(result);
        this.techquestionlist = result;
      },
      error: (err) => console.log("Error"),
    });
  }

  open({ content }: { content: any }) {
    this.modalService
      .open(content, { ariaLabelledBy: "modal-basic-title" })
      .result.then(
        (result) => {
          this.closeResult = `Closed with: ${result}`;
        },
        (reason) => {
          this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        }
      );
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return "by pressing ESC";
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return "by clicking on a backdrop";
    } else {
      return `with: ${reason}`;
    }
  }
}
