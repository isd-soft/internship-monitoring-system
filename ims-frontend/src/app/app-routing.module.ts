import { RouterModule, Routes } from "@angular/router";
import { NgModule } from "@angular/core";
import { LoginComponent } from "./account/login/login.component";
import { RegisterComponent } from "./account/register/register.component";
import {InternshipComponent} from "./intership/internship.component";
import {AddCandidateComponent} from "./pages/add-candidate/add-candidate.component";
import {AuthGuard} from "./shared/common/auth.guard";

const routes: Routes = [
  {
    path: "",
    loadChildren: () =>
      import("./pages/main-page/main-page.module").then(
        (m) => m.MainPageModule
      ),
  },
  //{ path: "register", component: RegisterComponent },
  { path: "candidate-add", component: AddCandidateComponent, canActivate: [AuthGuard] },
  { path: "login", component: LoginComponent },
  { path: "internships", component: InternshipComponent, canActivate: [AuthGuard] },
  {path: "**", redirectTo: "login"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
