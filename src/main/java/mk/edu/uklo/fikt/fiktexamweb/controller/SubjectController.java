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
import mk.edu.uklo.fikt.fiktexamweb.model.User;
import mk.edu.uklo.fikt.fiktexamweb.util.SubjectBL;

@RestController
@RequestMapping({"/subject"})
public class SubjectController {
	
	@Autowired
	SubjectBL subjectBl;
	
	@GetMapping({"/get"})
	public List<Subject> getAllSubjects(){
		return subjectBl.getSubjects();
	}
	
	@GetMapping({"/get/byteacher"})
	public List<Subject> getSubjectsByTeacher(String teacherUsername){
		return subjectBl.getSubjectsByProfessor(teacherUsername);
	}
	
	@PostMapping({"/post"})
	public Subject addSubject(@Valid @RequestBody Subject subject) {
		return subjectBl.createSubject(subject);
	}
	
}
