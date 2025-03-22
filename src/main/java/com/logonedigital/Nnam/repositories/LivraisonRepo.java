package com.logonedigital.Nnam.repositories;

import com.logonedigital.Nnam.entities.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivraisonRepo extends JpaRepository<Livraison, Integer> {
    Optional<Livraison> findByAdresseDestination(String adresseDestination);

    @Query("SELECT l FROM Livraison l WHERE l.livreur.id = :livreurId")
    List<Livraison> findLivraisonsByLivreurId(@Param("livreurId") Long livreurId);
}
