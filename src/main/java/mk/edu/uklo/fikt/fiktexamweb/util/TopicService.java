package mk.edu.uklo.fikt.fiktexamweb.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.edu.uklo.fikt.fiktexamweb.model.Topic;



@Service
public class TopicService {
	
	@Autowired
	TopicRepository topicRepository;
	SubjectService subjectService;
	
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
