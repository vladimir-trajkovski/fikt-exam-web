package mk.edu.uklo.fikt.fiktexamweb.controller;

import java.util.List;

import javax.validation.Valid;

import mk.edu.uklo.fikt.fiktexamweb.DTO.SubjectTopics;
import mk.edu.uklo.fikt.fiktexamweb.model.Topic;
import mk.edu.uklo.fikt.fiktexamweb.util.TopicBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import mk.edu.uklo.fikt.fiktexamweb.model.Subject;
import mk.edu.uklo.fikt.fiktexamweb.model.User;
import mk.edu.uklo.fikt.fiktexamweb.util.SubjectBL;

@RestController
@RequestMapping({"/subject"})
public class SubjectController {
	
	@Autowired
	SubjectBL subjectBl;

	@Autowired
	TopicBL topicBL;
	
	@PreAuthorize("hasAnyRole('Admin')")
	@GetMapping({"/get"})
	public List<Subject> getAllSubjects(){
		return subjectBl.getSubjects();
	}
	
//	@GetMapping({"/get/byteacher"})
//	public List<Subject> getSubjectsByTeacher(String teacherUsername){
//		return subjectBl.getSubjectsByProfessor(teacherUsername);
//	}

	//needs to be deleted
	@GetMapping("/get/byname")
	public List<Subject> getBySubjectName(long name){
		return subjectBl.getSubjectByName(name);
	}
	
	@PostMapping({"/post"})
	public Subject addSubject(@Valid @RequestBody Subject subject) {
		return subjectBl.createSubject(subject);
	}


	//FINAL -- Get subject and his topics
	@GetMapping("/get/subjecttopics/{id}")
	public SubjectTopics getSubjectAndTopics(@PathVariable(name = "id") long id){
		Subject subject = subjectBl.getById(id);
		List<Topic> topics = topicBL.getTopicsForSubject(id);
		SubjectTopics subjectTopics = new SubjectTopics();
		subjectTopics.setSubjectName(subject.getName());
		subjectTopics.setTopics(topics);
		return subjectTopics;
	}
}
