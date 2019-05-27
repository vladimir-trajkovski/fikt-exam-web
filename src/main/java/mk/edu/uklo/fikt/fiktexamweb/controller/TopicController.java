package mk.edu.uklo.fikt.fiktexamweb.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import mk.edu.uklo.fikt.fiktexamweb.DTO.SubjectTopics;
import mk.edu.uklo.fikt.fiktexamweb.DTO.TopicQuestions;
import mk.edu.uklo.fikt.fiktexamweb.model.Subject;
import mk.edu.uklo.fikt.fiktexamweb.util.QuestionService;
import mk.edu.uklo.fikt.fiktexamweb.util.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import mk.edu.uklo.fikt.fiktexamweb.model.Topic;
import mk.edu.uklo.fikt.fiktexamweb.util.TopicService;

@Controller
@RequestMapping({"/topic"})
@SessionAttributes
public class TopicController {

	@Autowired
	TopicService topicService;

	@Autowired
	QuestionService questionService;

	@Autowired
	SubjectService subjectService;

	@PostMapping("/get/topicsforsubject")
	public String getTopicsForSubjectPost(@Valid @ModelAttribute(value = "subject") Subject subject, Model model){
		model.addAttribute("subject", subject);
		getTopicsForSubject(subject.getId(), model);
		Topic topic = new Topic();
		model.addAttribute("topic", topic);
		return "P3";
	}

	//get screen with topics for selected subject
	@GetMapping("/topic/{id}")
	public String topicScreen(@PathVariable int id, Model model){
		getTopicsForSubject(id, model);
		return "P3";
	}

	//get topics for selected subject
	@GetMapping("/get/topics")
	public SubjectTopics getTopicsForSubject(int id, Model model){
		SubjectTopics subjectTopics = topicService.getTopicsForSubject(id);
		model.addAttribute("subjectTopics", subjectTopics);
		return subjectTopics;
	}

	//TODO -- add a new topic
	@PostMapping({"/post"})
	public Topic addTopic(@Valid @RequestBody Topic topic) {
		return topicService.addTopic(topic);
	}

	//get topic and all his questions
	@GetMapping("/get/topicquestins/{id}")
	public TopicQuestions getTopicAndQuestions(@PathVariable(name = "id") int id){
		TopicQuestions topicQuestions = new TopicQuestions();
		topicQuestions.setTopicName(topicService.getById(id).getName());
		topicQuestions.setQuestions(questionService.getByTopic(id).getQuestions());
		return topicQuestions;
	}
}
