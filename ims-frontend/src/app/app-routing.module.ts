import { RouterModule, Routes } from "@angular/router";
import { NgModule } from "@angular/core";
import { LoginComponent } from "./account/login/login.component";
import { RegisterComponent } from "./account/register/register.component";
import {InternshipComponent} from "./intership/internship.component";

const routes: Routes = [
  {
    path: "",
    loadChildren: () =>
      import("./pages/main-page/main-page.module").then(
        (m) => m.MainPageModule
      ),
  },
  //{ path: "register", component: RegisterComponent },
  { path: "login", component: LoginComponent },
  { path: "internships", component: InternshipComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
