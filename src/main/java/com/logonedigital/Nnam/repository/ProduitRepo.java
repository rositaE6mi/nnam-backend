package com.logonedigital.Nnam.repository;

import com.logonedigital.Nnam.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProduitRepo extends JpaRepository<Produit, Integer> {

    Optional<Produit> findById(Integer id);
}
