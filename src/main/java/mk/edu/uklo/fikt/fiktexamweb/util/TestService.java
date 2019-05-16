package mk.edu.uklo.fikt.fiktexamweb.util;

import mk.edu.uklo.fikt.fiktexamweb.model.Combination;
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

    public List<Integer> getCombinationForTest(int id){
        List<Combination> lista = combinationRepository.findByTestId(id);
        List<Integer> prasanja = new ArrayList<>();
        for (int i=0; i<lista.size();i++){
            prasanja.add(lista.get(i).getQuestionId());
        }
        return prasanja;
    }

}
