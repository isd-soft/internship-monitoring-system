import {PreInterviewTestMark} from "./pre-interview-test-mark";

export class CandidateResults {
  candidateId: string;
  englishMark: number;
  softSkillMark: number;
  practiceMark: number;
  averageMark: number;
  averagePreInterviewTestMark: number;
  testMarks: PreInterviewTestMark[];
}
