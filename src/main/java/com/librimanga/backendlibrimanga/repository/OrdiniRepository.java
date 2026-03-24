package com.librimanga.backendlibrimanga.repository;

import com.librimanga.backendlibrimanga.model.DettagliOrdini;
import com.librimanga.backendlibrimanga.model.Ordini;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdiniRepository extends JpaRepository<Ordini, Integer> {
}
