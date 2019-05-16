package mk.edu.uklo.fikt.fiktexamweb.util;

import java.util.ArrayList;
import java.util.List;

import mk.edu.uklo.fikt.fiktexamweb.DTO.QuestionAnswers;
import mk.edu.uklo.fikt.fiktexamweb.model.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import mk.edu.uklo.fikt.fiktexamweb.model.Question;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private TopicService topicService;

	@Autowired
	private OptionsRepository optionsRepository;

	@Autowired
	private TestService testService;
	
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
		return questionRepository.findById((int) id).get();
	}

	public List<Question> getWithListOfIds(List<Integer> prasanja){
		List<Question> lista = new ArrayList<>();
		for(int i = 0; i<prasanja.size();i++){
			lista.add(questionRepository.findById(prasanja.get(i)).get());
		}
		return lista;
	}

	public QuestionAnswers addQuestionAndOptions(QuestionAnswers questionAnswers){
		Question question = new Question();
		question.setText(questionAnswers.getQuestionText());
		question.setTopicId(questionAnswers.getTopic().getId());
		question.setLevel(questionAnswers.getLevel());
		int questionId = questionRepository.save(question).getId();
		int answersSize = questionAnswers.getOptions().size();
		List<Options> options = questionAnswers.getOptions();
		for (int i = 0; i<answersSize; i++){
			options.get(i).setQuestionId((long) questionId);
			optionsRepository.save(options.get(i));
		}

		return questionAnswers;
	}

}
