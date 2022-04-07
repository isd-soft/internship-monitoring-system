export class Internship {
  id: string;
  projectName: string;
  category: string;
  mentorsId: string []; // mentors id
  periodFrom: Date;
  periodTo: Date;
  internshipStatus: StatusEnum;
  preInterviewTestList: string[]; // review PreinterviewTest class
  techQuesListId: string;
  gitHubUrl: string;
  trelloBoardUrl: string;
  deployedAppUrl: string; // review project download
  presentationUrl: string; // review presentation download
  candidatesId: string []
}

export enum StatusEnum {
  NEW = "NEW",
  INTERVIEWING = "INTERVIEWING",
  IN_PROGRESS = "IN_PROGRESS",
  DONE = "DONE"
}

export enum Category {
  JAVA = "Java",
  C_SHARP = "C#",
  PLC = "PLC"
}

export enum PreInterviewTest {
  ENGLISH = "English",
  LOGIC = "Logic",
  JAVA = "Java",
  SQL = "SQL"
}
