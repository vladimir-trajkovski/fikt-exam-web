package mk.edu.uklo.fikt.fiktexamweb.controller;

import java.util.List;

import javax.validation.Valid;

import mk.edu.uklo.fikt.fiktexamweb.DTO.TopicQuestions;
import mk.edu.uklo.fikt.fiktexamweb.model.Question;
import mk.edu.uklo.fikt.fiktexamweb.util.QuestionBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mk.edu.uklo.fikt.fiktexamweb.model.Subject;
import mk.edu.uklo.fikt.fiktexamweb.model.Topic;
import mk.edu.uklo.fikt.fiktexamweb.util.TopicBL;

@RestController
@RequestMapping({"/topic"})
public class TopicController {

	@Autowired
	TopicBL topicBl;

	@Autowired
	QuestionBL questionBL;
	
	@GetMapping({"/get"})
	public List<Topic> getAllTopics(){
		return topicBl.getTopics();
	}

	
	@PostMapping({"/post"})
	public Topic addTopic(@Valid @RequestBody Topic topic) {
		return topicBl.addTopic(topic);
	}

	//FINAL - get topic and all his questions
	@GetMapping("/get/topicquestins/{id}")
	public TopicQuestions getTopicAndQuestions(@PathVariable(name = "id") long id){
		TopicQuestions topicQuestions = new TopicQuestions();
		topicQuestions.setTopicName(topicBl.getById(id).getName());
		topicQuestions.setQuestions(questionBL.getByTopic(id));
		return topicQuestions;
	}

}
