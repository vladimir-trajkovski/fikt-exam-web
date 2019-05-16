package mk.edu.uklo.fikt.fiktexamweb.controller;

import java.util.List;

import javax.jws.WebParam;
import javax.validation.Valid;

import mk.edu.uklo.fikt.fiktexamweb.DTO.SubjectTopics;
import mk.edu.uklo.fikt.fiktexamweb.model.Topic;
import mk.edu.uklo.fikt.fiktexamweb.util.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import mk.edu.uklo.fikt.fiktexamweb.model.Subject;
import mk.edu.uklo.fikt.fiktexamweb.util.SubjectService;

@Controller
@RequestMapping({"/subject"})
public class SubjectController {
	
	@Autowired
	SubjectService subjectService;

	@Autowired
	TopicService topicService;

	//go to P2 screen which contains all subjects for authencicated teacher
	@GetMapping("/subjectscreen/{id}")
	public String getSubjects(@PathVariable int id, Model model){
		getByTeacher(id, model);
		return "P2";
	}

	//go to S2 screen which contains all subjects for student to select on which subject is the test
	@GetMapping("/studentsubjectscreen")
	public String getStudentSubjectScreen(Model model){
		getAllSubjects(model);
		return "S2";
	}

	//get all subjects -- needed for S2 screen
	@GetMapping({"/get"})
	public List<Subject> getAllSubjects(Model model){
		List<Subject> subjects = subjectService.getSubjects();
		model.addAttribute("subjects", subjects);
		return subjects;
	}

	//get all subjects for 1 teacher
	@GetMapping({"/get/byteacher/{id}"})
	public List<Subject> getByTeacher(@PathVariable int teacherId, Model model){
		List<Subject> subjects = subjectService.getByTeacher(teacherId);
		model.addAttribute("subjects", subjects);
		return subjects;
	}

	//TODO -- add a new subject
	@PostMapping({"/post"})
	public Subject addSubject(@Valid @RequestBody Subject subject) {
		return subjectService.createSubject(subject);
	}


	//Get subject and his topics
	@GetMapping("/get/subjecttopics/{id}")
	public SubjectTopics getSubjectAndTopics(@PathVariable(name = "id") long id){
		Subject subject = subjectService.getById(id);
		List<Topic> topics = topicService.getTopicsForSubject((int) id);
		SubjectTopics subjectTopics = new SubjectTopics();
		subjectTopics.setSubjectName(subject.getName());
		subjectTopics.setTopics(topics);
		return subjectTopics;
	}
}
