package mk.edu.uklo.fikt.fiktexamweb.controller;

import mk.edu.uklo.fikt.fiktexamweb.DTO.QuestionOptions;
import mk.edu.uklo.fikt.fiktexamweb.model.Options;
import mk.edu.uklo.fikt.fiktexamweb.model.Question;
import mk.edu.uklo.fikt.fiktexamweb.util.OptionsService;
import mk.edu.uklo.fikt.fiktexamweb.util.QuestionService;
import mk.edu.uklo.fikt.fiktexamweb.util.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private OptionsService optionsService;

    //get all questions and his options for 1 test
    @GetMapping("/get/{id}")
    public String getQuestionsFromTest(@PathVariable(name = "id") int id, Model model){
        List<QuestionOptions> lista = new ArrayList<>();
        List<Integer> prasanjaId = testService.getCombinationForTest(id);
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
}
