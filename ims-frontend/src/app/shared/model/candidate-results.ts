import {PreInterviewTestMark} from "./pre-interview-test-mark";

export class CandidateResults {
  candidateId: string;
  englishMark: number;
  softSkillMark: number;
  practiceMark: number;
  techMark: number;
  averageInterviewMark: number;
  averagePreInterviewMark: number;
  testMarks: PreInterviewTestMark[];
}
