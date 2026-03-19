package com.librimanga.backendlibrimanga.repository;

import com.librimanga.backendlibrimanga.model.Utenti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtentiRepository extends JpaRepository<Utenti, Integer> {
    boolean existsByEmail(String email);
    Optional<Utenti> findByEmail(String email);
}
