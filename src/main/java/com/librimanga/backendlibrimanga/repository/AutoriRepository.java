package com.librimanga.backendlibrimanga.repository;

import com.librimanga.backendlibrimanga.model.Autori;
import com.librimanga.backendlibrimanga.model.Utenti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutoriRepository extends JpaRepository<Autori, Integer> {
}
