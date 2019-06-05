package mk.edu.uklo.fikt.fiktexamweb.util;

import mk.edu.uklo.fikt.fiktexamweb.DTO.Result;
import mk.edu.uklo.fikt.fiktexamweb.model.Combination;
import mk.edu.uklo.fikt.fiktexamweb.model.Options;
import mk.edu.uklo.fikt.fiktexamweb.model.Question;
import mk.edu.uklo.fikt.fiktexamweb.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TestingRepository testingRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private CombinationRepository combinationRepository;

    @Autowired
    private OptionsRepository optionsRepository;

    public List<Integer> getCombinationForTest(int id){
        List<Combination> lista = combinationRepository.findByTestId(id);
        List<Integer> prasanja = new ArrayList<>();
        for (int i=0; i<lista.size();i++){
            prasanja.add(lista.get(i).getQuestionId());
        }
        return prasanja;
    }

    public void questionsForTest(List<Question> questions){
        Test test = new Test();
        test.setId(0);
        testRepository.save(test);
        for (int i = 0; i<questions.size(); i++){
            Combination combination = new Combination();
            combination.setTestId(test.getId());
            combination.setQuestionId(questions.get(i).getId());
            combinationRepository.save(combination);
        }
    }

    public Result results(List<Options> options){
        List<Options> options1 = new ArrayList<>();
        int correct=0;
        int incorrect=0;
        for (int i = 0; i<options.size(); i++){
            Options option = optionsRepository.findById(options.get(i).getId()).get();
            options1.add(option);
            if(option.getIsTrue()){
                correct++;
            }
            else{
                incorrect++;
            }
        }
        Result result = new Result();
        result.setCorrect(correct);
        result.setIncorrect(incorrect);
        return result;
    }

}
