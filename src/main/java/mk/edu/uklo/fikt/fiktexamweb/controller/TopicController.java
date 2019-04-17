package mk.edu.uklo.fikt.fiktexamweb.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mk.edu.uklo.fikt.fiktexamweb.model.Subject;
import mk.edu.uklo.fikt.fiktexamweb.model.Topic;
import mk.edu.uklo.fikt.fiktexamweb.util.TopicBL;

@RestController
@RequestMapping({"/topic"})
public class TopicController {

	@Autowired
	TopicBL topicBl;
	
	@GetMapping({"/get"})
	public List<Topic> getAllTopics(){
		return topicBl.getTopics();
	}
	
	@GetMapping({"/get/bysubject"})
	public List<Topic> getBySubject(String subjectName){
		return topicBl.getTopicsForSubject(subjectName);
	}
	
	@PostMapping({"/post"})
	public Topic addTopic(@Valid @RequestBody Topic topic) {
		return topicBl.addTopic(topic);
	}
	
	
	
}
