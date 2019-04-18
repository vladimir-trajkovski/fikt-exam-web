package mk.edu.uklo.fikt.fiktexamweb.util;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import mk.edu.uklo.fikt.fiktexamweb.model.Subject;
import mk.edu.uklo.fikt.fiktexamweb.model.User;



@Service
public class SubjectBL {
	
	@Autowired
	SubjectRepository subjectRepository;

	@Autowired
	UserBL userBl;
	
	//get all subjects
	public List<Subject> getSubjects(){
		return subjectRepository.findAll();
	}
	
	//add a new subject
	public Subject createSubject(@Valid Subject subject) {
		return subjectRepository.save(subject);
		}


	//get all subjects for 1 professor
//	public List<Subject> getSubjectsByProfessor(String profUsername){
//
//		User user = userBl.getByUsername(profUsername).get(0);
//
//		Subject example = new Subject();
//
//		example.setTeacherId(user.getId());
//
//		return subjectRepository.findAll(Example.of(example));
//	}
	
	//get by name
	public List<Subject> getByName(String name){
		Subject example = new Subject();
		
		example.setName(name);
		
		return subjectRepository.findAll(Example.of(example));
	}

	//get by name -- needs to be deleted
	public List<Subject> getSubjectByName(long name){
		return subjectRepository.findByTeacherId(name);
	}

	//get subject by id
	public Subject getById(long id){
		return subjectRepository.findById(id).get();
	}
	
	
}
