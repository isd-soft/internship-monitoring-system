import {Component, AfterViewInit, ViewChild} from '@angular/core';
import {InternshipService} from "../shared/service/internship.service";
import {Internship, Status} from "../shared/model/internship";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-internship',
  templateUrl: './internship.component.html',
  styleUrls: ['./internship.component.css']
})
export class InternshipComponent implements AfterViewInit  {
  internshipForm: FormGroup = new FormGroup({});
  internships: Internship[];
  displayedColumns: string[] = ['position', 'projectName', 'category', 'periodFrom','periodTo', 'mentors',
    'internshipStatus', 'gitHubUrl', 'trelloBoardUrl', 'deployedAppUrl', 'presentationUrl','actions'];
  dataSource: MatTableDataSource<Internship>;
  closeResult: string;
  status = Status;
  statusOptions: {name: string, value: number}[] = [];



  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private internshipService: InternshipService,
              private formBuilder: FormBuilder,
              private modalService: NgbModal) {

  }
  ngAfterViewInit(): void {
    this.getAllInternships();
    this.statusOptions = this.buildStatusOptions()
    setInterval( () => {
      console.dir(this.internshipForm.controls);
    }, 3000)

    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;

  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  onSubmit() {
  }
  public getAllInternships() {
    this.internshipService.getAllInternships().subscribe({
      next: result => {
        console.log(result);
        this.dataSource = new MatTableDataSource(result);
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
  private buildStatusOptions():  {name: string, value: number}[] {
    let options = [];
    for (const enumMember in this.status) {
      if (parseInt(enumMember, 10) >= 0) {
        options.push({name: this.status[enumMember], value: parseInt(enumMember, 10)})
      }
    }
    return options;
  }

  viewInternship(internship : Internship) {


  }

  editInternship(row:any) {

  }

  deleteInternship(row:any) {

  }
}

