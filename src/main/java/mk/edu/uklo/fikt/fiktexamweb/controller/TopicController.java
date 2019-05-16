package mk.edu.uklo.fikt.fiktexamweb.controller;

import java.util.List;

import javax.validation.Valid;

import mk.edu.uklo.fikt.fiktexamweb.DTO.TopicQuestions;
import mk.edu.uklo.fikt.fiktexamweb.util.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import mk.edu.uklo.fikt.fiktexamweb.model.Topic;
import mk.edu.uklo.fikt.fiktexamweb.util.TopicService;

@Controller
@RequestMapping({"/topic"})
public class TopicController {

	@Autowired
	TopicService topicService;

	@Autowired
	QuestionService questionService;

	//get screen with topics for selected subject
	@GetMapping("/topic/{id}")
	public String topicScreen(@PathVariable int id, Model model){
		getTopicsForSubject(id, model);
		return "P3";
	}

	//get topics for selected subject
	@GetMapping("/get/topics/{id}")
	public List<Topic> getTopicsForSubject(@PathVariable int id, Model model){
		List<Topic> topics = topicService.getTopicsForSubject(id);
		model.addAttribute("topics", topics);
		return topics;
	}

	//TODO -- add a new topic
	@PostMapping({"/post"})
	public Topic addTopic(@Valid @RequestBody Topic topic) {
		return topicService.addTopic(topic);
	}

	//get topic and all his questions
	@GetMapping("/get/topicquestins/{id}")
	public TopicQuestions getTopicAndQuestions(@PathVariable(name = "id") long id){
		TopicQuestions topicQuestions = new TopicQuestions();
		topicQuestions.setTopicName(topicService.getById(id).getName());
		topicQuestions.setQuestions(questionService.getByTopic(id));
		return topicQuestions;
	}
}
