import {User} from "./user";

export class Internship {
  id: string;
  projectName: string;
  category: string;
  mentors: User []; // review users class
  periodFrom: Date;
  periodTo: Date;
  internshipStatus: Status;
  preInterviewTestList: string[]; // review PreinterviewTest class
  techQuesListName: string;
  gitHubUrl: string;
  trelloBoardUrl: string;
  deployedAppUrl: string; // review project download
  presentationUrl: string; // review presentation download
}

export enum Status {
  NEW,
  INTERVIEWING,
  IN_PROGRESS,
  DONE
}
