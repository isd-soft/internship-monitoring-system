import { RouterModule, Routes } from "@angular/router";
import { NgModule } from "@angular/core";
import { LoginComponent } from "./account/login/login.component";
import { InternshipComponent } from "./intership/internship.component";
import { AddCandidateComponent } from "./pages/add-candidate/add-candidate.component";
import { TechquestionlistComponent } from "./techquestionlist/techquestionlist.component";
import { AuthGuard } from "./shared/common/auth.guard";
import { TechquestionComponent } from "./techquestion/techquestion.component";
import { TechMarkComponent } from "./tech-mark/tech-mark.component";
import { CandidateEvaluationComponent } from "./candidate-evaluation/candidate-evaluation.component";
import { FeedbackComponent } from "./pages/feedback/feedback.component";
import { CandidatesListComponent } from "./pages/candidates-list/candidates-list.component";
import { CandidatesTableComponent } from "./candidates-table/candidates-table.component";
import { QuestionComponent } from "./question/question.component";
import { AddInternshipComponent } from "./intership/add-internship/add-internship.component";

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
    path: "add",
    component: AddCandidateComponent,
    data: { intent: "add" },
    canActivate: [AuthGuard],
  },
  {
    path: "update",
    component: AddCandidateComponent,
    data: { intent: "update" },
    canActivate: [AuthGuard],
  },
  {
    path: "internship/:id/candidates",
    component: CandidatesListComponent,
    canActivate: [AuthGuard],
  },
  {
    path: "candidates",
    component: CandidatesListComponent,
    canActivate: [AuthGuard],
  },
  {
    path: "create",
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
    path: "add",
    component: AddInternshipComponent,
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
  {
    path: "questions",
    component: QuestionComponent,
    canActivate: [AuthGuard],
  },
  { path: "**", redirectTo: "login" },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
