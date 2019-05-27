package mk.edu.uklo.fikt.fiktexamweb.util;

import java.util.List;

import javax.validation.Valid;

import mk.edu.uklo.fikt.fiktexamweb.DTO.TeacherSubjects;
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


	public TeacherSubjects getByTeacher(int teacherId){
		TeacherSubjects teacherSubjects = new TeacherSubjects();
		teacherSubjects.setId(teacherId);
		teacherSubjects.setTeacherName(userService.userRepository.findById(teacherId)
				.get().getImePrezime());
		teacherSubjects.setSubjectList(subjectRepository.findByTeacherId(teacherId));

		return teacherSubjects;
	}

	public Subject getByName(String name){
		return subjectRepository.findByName(name);
	}


	//get subject by id
	public Subject getById(int id){
		return subjectRepository.findById(id).get();
	}

}
