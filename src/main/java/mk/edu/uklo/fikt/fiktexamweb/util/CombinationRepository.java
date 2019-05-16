package mk.edu.uklo.fikt.fiktexamweb.util;

import mk.edu.uklo.fikt.fiktexamweb.model.Combination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CombinationRepository extends JpaRepository<Combination, Integer> {
    List<Combination> findByTestId(int testId);

}
