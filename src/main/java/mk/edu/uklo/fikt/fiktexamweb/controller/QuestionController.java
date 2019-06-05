package mk.edu.uklo.fikt.fiktexamweb.controller;

import java.util.List;

import mk.edu.uklo.fikt.fiktexamweb.DTO.QuestionAnswers;
import mk.edu.uklo.fikt.fiktexamweb.DTO.QuestionOptions;
import mk.edu.uklo.fikt.fiktexamweb.DTO.TopicQuestionsOptions;
import mk.edu.uklo.fikt.fiktexamweb.model.*;
import mk.edu.uklo.fikt.fiktexamweb.util.OptionsService;
import mk.edu.uklo.fikt.fiktexamweb.util.QuestionService;
import mk.edu.uklo.fikt.fiktexamweb.util.SubjectService;
import mk.edu.uklo.fikt.fiktexamweb.util.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

	@Autowired
	SubjectService subjectService;

	//go to the Screen with questions for 1 topic
	@PostMapping("/question")
	public String getAddQuestionForm(Topic topic, Model model){
		model.addAttribute("topic", topic);
		getQuestionsForTopic(topic.getId(), model);
		Question question = new Question();
		model.addAttribute("question", question);
		return "P4";
	}

	//get all questions for 1 topic
	@GetMapping("/get/question")
	public TopicQuestionsOptions getQuestionsForTopic(int id, Model model){
		TopicQuestionsOptions topicQuestionsOptions = questionService.getByTopic(id);
		model.addAttribute("topicQuestionsOptions", topicQuestionsOptions);
		return topicQuestionsOptions;
	}

	//TODO -- needs to be retested -- go to screen with questions for testing
	@GetMapping("/questionform")
	public String getQuestionForm(Model model){
		getQuestionOptions(12, model);
		return "question";
	}

	//go to screen for adding questions
	@PostMapping("/addquestionform")
	public String getQuestionAddForm(Topic topic, Model model){
		model.addAttribute("topicq", topic);
		Topic newTopic = topicService.getById(topic.getId());
		Subject subject = subjectService.getById(newTopic.getSubjectId());
		model.addAttribute("newtopic", newTopic);
		List<Topic> topics = topicService.getTopicsForOneSubject(subject.getId());
		model.addAttribute("topics" ,topics);
		QuestionAnswers questionAnswers = new QuestionAnswers();
		List<Options> options = optionsService.getOptionsForQuestion(1);
		questionAnswers.setOptions(options);
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
	public QuestionOptions getQuestionOptions(@PathVariable(name = "id") int id, Model model){
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
		List<Topic> topics = topicService.getTopicsForOneSubject(3);
		model.addAttribute("topics", topics);
		model.addAttribute("questionAnswers", questionAnswers);
		questionService.addQuestionAndOptions(questionAnswers);
		return getAddQuestionForm(topicService.getById(questionAnswers.getTopic().getId()), model);
	}
}
