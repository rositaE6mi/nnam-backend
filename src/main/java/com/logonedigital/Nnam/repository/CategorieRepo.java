package com.logonedigital.Nnam.repository;

import com.logonedigital.Nnam.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorieRepo extends JpaRepository<Categorie, Integer> {
    boolean existsByNomCat(String nomCat);


    // Requête JPQL personnalisée pour éviter l'erreur de génération automatique
    @Query("SELECT c FROM Categorie c " +
            "WHERE (:nomCat IS NULL OR c.nomCat LIKE %:nomCat%) " +
            "AND (:description IS NULL OR c.description LIKE %:description%) " +
            "AND (:minProduits IS NULL OR SIZE(c.produits) >= :minProduits)")
    List<Categorie> findByNomCatContainingAndDescriptionContainingAndProduitsSizeGreaterThanEqual(
            @Param("nomCat") String nomCat,
            @Param("description") String description,
            @Param("minProduits") Integer minProduits
    );
    //List<Categorie> findByNomCatContainingAndDescriptionContainingAndProduitsSizeGreaterThanEqual(String nomCat, String description, Integer minProduits);
}