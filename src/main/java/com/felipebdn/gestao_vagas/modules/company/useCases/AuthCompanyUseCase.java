package com.felipebdn.gestao_vagas.modules.company.useCases;


import java.time.Duration;
import java.time.Instant;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.felipebdn.gestao_vagas.exceptions.EmailNotFoundException;
import com.felipebdn.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.felipebdn.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

  @Value("${security.token.secret}")
  private String secreatKey;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException{
    var company = this.companyRepository.findByEmail(authCompanyDTO.getEmail()).orElseThrow(
      ()->{
        throw new EmailNotFoundException("email/password incorrect");
      }
    );

    // Verificar senha são iguais    
    var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());
        
    // Se não for igual -> Erro
    if(!passwordMatches){
      throw new AuthenticationException();
    }

    // Se for igual -> Gerar Token
    Algorithm algorithm = Algorithm.HMAC256(secreatKey);
    var token = JWT.create().withIssuer("javagas")
    .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
    .withSubject(company.getId().toString())
    .sign(algorithm);

    return token;
  }

}
