package com.logonedigital.Nnam.repository;

import com.logonedigital.Nnam.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepo extends JpaRepository<Produit, Integer> {
    @Query("SELECT p FROM Produit p WHERE p.prixU BETWEEN :min AND :max")
    List<Produit> findByPriceRange(@Param("min") double min, @Param("max") double max);

    @Query("SELECT p FROM Produit p JOIN FETCH p.stock WHERE p.idProduit = :idProduit")
    Produit findProduitWithStock(@Param("idProduit") int idProduit);


    List<Produit> findByNomProduitContainingAndPrixUBetween(String nom, Double minPrice, Double maxPrice);
}