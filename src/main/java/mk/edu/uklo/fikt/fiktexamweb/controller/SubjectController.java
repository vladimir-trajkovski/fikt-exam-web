package mk.edu.uklo.fikt.fiktexamweb.controller;

import java.util.List;

import javax.validation.Valid;

import mk.edu.uklo.fikt.fiktexamweb.DTO.TeacherSubjects;
import mk.edu.uklo.fikt.fiktexamweb.DTO.TestSubject;
import mk.edu.uklo.fikt.fiktexamweb.model.Test;
import mk.edu.uklo.fikt.fiktexamweb.util.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
	private UserService userService;

	//go to P2 screen which contains all subjects for authencicated teacher
	@GetMapping("/subject-screen")
	public String getSubjects(Model model){
		getByTeacher(userService.getIdByUsername(
				SecurityContextHolder.getContext().getAuthentication().getName()),
				model);
		Subject subject = new Subject();
		model.addAttribute("subject", subject);
		return "P2";
	}

	//go to S2 screen which contains all subjects for student to select on which subject is the test
	@GetMapping("/student-subject-screen")
	public String getStudentSubjectScreen(Model model){
		List<TestSubject> testSubjects = subjectService.testSubjects();
		model.addAttribute("testSubjects", testSubjects);
		Test test = new Test();
		model.addAttribute("test", test);
		return "S2";
	}

	//get all subjects for 1 teacher
	@GetMapping({"/get/byteacher"})
	public TeacherSubjects getByTeacher(int teacherId, Model model){
		TeacherSubjects teacherSubjects = subjectService.getByTeacher(teacherId);
		model.addAttribute("teacherSubjects", teacherSubjects);
		return teacherSubjects;
	}

	//add a new subject
	@PostMapping({"/post"})
	public String addSubject(@Valid Subject subject, Model model) {
		model.addAttribute("subject", subject);
		subject.setId(0);
		subject.setTeacherId(userService
				.getIdByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
		subjectService.createSubject(subject);
		return getSubjects(model);
	}
}
