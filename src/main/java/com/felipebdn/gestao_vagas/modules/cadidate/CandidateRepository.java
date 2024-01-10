package com.felipebdn.gestao_vagas.modules.cadidate;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidateEnity, UUID> {
  Optional<CandidateEnity> findByUsernameOrEmail(String username, String email);
}
