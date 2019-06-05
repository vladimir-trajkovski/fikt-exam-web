package mk.edu.uklo.fikt.fiktexamweb.DTO;

import mk.edu.uklo.fikt.fiktexamweb.model.Options;
import mk.edu.uklo.fikt.fiktexamweb.model.Topic;

import java.util.List;

public class QuestionAnswers {

    private String questionText;
    private String level;
    private Topic topic;
    private List<Options> options;

    public QuestionAnswers(){

    }

    public QuestionAnswers(QuestionAnswers questionAnswers) {
        this.questionText = questionAnswers.questionText;
        this.level = questionAnswers.level;
        this.topic = questionAnswers.topic;
        this.options = questionAnswers.options;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<Options> getOptions() {
        return options;
    }

    public void setOptions(List<Options> options) {
        this.options = options;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
