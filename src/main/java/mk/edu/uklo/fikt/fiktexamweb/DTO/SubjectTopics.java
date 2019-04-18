package mk.edu.uklo.fikt.fiktexamweb.DTO;

import mk.edu.uklo.fikt.fiktexamweb.model.Topic;

import java.util.List;

public class SubjectTopics {

    private String subjectName;
    private List<Topic> topics;

    public String getSubjectName(){
        return this.subjectName;
    }

    public void setSubjectName(String subjectName){
        this.subjectName = subjectName;
    }

    public List<Topic> getTopics(){
        return this.topics;
    }

    public void setTopics(List<Topic> topics){
        this.topics = topics;
    }
}
