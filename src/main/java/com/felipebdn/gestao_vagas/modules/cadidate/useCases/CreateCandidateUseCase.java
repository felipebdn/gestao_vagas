package com.felipebdn.gestao_vagas.modules.cadidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipebdn.gestao_vagas.exceptions.UserFoundException;
import com.felipebdn.gestao_vagas.modules.cadidate.CandidateEnity;
import com.felipebdn.gestao_vagas.modules.cadidate.CandidateRepository;

@Service
public class CreateCandidateUseCase {
  
  @Autowired
  private CandidateRepository candidateRepository;

  public CandidateEnity execute(CandidateEnity candidateEnity){
    this.candidateRepository
      .findByUsernameOrEmail(candidateEnity.getUsername(), candidateEnity.getEmail())
      .ifPresent((user)->{
        throw new UserFoundException();
      });
    return this.candidateRepository.save(candidateEnity);
  }
}
