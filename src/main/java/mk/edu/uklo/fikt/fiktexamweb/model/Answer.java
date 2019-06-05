package mk.edu.uklo.fikt.fiktexamweb.model;

import javax.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "testingId")
    private int testingId;

    @Column(name = "optionId")
    private int optionId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTestingId() {
        return testingId;
    }

    public void setTestingId(int testingId) {
        this.testingId = testingId;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }
}
