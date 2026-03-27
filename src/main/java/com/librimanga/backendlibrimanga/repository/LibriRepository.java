package com.librimanga.backendlibrimanga.repository;

import com.librimanga.backendlibrimanga.model.Libri;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibriRepository extends JpaRepository<Libri, Integer> {
    List<Libri> findByOrderByDataPubblicazioneAsc();
    List<Libri> findByOrderByDataPubblicazioneDesc();
    List<Libri> findByOrderByPrezzoAsc();
    List<Libri> findByOrderByPrezzoDesc();
    List<Libri> findByOrderByIdAsc();
}
