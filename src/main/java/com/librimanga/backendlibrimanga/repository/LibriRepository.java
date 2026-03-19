package com.librimanga.backendlibrimanga.repository;

import com.librimanga.backendlibrimanga.model.Autori;
import com.librimanga.backendlibrimanga.model.Libri;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibriRepository extends JpaRepository<Libri, Integer> {
}
