package com.librimanga.backendlibrimanga.repository;

import com.librimanga.backendlibrimanga.model.Categorie;
import com.librimanga.backendlibrimanga.model.Libri;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
}
