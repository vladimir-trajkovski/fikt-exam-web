package mk.edu.uklo.fikt.fiktexamweb.util;

import org.springframework.stereotype.Repository;

import mk.edu.uklo.fikt.fiktexamweb.model.Topic;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long>{

}
