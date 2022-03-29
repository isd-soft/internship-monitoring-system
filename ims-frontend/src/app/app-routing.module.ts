import { RouterModule } from "@angular/router";
import { NgModule } from "@angular/core";
import { LoginComponent } from "./account/login/login.component";
import { RegisterComponent } from "./account/register/register.component";
import { InternshipComponent } from "./intership/internship.component";
import { AddCandidateComponent } from "./pages/add-candidate/add-candidate.component";
import { TechquestionlistComponent } from "./techquestionlist/techquestionlist.component";
import { Routes, CanActivate } from "@angular/router";
import { AuthGuard } from "./shared/common/auth.guard";
import { TechquestionComponent } from "./techquestion/techquestion.component";
import { TechMarkComponent } from "./tech-mark/tech-mark.component";
import { CandidateEvaluationComponent } from "./candidate-evaluation/candidate-evaluation.component";
import { FeedbackComponent } from "./pages/feedback/feedback.component";
import { CandidatesTableComponent } from "./candidates-table/candidates-table.component";

const routes: Routes = [
  {
    path: "",
    loadChildren: () =>
      import("./pages/main-page/main-page.module").then(
        (m) => m.MainPageModule
      ),
  },
  //{ path: "register", component: RegisterComponent },
  {
    path: "candidate-add",
    component: AddCandidateComponent,
    data: { intent: "add" },
    canActivate: [AuthGuard],
  },
  {
    path: "candidate-update",
    component: AddCandidateComponent,
    data: { intent: "update" },
    canActivate: [AuthGuard],
  },
  {
    path: "feedback-create",
    component: FeedbackComponent,
    data: { intent: "add" },
    canActivate: [AuthGuard],
  },
  { path: "login", component: LoginComponent },
  {
    path: "internships",
    component: InternshipComponent,
    canActivate: [AuthGuard],
  },
  {
    path: "tql",
    component: TechquestionlistComponent,
    canActivate: [AuthGuard],
  },
  {
    path: "tq",
    component: TechquestionComponent,
    canActivate: [AuthGuard],
  },
  {
    path: "tm",
    component: TechMarkComponent,
    canActivate: [AuthGuard],
  },
  {
    path: "ce",
    component: CandidateEvaluationComponent,
    canActivate: [AuthGuard],
  },
  {
    path: "table",
    component: CandidatesTableComponent,
    canActivate: [AuthGuard],
  },
  { path: "**", redirectTo: "login" },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
