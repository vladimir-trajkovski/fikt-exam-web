package mk.edu.uklo.fikt.fiktexamweb.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mk.edu.uklo.fikt.fiktexamweb.model.Question;
import mk.edu.uklo.fikt.fiktexamweb.model.Topic;
import mk.edu.uklo.fikt.fiktexamweb.util.QuestionBL;

@RestController
@RequestMapping({"/question"})
public class QuestionController {

	@Autowired
	QuestionBL questionBl;
	
	@GetMapping({"/get"})
	public List<Question> getAllQuestions(){
		return questionBl.getQuestions();
	}
	
	@GetMapping({"/get/bytopic"})
	public List<Question> getByTopic(String topicName){
		return questionBl.getByTopic(topicName);
	}
	
	@PostMapping({"/post"})
	public Question addQuestion(@Valid @RequestBody Question question) {
		return questionBl.addQuestion(question);
	}
	
}
