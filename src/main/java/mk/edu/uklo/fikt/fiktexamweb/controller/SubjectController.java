package mk.edu.uklo.fikt.fiktexamweb.controller;

import java.util.List;

import javax.validation.Valid;

import mk.edu.uklo.fikt.fiktexamweb.DTO.SubjectTopics;
import mk.edu.uklo.fikt.fiktexamweb.model.Topic;
import mk.edu.uklo.fikt.fiktexamweb.util.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mk.edu.uklo.fikt.fiktexamweb.model.Subject;
import mk.edu.uklo.fikt.fiktexamweb.util.SubjectService;

@RestController
@RequestMapping({"/subject"})
public class SubjectController {
	
	@Autowired
	SubjectService subjectService;

	@Autowired
	TopicService topicService;
	
	@GetMapping({"/get"})
	public List<Subject> getAllSubjects(){
		return subjectService.getSubjects();
	}
	
//	@GetMapping({"/get/byteacher"})
//	public List<Subject> getSubjectsByTeacher(String teacherUsername){
//		return subjectService.getSubjectsByProfessor(teacherUsername);
//	}

	//needs to be deleted
	@GetMapping("/get/byname")
	public List<Subject> getBySubjectName(long name){
		return subjectService.getSubjectByName(name);
	}
	
	@PostMapping({"/post"})
	public Subject addSubject(@Valid @RequestBody Subject subject) {
		return subjectService.createSubject(subject);
	}


	//FINAL -- Get subject and his topics
	@GetMapping("/get/subjecttopics/{id}")
	public SubjectTopics getSubjectAndTopics(@PathVariable(name = "id") long id){
		Subject subject = subjectService.getById(id);
		List<Topic> topics = topicService.getTopicsForSubject(id);
		SubjectTopics subjectTopics = new SubjectTopics();
		subjectTopics.setSubjectName(subject.getName());
		subjectTopics.setTopics(topics);
		return subjectTopics;
	}
}
