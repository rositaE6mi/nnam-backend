package com.logonedigital.Nnam.repositories;

import com.logonedigital.Nnam.entities.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivraisonRepo extends JpaRepository<Livraison, Integer> {
}
