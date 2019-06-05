package mk.edu.uklo.fikt.fiktexamweb.util;

import mk.edu.uklo.fikt.fiktexamweb.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Integer> {

}
