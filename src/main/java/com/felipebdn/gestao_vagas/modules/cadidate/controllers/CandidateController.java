package com.felipebdn.gestao_vagas.modules.cadidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipebdn.gestao_vagas.modules.cadidate.CandidateEnity;
import com.felipebdn.gestao_vagas.modules.cadidate.useCases.CreateCandidateUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;

  @PostMapping("/")
  public ResponseEntity<Object> create(@Valid @RequestBody CandidateEnity candidateEnity){
    try {
      var result = this.createCandidateUseCase.execute(candidateEnity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
     return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
