package com.felipebdn.gestao_vagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipebdn.gestao_vagas.modules.company.entities.JobEntity;
import com.felipebdn.gestao_vagas.modules.company.useCases.CreateJobUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class JobController {
  
  @Autowired
  private CreateJobUseCase createJobUseCase;

  @PostMapping("/job")
  public JobEntity create(@Valid @RequestBody JobEntity jobEntity){
    System.out.println("teste"+jobEntity);
    return this.createJobUseCase.execute(jobEntity);
  }

}
