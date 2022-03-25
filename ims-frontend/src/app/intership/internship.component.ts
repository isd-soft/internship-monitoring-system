import {Component, AfterViewInit, ViewChild} from '@angular/core';
import {InternshipService} from "../shared/service/internship.service";
import {Internship, Status} from "../shared/model/internship";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {MatDialog} from '@angular/material/dialog';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AddInternshipComponent} from "./add-internship/add-internship.component";

@Component({
  selector: 'app-internship',
  templateUrl: './internship.component.html',
  styleUrls: ['./internship.component.css']
})
export class InternshipComponent implements AfterViewInit {
  internships: Internship[];
  displayedColumns: string[] = ['position', 'projectName', 'category', 'periodFrom', 'periodTo', 'mentors',
    'internshipStatus', 'gitHubUrl', 'trelloBoardUrl', 'deployedAppUrl', 'presentationUrl', 'actions'];
  dataSource: MatTableDataSource<Internship>;
  closeResult: string;


  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private internshipService: InternshipService,
              private modalService: NgbModal,
              private dialog: MatDialog) {
    this.getAllInternships();

  }

  ngAfterViewInit(): void {
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

  openDialog() {
    const dialogRef = this.dialog.open(AddInternshipComponent, {
      width: '75%'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  // open({content}: { content: any }) {
  //   this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
  //     this.closeResult = `Closed with: ${result}`;
  //   }, (reason) => {
  //     this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
  //   });
  // }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }


  viewInternship(internship: Internship) {


  }

  editInternship(row: any) {

  }

  deleteInternship(row: any) {

  }
}

