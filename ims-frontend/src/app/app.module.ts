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
import { MatButton, MatButtonModule } from "@angular/material/button";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { SideMenuComponent } from "./shared/side-menu/side-menu.component";
import { MatToolbarModule } from "@angular/material/toolbar";
import { ReviewsComponent } from "./pages/reviews/reviews.component";
import { MainPageModule } from "./pages/main-page/main-page.module";
import { MainPageComponent } from "./pages/main-page/main-page.component";
import { InternshipComponent } from "./intership/internship.component";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";

import { AddCandidateComponent } from './pages/add-candidate/add-candidate.component';
import {MatSelectModule} from '@angular/material/select';
import {CommonModule} from "@angular/common";
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import {MatIconModule} from "@angular/material/icon";
import { AddInternshipComponent } from './intership/add-internship/add-internship.component';
import { TechquestionlistComponent } from "./techquestionlist/techquestionlist.component";
import { AuthGuard } from "./shared/common/auth.guard";
import { FeedbackComponent } from './pages/feedback/feedback.component';
import { CandidatesListComponent } from './pages/candidates-list/candidates-list.component';
import { TechquestionComponent } from "./techquestion/techquestion.component";
import { TechMarkComponent } from './tech-mark/tech-mark.component';
import { CandidateEvaluationComponent } from './candidate-evaluation/candidate-evaluation.component';
import {MatMenuModule} from '@angular/material/menu';
import {MatDialogModule} from '@angular/material/dialog';

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
    CandidatesListComponent
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
    NgbModule,
    FormsModule,
    MatSelectModule,
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatIconModule,
    MatMenuModule,
    MatDialogModule

  ],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  bootstrap: [AppComponent],
})
export class AppModule {}
