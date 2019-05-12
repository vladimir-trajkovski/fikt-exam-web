package mk.edu.uklo.fikt.fiktexamweb.util;

import org.springframework.stereotype.Repository;

import mk.edu.uklo.fikt.fiktexamweb.model.Question;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{
    List<Question> findByTopicId(long subjectId);

}
