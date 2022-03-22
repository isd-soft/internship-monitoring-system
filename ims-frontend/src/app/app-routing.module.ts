import { RouterModule, Routes } from "@angular/router";
import { NgModule } from "@angular/core";
import { LoginComponent } from "./account/login/login.component";
import { RegisterComponent } from "./account/register/register.component";
import {InternshipComponent} from "./intership/internship.component";
import {AddCandidateComponent} from "./pages/add-candidate/add-candidate.component";

const routes: Routes = [
  {
    path: "",
    loadChildren: () =>
      import("./pages/main-page/main-page.module").then(
        (m) => m.MainPageModule
      ),
  },
  //{ path: "register", component: RegisterComponent },
  { path: "candidate-add", component: AddCandidateComponent },
  { path: "login", component: LoginComponent },
  { path: "internships", component: InternshipComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
