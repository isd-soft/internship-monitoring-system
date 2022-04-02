export class Feedback {
  id?: string;
  feedback: string;
  candidateId: string;
  userId: string;
}

export class FeedbackWithUsername {
  id?: string;
  feedback: string;
  candidateId: string;
  userId: string
  userName: string;
}
