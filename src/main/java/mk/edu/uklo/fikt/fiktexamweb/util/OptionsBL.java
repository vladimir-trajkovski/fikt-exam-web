package mk.edu.uklo.fikt.fiktexamweb.util;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import mk.edu.uklo.fikt.fiktexamweb.model.Question;
import mk.edu.uklo.fikt.fiktexamweb.model.Options;

@Service
public class OptionsBL {
	
	@Autowired
	OptionsRepository optionsRepository;
	QuestionBL questionBl;
	
	//get all options
	public List<Options> getOptions(){
		return optionsRepository.findAll();
	}
	
	//add a new option
	public Options addOption(Options option) {
		return optionsRepository.save(option);
	}
	
	//get all options for 1 question
	public List<Options> getOptionsForQuestion(long questionId){
		return optionsRepository.findByQuestionId(questionId);
	}
	
}
