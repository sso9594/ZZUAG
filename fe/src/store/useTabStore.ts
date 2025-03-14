import { create } from 'zustand';
import { devtools } from 'zustand/middleware';

export type TabType =
  | 'Question'
  | 'Review'
  | 'Request'
  | 'Setting'
  | 'CreateQuestion'
  | 'CreateReview'
  | null;
export type QuestionTabType = 'myQuestion' | 'questionDrafts' | null; //내 질문 - 임시보관 - 빈 선택지
export type ReviewTabType = 'myReview' | 'reviewDrafts' | null; //내 리뷰 - 임시보관 - 빈 선택지
export type DefaultTabType =
  | 'myQuestion'
  | 'questionDrafts'
  | 'myReview'
  | 'reviewDrafts'
  | 'reviewRequest'
  | 'myMemberInfo'
  | null;

interface TabState {
  tabType: TabType;
  questionTabType: QuestionTabType;
  reviewTabType: ReviewTabType;
  defaultTabType: DefaultTabType;
}

interface TabAction {
  setTabType: (tabType: TabType) => void;
  setQuestionTabType: (questionTabType: QuestionTabType) => void;
  setReviewTabType: (reviewTabType: ReviewTabType) => void;
  setDefaultTabType: (defaultTabType: DefaultTabType) => void;
}

const useTabStore = create<TabState & TabAction>()(
  devtools((set) => ({
    tabType: 'Question',
    questionTabType: null,
    reviewTabType: null,
    defaultTabType: null,

    setTabType: (tabType: TabType) => set({ tabType }),
    setQuestionTabType: (questionTabType: QuestionTabType) => set({ questionTabType }),
    setReviewTabType: (reviewTabType: ReviewTabType) => set({ reviewTabType }),
    setDefaultTabType: (defaultTabType: DefaultTabType) => set({ defaultTabType }),
  })),
);

export default useTabStore;
