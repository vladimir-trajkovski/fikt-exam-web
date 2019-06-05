package mk.edu.uklo.fikt.fiktexamweb.controller;

import javax.validation.Valid;

import mk.edu.uklo.fikt.fiktexamweb.DTO.SubjectTopics;
import mk.edu.uklo.fikt.fiktexamweb.model.Subject;
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
	SubjectService subjectService;

	@PostMapping("/get/topicsforsubject")
	public String getTopicsForSubjectPost(@Valid @ModelAttribute(value = "subject") Subject subject, Model model){
		model.addAttribute("subject", subject);
		getTopicsForSubject(subject.getId(), model);
		Topic topic = new Topic();
		model.addAttribute("topic", topic);
		return "P3";
	}

	//get topics for selected subject
	@GetMapping("/get/topics")
	public SubjectTopics getTopicsForSubject(int id, Model model){
		SubjectTopics subjectTopics = topicService.getTopicsForSubject(id);
		model.addAttribute("subjectTopics", subjectTopics);
		return subjectTopics;
	}

	//add a new topic
	@PostMapping({"/post"})
	public String addTopic(@Valid Topic topic, Model model) {
		model.addAttribute("topic", topic);
		topic.setId(0);
		topicService.addTopic(topic);
		return getTopicsForSubjectPost(subjectService.getById(topic.getSubjectId()), model);
	}
}
