export class Candidate{
  id?: number ;
  name: string;
  surname: string;
  email: string;
  cv: string;
  comment: string;
  status: Status;
  mark: number;
}
 export enum Status {
  NEW,
  ACCEPTED,
  ON_HOLD,
  REJECTED
}
