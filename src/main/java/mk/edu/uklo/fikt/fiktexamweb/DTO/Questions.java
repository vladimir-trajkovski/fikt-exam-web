package mk.edu.uklo.fikt.fiktexamweb.DTO;

import mk.edu.uklo.fikt.fiktexamweb.model.Question;

import java.util.List;

public class Questions {
    private List<Question> questionList;

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
