package com.logonedigital.Nnam.repositories;

import com.logonedigital.Nnam.entities.Livreur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivreurRepo extends JpaRepository<Livreur, Integer> , JpaSpecificationExecutor<Livreur> {
    Optional<Livreur> findByNom(String nom);
}
