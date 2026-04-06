package io.github.larrythexu.epigramsite.epigrams;

import io.github.larrythexu.epigramsite.domain.exceptions.NoEpigramsFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class EpigramService {

  private final EpigramRepository epigramRepository;
  private final EpigramMapper epigramMapper;

  public EpigramDTO getEpigram(Long id) {
    Epigram epigram = epigramRepository.findById(id).orElseThrow();

    return epigramMapper.toEpigramDTO(epigram);
  }

  public List<EpigramDTO> getAllEpigrams() {
    List<Epigram> epigramList = epigramRepository.findAll();
    return epigramList.stream().map(epigramMapper::toEpigramDTO).toList();
  }

  public EpigramDTO createEpigram(EpigramDTO epigramDTO) {
    epigramRepository.save(epigramMapper.toEpigram(epigramDTO));

    return epigramDTO;
  }

  public EpigramDTO getRandomEpigram() {
    if (epigramRepository.count() == 0) {
      throw new NoEpigramsFoundException("No Epigrams found to load!");
    }

    Epigram epigram =
        epigramRepository
            .findRandom()
            .orElseThrow(() -> new NoEpigramsFoundException("Error getting random epigram"));

    return epigramMapper.toEpigramDTO(epigram);
  }
}
