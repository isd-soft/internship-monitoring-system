import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { AppComponent } from "./app.component";
import { HeaderComponent } from "./shared/header/header.component";
import { FooterComponent } from "./shared/footer/footer.component";
import { AppRoutingModule } from "./app-routing.module";
import { LoginComponent } from "./account/login/login.component";
import { RegisterComponent } from "./account/register/register.component";
import { HttpClientModule } from "@angular/common/http";
import { RouterModule } from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { MatButton, MatButtonModule } from "@angular/material/button";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { InternshipComponent } from './intership/internship.component';
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";

import { AddCandidateComponent } from './pages/add-candidate/add-candidate.component';
import {MatSelectModule} from '@angular/material/select';
import {CommonModule} from "@angular/common";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    FooterComponent,
    RegisterComponent,
    InternshipComponent,
    AddCandidateComponent,
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
    NgbModule,
    FormsModule,
    MatSelectModule,
    CommonModule

  ],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  bootstrap: [AppComponent],
})
export class AppModule {}
