package mk.edu.uklo.fikt.fiktexamweb.util;

import org.springframework.stereotype.Repository;

import mk.edu.uklo.fikt.fiktexamweb.model.Options;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OptionsRepository extends JpaRepository<Options, Long>{

}
