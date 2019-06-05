package mk.edu.uklo.fikt.fiktexamweb.DTO;

import mk.edu.uklo.fikt.fiktexamweb.model.Question;

import java.util.List;

public class TopicQuestionsOptions {

    private int id;

    private String topicName;
    private List<QuestionOptions> questionOptions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopicName(){
        return this.topicName;
    }

    public void setTopicName(String topicName){
        this.topicName = topicName;
    }

    public List<QuestionOptions> getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(List<QuestionOptions> questionOptions) {
        this.questionOptions = questionOptions;
    }
}
