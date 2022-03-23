export class User {
  id?: string;
  username?: string;
  name?: string;
  surname?: string;
  email: string;
  password: string;
  jobPosition: JPosition;
  token?: string;
  role: Role;
}

export enum JPosition {
  JAVA = "Java",
  C_SHARP = "C#",
  PCL = "PLC",
  ANGULAR = "ANGULAR",
  REACT = "REACT",
}

export enum Role {
  ADMIN,
  HR,
}
