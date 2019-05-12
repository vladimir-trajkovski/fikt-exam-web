package mk.edu.uklo.fikt.fiktexamweb.controller;

import java.util.List;

import mk.edu.uklo.fikt.fiktexamweb.DTO.QuestionAnswers;
import mk.edu.uklo.fikt.fiktexamweb.DTO.QuestionOptions;
import mk.edu.uklo.fikt.fiktexamweb.model.Answer;
import mk.edu.uklo.fikt.fiktexamweb.util.OptionsService;
import mk.edu.uklo.fikt.fiktexamweb.util.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import mk.edu.uklo.fikt.fiktexamweb.model.Question;

@Controller
@RequestMapping({"/question"})
@SessionAttributes
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@Autowired
	OptionsService optionsService;

	@GetMapping("/questionform")
	public String getQuestionForm(Model model){
		getQuestionOptions(12, model);
		return "question";
	}
	
	@GetMapping({"/get"})
	public List<Question> getAllQuestions(){
		return questionService.getQuestions();
	}

	
//	@PostMapping({"/post"})
//	public Question addQuestion(@Valid @RequestBody Question question) {
//		return questionService.addQuestion(question);
//	}

	@GetMapping("/get/questionoptions/{id}")
	public QuestionOptions getQuestionOptions(@PathVariable(name = "id") long id, Model model){
		QuestionOptions questionOptions = new QuestionOptions();
		id=15;
		questionOptions.setQuestionText(questionService.getById(id).getText());
		questionOptions.setOptions(optionsService.getOptionsForQuestion((int) id));
		model.addAttribute("questionOptions", questionOptions);
		return questionOptions;
	}

	@PostMapping("/post")
	public String addQuestion(long topicId, String level, String questionText, List<Answer> answers, Model model){
		model.addAttribute("topicId", topicId);
		model.addAttribute("level", level);
		model.addAttribute("answers", answers);
		model.addAttribute("questionText", questionText);
		Question question = new Question();
		question.setId(0);
		question.setText(questionText);
		question.setLevel(level);
		question.setTopicId(topicId);

		return "question";
	}
}
