package mk.edu.uklo.fikt.fiktexamweb.controller;

import mk.edu.uklo.fikt.fiktexamweb.DTO.*;
import mk.edu.uklo.fikt.fiktexamweb.model.Options;
import mk.edu.uklo.fikt.fiktexamweb.model.Question;
import mk.edu.uklo.fikt.fiktexamweb.model.Subject;
import mk.edu.uklo.fikt.fiktexamweb.model.Test;
import mk.edu.uklo.fikt.fiktexamweb.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private OptionsService optionsService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;

    //get all questions and his options for 1 test
    @PostMapping("/get")
    public String getQuestionsFromTest(Test test, Model model){
        model.addAttribute("test", test);
        List<QuestionOptions> lista = new ArrayList<>();
        List<Integer> prasanjaId = testService.getCombinationForTest(test.getId());
        List<Question> prasanja = questionService.getWithListOfIds(prasanjaId);
        for (int i = 0; i<prasanja.size();i++){
            List<Options> odgovoriLista = optionsService.getOptionsForQuestion(prasanja.get(i).getId());
            QuestionOptions questionOptions = new QuestionOptions();
            questionOptions.setQuestionText(prasanja.get(i).getText());
            questionOptions.setOptions(odgovoriLista);
            lista.add(questionOptions);
        }
        model.addAttribute("lista", lista);
        return "question";
    }

    @PostMapping("/post")
    public String showCreateTestForm(Subject subject1, Model model){
        Subject subject = subjectService.getByName(subject1.getName());
        model.addAttribute("subject", subject);
        List<Question> questions = questionService.getAllQuestionsForSubject(subject.getId());
        model.addAttribute("questionsa", questions);
        Questions allQuestions = new Questions();
        model.addAttribute("questions", allQuestions);
        return "createTest";
    }

    @PostMapping("/add-combination")
    public String addTest(Questions questions, Model model){
        model.addAttribute("questions", questions);
        testService.questionsForTest(questions.getQuestionList());
        TeacherSubjects teacherSubjects = subjectService.getByTeacher(userService.getIdByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("teacherSubjects", teacherSubjects);
        Subject subject = new Subject();
        model.addAttribute("subject", subject);
        List<Options> options = new ArrayList<>();
        model.addAttribute("options", options);
        return "P2";
    }

    @PostMapping("/submit")
    public String submitAnswers(OptionsDTO options, Model model){
        model.addAttribute("options", options.getOptionsList());
        Result result = testService.results(options.getOptionsList());
        model.addAttribute("result", result);
        return "result";
    }
}
