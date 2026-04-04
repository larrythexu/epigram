package io.github.larrythexu.epigramsite.epigrams;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Epigram {

  @Id @GeneratedValue() private Long id;

  @Column(unique = true, nullable = false)
  private String message;

  public Epigram(String message) {
    this.message = message;
  }
}
