package mk.edu.uklo.fikt.fiktexamweb.controller;

import java.util.List;

import javax.validation.Valid;

import mk.edu.uklo.fikt.fiktexamweb.DTO.TopicQuestions;
import mk.edu.uklo.fikt.fiktexamweb.util.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mk.edu.uklo.fikt.fiktexamweb.model.Topic;
import mk.edu.uklo.fikt.fiktexamweb.util.TopicService;

@RestController
@RequestMapping({"/topic"})
public class TopicController {

	@Autowired
	TopicService topicService;

	@Autowired
	QuestionService questionService;
	
	@GetMapping({"/get"})
	public List<Topic> getAllTopics(){
		return topicService.getTopics();
	}

	
	@PostMapping({"/post"})
	public Topic addTopic(@Valid @RequestBody Topic topic) {
		return topicService.addTopic(topic);
	}

	//FINAL - get topic and all his questions
	@GetMapping("/get/topicquestins/{id}")
	public TopicQuestions getTopicAndQuestions(@PathVariable(name = "id") long id){
		TopicQuestions topicQuestions = new TopicQuestions();
		topicQuestions.setTopicName(topicService.getById(id).getName());
		topicQuestions.setQuestions(questionService.getByTopic(id));
		return topicQuestions;
	}

}
