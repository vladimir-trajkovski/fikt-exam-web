package mk.edu.uklo.fikt.fiktexamweb.util;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import mk.edu.uklo.fikt.fiktexamweb.model.Subject;
import mk.edu.uklo.fikt.fiktexamweb.model.User;
import mk.edu.uklo.fikt.fiktexamweb.model.Topic;



@Service
public class TopicBL {
	
	@Autowired
	TopicRepository topicRepository;
	SubjectBL subjectBl;
	
	//get all topics
	public List<Topic> getTopics(){
		return topicRepository.findAll();
	}

	//get topic by Id
	public Topic getById(long id){
		return topicRepository.findById(id).get();
	}
	
	//add a new topic
	public Topic addTopic(Topic topic) {
		return topicRepository.save(topic);
	}
	
	//get all topics for 1 subject
	public List<Topic> getTopicsForSubject(long subjectId){

		return topicRepository.findBySubjectId(subjectId);
	}
	
}
