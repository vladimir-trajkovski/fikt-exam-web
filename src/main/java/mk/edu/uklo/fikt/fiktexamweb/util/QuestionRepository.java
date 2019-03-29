package mk.edu.uklo.fikt.fiktexamweb.util;

import org.springframework.stereotype.Repository;

import mk.edu.uklo.fikt.fiktexamweb.model.Question;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{

}
