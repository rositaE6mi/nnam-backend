package com.logonedigital.Nnam.repositories;

import com.logonedigital.Nnam.entities.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivraisonRepo extends JpaRepository<Livraison, Integer> {
    Optional<Livraison> findByAdresseDestination(String adresseDestination);
}
