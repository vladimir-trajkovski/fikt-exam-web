package mk.edu.uklo.fikt.fiktexamweb.util;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import mk.edu.uklo.fikt.fiktexamweb.DTO.TeacherSubjects;
import mk.edu.uklo.fikt.fiktexamweb.DTO.TestSubject;
import mk.edu.uklo.fikt.fiktexamweb.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


@Service
public class SubjectService {
	
	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private CombinationRepository combinationRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private TestRepository testRepository;
	
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

	public List<TestSubject> testSubjects(){
		List<Test> testIds = testRepository.findAll();
		List<TestSubject> testSubjects = new ArrayList<>();
		for (int i = 0; i<testIds.size(); i++){
			Combination combination = combinationRepository.findByTestId(testIds.get(i).getId()).get(0);
			Question question = questionRepository.findById(combination.getQuestionId()).get();
			Topic topic = topicRepository.findById(question.getTopicId()).get();
			Subject subject = subjectRepository.findById(topic.getSubjectId()).get();
			TestSubject testSubject = new TestSubject();
			testSubject.setSubjectName(subject.getName());
			testSubject.setTestId(testIds.get(i).getId());
			testSubjects.add(testSubject);
		}
		return testSubjects;
	}

}
