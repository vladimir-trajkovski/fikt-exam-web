package mk.edu.uklo.fikt.fiktexamweb.controller;

import java.util.List;

import javax.validation.Valid;

import mk.edu.uklo.fikt.fiktexamweb.DTO.QuestionOptions;
import mk.edu.uklo.fikt.fiktexamweb.util.OptionsBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mk.edu.uklo.fikt.fiktexamweb.model.Question;
import mk.edu.uklo.fikt.fiktexamweb.model.Topic;
import mk.edu.uklo.fikt.fiktexamweb.util.QuestionBL;

@RestController
@RequestMapping({"/question"})
public class QuestionController {

	@Autowired
	QuestionBL questionBl;

	@Autowired
	OptionsBL optionsBL;
	
	@GetMapping({"/get"})
	public List<Question> getAllQuestions(){
		return questionBl.getQuestions();
	}

	
	@PostMapping({"/post"})
	public Question addQuestion(@Valid @RequestBody Question question) {
		return questionBl.addQuestion(question);
	}

	@GetMapping("/get/questionoptions/{id}")
	public QuestionOptions getQuestionOptions(@PathVariable(name = "id") long id){
		QuestionOptions questionOptions = new QuestionOptions();
		questionOptions.setQuestionText(questionBl.getById(id).getText());
		questionOptions.setOptions(optionsBL.getOptionsForQuestion(id));
		return questionOptions;
	}
}
