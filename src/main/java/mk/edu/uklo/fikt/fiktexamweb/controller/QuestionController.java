package mk.edu.uklo.fikt.fiktexamweb.controller;

import java.util.List;

import mk.edu.uklo.fikt.fiktexamweb.DTO.QuestionAnswers;
import mk.edu.uklo.fikt.fiktexamweb.DTO.QuestionOptions;
import mk.edu.uklo.fikt.fiktexamweb.model.Answer;
import mk.edu.uklo.fikt.fiktexamweb.model.Topic;
import mk.edu.uklo.fikt.fiktexamweb.util.OptionsService;
import mk.edu.uklo.fikt.fiktexamweb.util.QuestionService;
import mk.edu.uklo.fikt.fiktexamweb.util.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import mk.edu.uklo.fikt.fiktexamweb.model.Question;

import javax.jws.WebParam;
import javax.validation.Valid;

@Controller
@RequestMapping({"/question"})
@SessionAttributes
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@Autowired
	OptionsService optionsService;

	@Autowired
	TopicService topicService;

	//go to the Screen with questions for 1 topic
	@GetMapping("/question/{id}")
	public String getAddQuestionForm(@PathVariable int id, Model model){
		getQuestionsForTopic(id, model);
		return "P4";
	}

	//get all questions for 1 topic
	@GetMapping("/get/question/{id}")
	public List<Question> getQuestionsForTopic(@PathVariable int id, Model model){
		List<Question> questions = questionService.getByTopic(id);
		model.addAttribute("questions", questions);
		return questions;
	}

	//TODO -- needs to be retested -- go to screen with questions for testing
	@GetMapping("/questionform")
	public String getQuestionForm(Model model){
		getQuestionOptions(12, model);
		return "question";
	}

	//go to screen for adding questions
	@GetMapping("/addquestionform/{id}")
	public String getQuestionAddForm(Model model, @PathVariable(name = "id") int id){
		List<Topic> topics = topicService.getTopicsForSubject(id);
		model.addAttribute("topics" ,topics);
		QuestionAnswers questionAnswers = new QuestionAnswers();
		model.addAttribute("questionAnswers", questionAnswers);
		return "addQuestionScreen";
	}

	//get all questions. Maybe needs to be used for android
	@GetMapping({"/get"})
	public List<Question> getAllQuestions(){
		return questionService.getQuestions();
	}

	//TODO -- needs to be retested to check if will be used
	@GetMapping("/get/questionoptions/{id}")
	public QuestionOptions getQuestionOptions(@PathVariable(name = "id") long id, Model model){
		QuestionOptions questionOptions = new QuestionOptions();
		id=15;
		questionOptions.setQuestionText(questionService.getById(id).getText());
		questionOptions.setOptions(optionsService.getOptionsForQuestion((int) id));
		model.addAttribute("questionOptions", questionOptions);
		return questionOptions;
	}

	//add a new questions and a list of options
	@PostMapping("/addquestion")
	public String addQuestionAndOptions(
			@RequestBody @Valid @ModelAttribute(value = "questionAnswers") QuestionAnswers questionAnswers,
			Model model){
		List<Topic> topics = topicService.getTopicsForSubject(3);
		model.addAttribute("topics", topics);
		model.addAttribute("questionAnswers", questionAnswers);
		questionService.addQuestionAndOptions(questionAnswers);
		return "addQuestionScreen";
	}
}
