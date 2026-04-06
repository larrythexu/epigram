package io.github.larrythexu.epigramsite.epigrams;

import org.springframework.stereotype.Service;

@Service
public class EpigramMapper {

  public EpigramDTO toEpigramDTO(Epigram epigram) {
    return new EpigramDTO(epigram.getMessage());
  }

  public Epigram toEpigram(EpigramDTO epigramDTO) {
    return new Epigram(epigramDTO.epigram());
  }
}
