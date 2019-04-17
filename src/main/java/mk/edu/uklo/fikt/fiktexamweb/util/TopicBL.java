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
	
	//add a new topic
	public Topic addTopic(Topic topic) {
		return topicRepository.save(topic);
	}
	
	//get all topics for 1 subject
	public List<Topic> getTopicsForSubject(String subjectName){
		Subject subject = subjectBl.getByName(subjectName).get(0);
		
		Topic example = new Topic();
		
		example.setSubjectId(subject.getId());
		
		return topicRepository.findAll(Example.of(example));
	}
	
	//get topic by name
	public Topic getByName(String name) {
		Topic example = new Topic();
		
		example.setName(name);
		
		return topicRepository.findAll(Example.of(example)).get(0);
	}
	
}
