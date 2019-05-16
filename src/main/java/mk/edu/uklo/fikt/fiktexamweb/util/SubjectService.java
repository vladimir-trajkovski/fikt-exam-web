package mk.edu.uklo.fikt.fiktexamweb.util;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import mk.edu.uklo.fikt.fiktexamweb.model.Subject;


@Service
public class SubjectService {
	
	@Autowired
	SubjectRepository subjectRepository;

	@Autowired
	UserService userService;
	
	//get all subjects
	public List<Subject> getSubjects(){
		return subjectRepository.findAll();
	}
	
	//add a new subject
	public Subject createSubject(@Valid Subject subject) {
		return subjectRepository.save(subject);
		}


	public List<Subject> getByTeacher(int teacherId){
		return subjectRepository.findByTeacherId(teacherId);
	}


	//get subject by id
	public Subject getById(long id){
		return subjectRepository.findById(id).get();
	}

}
