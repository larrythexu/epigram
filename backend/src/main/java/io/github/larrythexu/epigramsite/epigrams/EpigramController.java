package io.github.larrythexu.epigramsite.epigrams;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class EpigramController {

  private final EpigramService epigramService;

  @GetMapping("/epigrams/random")
  public EpigramDTO getRandomEpigram() {
    return epigramService.getRandomEpigram();
  }

  @GetMapping("/epigrams")
  public List<EpigramDTO> getAllEpigrams() {
    return epigramService.getAllEpigrams();
  }

  @GetMapping("/epigrams/{id}")
  public EpigramDTO getEpigram(@PathVariable long id) {
    return epigramService.getEpigram(id);
  }

  @PostMapping("/epigrams")
  public EpigramDTO createEpigram(@RequestBody @Validated EpigramDTO epigram) {
    return epigramService.createEpigram(epigram);
  }
}
