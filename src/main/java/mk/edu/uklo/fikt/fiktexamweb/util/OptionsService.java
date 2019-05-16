package mk.edu.uklo.fikt.fiktexamweb.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.edu.uklo.fikt.fiktexamweb.model.Options;

@Service
public class OptionsService {
	
	@Autowired
	private OptionsRepository optionsRepository;
	
	//get all options for 1 question
	public List<Options> getOptionsForQuestion(int questionId){
		return optionsRepository.findByQuestionId(questionId);
	}
	
}
