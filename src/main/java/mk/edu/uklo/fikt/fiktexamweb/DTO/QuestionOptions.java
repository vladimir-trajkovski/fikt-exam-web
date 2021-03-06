package mk.edu.uklo.fikt.fiktexamweb.DTO;

import mk.edu.uklo.fikt.fiktexamweb.model.Options;

import java.util.List;

public class QuestionOptions {

    private int id;

    private String questionText;
    private List<Options> options;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText(){
        return this.questionText;
    }

    public void setQuestionText(String questionText){
        this.questionText = questionText;
    }

    public List<Options> getOptions(){
        return this.options;
    }

    public void setOptions(List<Options> options){
        this.options = options;
    }
}
