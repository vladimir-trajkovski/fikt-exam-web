package mk.edu.uklo.fikt.fiktexamweb.controller;

import java.security.Principal;
import java.util.List;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;

import mk.edu.uklo.fikt.fiktexamweb.DTO.SubjectTopics;
import mk.edu.uklo.fikt.fiktexamweb.DTO.TeacherSubjects;
import mk.edu.uklo.fikt.fiktexamweb.model.Topic;
import mk.edu.uklo.fikt.fiktexamweb.model.User;
import mk.edu.uklo.fikt.fiktexamweb.util.TopicService;
import mk.edu.uklo.fikt.fiktexamweb.util.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import mk.edu.uklo.fikt.fiktexamweb.model.Subject;
import mk.edu.uklo.fikt.fiktexamweb.util.SubjectService;

@Controller
@RequestMapping({"/subject"})
@SessionAttributes
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;

	@Autowired
	private TopicService topicService;

	@Autowired
	private UserService userService;

	//go to P2 screen which contains all subjects for authencicated teacher
	@GetMapping("/subjectscreen")
	public String getSubjects(Model model){
		getByTeacher(userService.getIdByUsername(
				SecurityContextHolder.getContext().getAuthentication().getName()),
				model);
		Subject subject = new Subject();
		model.addAttribute("subject", subject);
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
	@GetMapping({"/get/byteacher"})
	public TeacherSubjects getByTeacher(int teacherId, Model model){
		TeacherSubjects teacherSubjects = subjectService.getByTeacher(teacherId);
		model.addAttribute("teacherSubjects", teacherSubjects);
		return teacherSubjects;
	}

	//TODO -- add a new subject
	@PostMapping({"/post"})
	public Subject addSubject(@Valid @RequestBody Subject subject) {
		return subjectService.createSubject(subject);
	}


	//Get subject and his topics
	@GetMapping("/get/subjecttopics/{id}")
	public SubjectTopics getSubjectAndTopics(@PathVariable(name = "id") int id){
		Subject subject = subjectService.getById(id);
		List<Topic> topics = topicService.getTopicsForOneSubject((int) id);
		SubjectTopics subjectTopics = new SubjectTopics();
		subjectTopics.setSubjectName(subject.getName());
		subjectTopics.setTopics(topics);
		return subjectTopics;
	}
}
