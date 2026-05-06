package com.librimanga.backendlibrimanga.repository;

import com.librimanga.backendlibrimanga.model.Libri;
import com.librimanga.backendlibrimanga.model.Recensioni;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecensioniRepository extends JpaRepository<Recensioni, Integer> {
    List<Recensioni> findByLibro(Libri libro);
}
