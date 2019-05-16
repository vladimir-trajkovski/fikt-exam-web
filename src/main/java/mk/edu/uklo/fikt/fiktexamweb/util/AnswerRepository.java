package mk.edu.uklo.fikt.fiktexamweb.util;

import mk.edu.uklo.fikt.fiktexamweb.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    
}
