package com.librimanga.backendlibrimanga.repository;

import com.librimanga.backendlibrimanga.model.Libri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LibriRepository extends JpaRepository<Libri, Integer>, JpaSpecificationExecutor<Libri> {
}
