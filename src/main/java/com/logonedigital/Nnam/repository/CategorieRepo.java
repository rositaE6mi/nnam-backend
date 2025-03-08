package com.logonedigital.Nnam.repository;

import com.logonedigital.Nnam.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepo extends JpaRepository<Categorie, Integer> {
    boolean existsByNomCat(String nomCat);
}