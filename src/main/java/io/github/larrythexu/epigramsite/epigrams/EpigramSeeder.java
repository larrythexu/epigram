package io.github.larrythexu.epigramsite.epigrams;

import jakarta.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
@AllArgsConstructor
@Slf4j
public class EpigramSeeder {

  private final EpigramRepository epigramRepository;
  private final ObjectMapper objectMapper;

  @EventListener(ApplicationReadyEvent.class)
  @Transactional
  public void seedEpigrams() {
    if (epigramRepository.count() > 0) {
      return;
    }

    ClassPathResource resource = new ClassPathResource("epigramdata.json");
    try (InputStream inputStream = resource.getInputStream()) {
      String[] epigramList = objectMapper.readValue(inputStream, String[].class);

      List<Epigram> epigrams = Arrays.stream(epigramList).map(Epigram::new).toList();

      epigramRepository.saveAll(epigrams);
    } catch (IOException e) {
      log.error("Failed to seed epigrams: {}", e.getMessage());
    }
  }
}
