package mk.edu.uklo.fikt.fiktexamweb.util;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import mk.edu.uklo.fikt.fiktexamweb.model.Question;
import mk.edu.uklo.fikt.fiktexamweb.model.Topic;

@Service
public class QuestionBL {
	
	@Autowired
	QuestionRepository questionRepository;
	TopicBL topicBl;
	
	//get all questions
	public List<Question> getQuestions(){
		return questionRepository.findAll();
	}
	
	//add a new question
	public Question addQuestion(Question question) {
		return questionRepository.save(question);
	}
	
	//get all questions for 1 topic
	public List<Question> getByTopic(long id){
		
		return questionRepository.findByTopicId(id);
	}
	
	//get a question by text
	public Question getByText(String text) {
		Question example = new Question();
		
		example.setText(text);
		return questionRepository.findAll(Example.of(example)).get(0);
	}
	
	//get a question by id
	public Question getById(long id){
		return questionRepository.findById(id).get();
	}
}
