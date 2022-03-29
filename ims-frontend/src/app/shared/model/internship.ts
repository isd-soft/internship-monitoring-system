import {User} from "./user";
import {Candidate} from "./candidate";

export class Internship {
  id: string;
  projectName: string;
  category: string;
  mentorsId: string []; // mentors id
  periodFrom: Date;
  periodTo: Date;
  internshipStatus: Status;
  preInterviewTestList: string[]; // review PreinterviewTest class
  techQuesListId: string;
  gitHubUrl: string;
  trelloBoardUrl: string;
  deployedAppUrl: string; // review project download
  presentationUrl: string; // review presentation download
  candidatesId: string []
}

export enum Status {
  NEW = "New",
  INTERVIEWING = "Interviewing",
  IN_PROGRESS = "In process",
  DONE = "Done"
}

export enum Category{
  JAVA="Java",
  C_SHARP = "C#",
  PLC = "PLC"
}
export enum PreInterviewTest{
  ENGLISH ="English",
  LOGIC = "Logic",
  JAVA = "Java",
  SQL = "SQL"
}
