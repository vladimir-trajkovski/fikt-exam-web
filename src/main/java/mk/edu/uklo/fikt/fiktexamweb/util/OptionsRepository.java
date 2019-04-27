package mk.edu.uklo.fikt.fiktexamweb.util;

import org.springframework.stereotype.Repository;

import mk.edu.uklo.fikt.fiktexamweb.model.Options;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface OptionsRepository extends JpaRepository<Options, Long>{
    List<Options> findByQuestionId(long questionId);
}
