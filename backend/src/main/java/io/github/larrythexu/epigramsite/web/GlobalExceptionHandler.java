package io.github.larrythexu.epigramsite.web;

import io.github.larrythexu.epigramsite.domain.exceptions.NoEpigramsFoundException;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NoEpigramsFoundException.class)
  public ResponseEntity<ProblemDetail> handleNoEpigramsFound(NoEpigramsFoundException e) {
    ProblemDetail problem =
        ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    problem.setTitle("Epigram retrieval error");

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problem);
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<ProblemDetail> handleNoSuchElement(NoSuchElementException e) {
    ProblemDetail problem =
        ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    problem.setTitle("Epigram not found");

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
  }
}
