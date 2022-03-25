import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {AppComponent} from "./app.component";
import {FooterComponent} from "./shared/footer/footer.component";
import {AppRoutingModule} from "./app-routing.module";
import {LoginComponent} from "./account/login/login.component";
import {RegisterComponent} from "./account/register/register.component";
import {HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatButton, MatButtonModule} from "@angular/material/button";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {SideMenuComponent} from "./shared/side-menu/side-menu.component";
import {MatToolbarModule} from "@angular/material/toolbar";
import {ReviewsComponent} from "./pages/reviews/reviews.component";
import {MainPageModule} from "./pages/main-page/main-page.module";
import {MainPageComponent} from "./pages/main-page/main-page.component";
import {InternshipComponent} from "./intership/internship.component";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {MatDialogModule} from '@angular/material/dialog';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';

import {AddCandidateComponent} from './pages/add-candidate/add-candidate.component';
import {MatSelectModule} from '@angular/material/select';
import {CommonModule} from "@angular/common";
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import {MatIconModule} from "@angular/material/icon";
import {AddInternshipComponent} from './intership/add-internship/add-internship.component';
import {TechquestionlistComponent} from "./techquestionlist/techquestionlist.component";
import {AuthGuard} from "./shared/common/auth.guard";
import {FeedbackComponent} from "./pages/feedback/feedback.component";

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
    MatDialogModule,
    MatGridListModule,
    MatDatepickerModule,
    MatNativeDateModule

  ],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  bootstrap: [AppComponent],
})
export class AppModule {
}
