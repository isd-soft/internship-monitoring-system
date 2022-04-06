import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { AppComponent } from "./app.component";
import { FooterComponent } from "./shared/footer/footer.component";
import { AppRoutingModule } from "./app-routing.module";
import { LoginComponent } from "./account/login/login.component";
import { RegisterComponent } from "./account/register/register.component";
import { HttpClientModule } from "@angular/common/http";
import { RouterModule } from "@angular/router";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatButtonModule } from "@angular/material/button";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { SideMenuComponent } from "./shared/side-menu/side-menu.component";
import { MatToolbarModule } from "@angular/material/toolbar";
import { ReviewsComponent } from "./pages/reviews/reviews.component";
import { MainPageModule } from "./pages/main-page/main-page.module";
import { MainPageComponent } from "./pages/main-page/main-page.component";
import { InternshipComponent } from "./intership/internship.component";
import { MatGridListModule } from "@angular/material/grid-list";
import { MatDatepickerModule } from "@angular/material/datepicker";
import { MatNativeDateModule } from "@angular/material/core";
import { AddCandidateComponent } from "./pages/add-candidate/add-candidate.component";
import { MatSelectModule } from "@angular/material/select";
import { CommonModule } from "@angular/common";
import { MatTableModule } from "@angular/material/table";
import { MatPaginatorModule } from "@angular/material/paginator";
import { MatSortModule } from "@angular/material/sort";
import { MatIconModule } from "@angular/material/icon";
import { AddInternshipComponent } from "./intership/add-internship/add-internship.component";
import { TechquestionlistComponent } from "./techquestionlist/techquestionlist.component";
import { TechquestionComponent } from "./techquestion/techquestion.component";
import { TechMarkComponent } from "./tech-mark/tech-mark.component";
import { CandidateEvaluationComponent } from "./candidate-evaluation/candidate-evaluation.component";
import { FeedbackComponent } from "./pages/feedback/feedback.component";
import { CandidatesListComponent } from "./pages/candidates-list/candidates-list.component";
import { CandidatesTableComponent } from "./candidates-table/candidates-table.component";
import { MarksModalComponent } from "./candidates-table/marks-modal/marks-modal.component";
import { MatDialogModule } from "@angular/material/dialog";
import { MatDividerModule } from "@angular/material/divider";
import { MatMenuModule } from "@angular/material/menu";
import {
  MAT_SNACK_BAR_DEFAULT_OPTIONS,
  MatSnackBarModule,
} from "@angular/material/snack-bar";
import { QuestionComponent } from "./question/question.component";
import { QuestionListModalComponent } from "./question/question-list-modal/question-list-modal.component";
import { TechQuestionModalComponent } from "./question/tech-question-modal/tech-question-modal.component";
import { EditTechQuestionModalComponent } from "./question/edit-tech-question-modal/edit-tech-question-modal.component";
import { ConfirmDialogComponent } from './intership/confirm-dialog/confirm-dialog.component';
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatTabsModule} from '@angular/material/tabs';
import { InternshipResultsComponent } from './intership/internship-results/internship-results.component';
import {ConfirmCandidateDialogComponent} from "./pages/candidates-list/confirm-dialog/confirm-dialog.component";

@NgModule({
  declarations: [
    MainPageComponent,
    AppComponent,
    LoginComponent,
    FooterComponent,
    RegisterComponent,
    SideMenuComponent,
    ReviewsComponent,
    InternshipComponent,
    AddCandidateComponent,
    AddInternshipComponent,
    TechquestionlistComponent,
    FeedbackComponent,
    TechquestionComponent,
    TechMarkComponent,
    CandidateEvaluationComponent,
    CandidatesTableComponent,
    MarksModalComponent,
    CandidatesListComponent,
    QuestionComponent,
    QuestionListModalComponent,
    TechQuestionModalComponent,
    EditTechQuestionModalComponent,
    CandidatesListComponent,
    ConfirmDialogComponent,
    ConfirmCandidateDialogComponent,
    InternshipResultsComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        BrowserAnimationsModule,
        RouterModule,
        MatButtonModule,
        ReactiveFormsModule,
        MatInputModule,
        MatFormFieldModule,
        MatToolbarModule,
        MainPageModule,
        FormsModule,
        MatSelectModule,
        CommonModule,
        MatTableModule,
        MatPaginatorModule,
        MatSortModule,
        MatIconModule,
        MatDialogModule,
        MatGridListModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatDividerModule,
        MatMenuModule,
        MatSnackBarModule,
        MatCheckboxModule,
        MatTabsModule
    ],
  providers: [
    { provide: MAT_SNACK_BAR_DEFAULT_OPTIONS, useValue: { duration: 1500 } },
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  bootstrap: [AppComponent],
})
export class AppModule {}
