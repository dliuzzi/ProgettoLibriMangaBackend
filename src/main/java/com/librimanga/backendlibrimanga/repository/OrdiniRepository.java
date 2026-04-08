package com.librimanga.backendlibrimanga.repository;

import com.librimanga.backendlibrimanga.model.Ordini;
import com.librimanga.backendlibrimanga.model.Utenti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdiniRepository extends JpaRepository<Ordini, Integer> {
    List<Ordini> findByUtente(Utenti utente);
}
