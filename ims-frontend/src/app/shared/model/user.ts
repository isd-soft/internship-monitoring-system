export class User {
  id?: string;
  username?: string;
  name?: string;
  surname?: string;
  email: string;
  password: string;
  job_position: Job_Position;
  token?: string;
  role: Role;
}

export enum Job_Position {
  Java,
  C,
  PCL,
  ANGULAR,
  REACT,
}

export enum Role {
  ADMIN,
  HR,
}
