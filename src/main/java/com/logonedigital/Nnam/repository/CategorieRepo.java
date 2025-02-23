package com.logonedigital.Nnam.repository;

import com.logonedigital.Nnam.entities.Categorie;
import com.logonedigital.Nnam.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepo extends JpaRepository<Categorie, Integer>  {
}
