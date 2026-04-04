package io.github.larrythexu.epigramsite.epigrams;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EpigramRepository extends JpaRepository<Epigram, Long> {

  @Query(value = "SELECT * from epigram ORDER BY random() LIMIT 1", nativeQuery = true)
  Optional<Epigram> findRandom();
}
