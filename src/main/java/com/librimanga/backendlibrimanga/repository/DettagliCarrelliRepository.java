package com.librimanga.backendlibrimanga.repository;

import com.librimanga.backendlibrimanga.model.DettagliCarrelli;
import com.librimanga.backendlibrimanga.model.Utenti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DettagliCarrelliRepository extends JpaRepository<DettagliCarrelli, Integer> {
    List<DettagliCarrelli> findByUtente(Utenti utente);
}
