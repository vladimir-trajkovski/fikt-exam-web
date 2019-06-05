package mk.edu.uklo.fikt.fiktexamweb.DTO;

import mk.edu.uklo.fikt.fiktexamweb.model.Options;

import java.util.List;

public class OptionsDTO {

    private List<Options> optionsList;

    public List<Options> getOptionsList() {
        return optionsList;
    }

    public void setOptionsList(List<Options> optionsList) {
        this.optionsList = optionsList;
    }
}
