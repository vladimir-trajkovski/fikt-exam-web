package mk.edu.uklo.fikt.fiktexamweb.util;

import org.springframework.stereotype.Repository;

import mk.edu.uklo.fikt.fiktexamweb.model.Subject;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{

}
