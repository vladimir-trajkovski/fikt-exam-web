package mk.edu.uklo.fikt.fiktexamweb.DTO;

import mk.edu.uklo.fikt.fiktexamweb.model.Question;

import java.util.List;

public class TopicQuestions {

    private String topicName;
    private List<Question> questions;

    public String getTopicName(){
        return this.topicName;
    }

    public void setTopicName(String topicName){
        this.topicName = topicName;
    }

    public List<Question> getQuestions(){
        return this.questions;
    }

    public void setQuestions(List<Question> questions){
        this.questions = questions;
    }
}