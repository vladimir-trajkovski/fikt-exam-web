package mk.edu.uklo.fikt.fiktexamweb.util;

import java.util.List;

import mk.edu.uklo.fikt.fiktexamweb.DTO.SubjectTopics;
import mk.edu.uklo.fikt.fiktexamweb.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.edu.uklo.fikt.fiktexamweb.model.Topic;



@Service
public class TopicService {
	
	@Autowired
	TopicRepository topicRepository;

	@Autowired
	SubjectService subjectService;

	//get topic by Id
	public Topic getById(int id){
		return topicRepository.findById(id).get();
	}
	
	//add a new topic
	public Topic addTopic(Topic topic) {
		return topicRepository.save(topic);
	}
	
	//get all topics for 1 subject
	public SubjectTopics getTopicsForSubject(int subjectId){
		SubjectTopics subjectTopics = new SubjectTopics();
		Subject subject = subjectService.getById(subjectId);
		subjectTopics.setId(subjectId);
		subjectTopics.setSubjectName(subject.getName());
		subjectTopics.setTopics(topicRepository.findBySubjectId(subjectId));
		return subjectTopics;
	}

	public List<Topic> getTopicsForOneSubject(int subjectId){
		return topicRepository.findBySubjectId(subjectId);
	}
}
