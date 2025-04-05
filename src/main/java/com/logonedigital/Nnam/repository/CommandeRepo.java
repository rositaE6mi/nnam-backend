package com.logonedigital.Nnam.repository;

import com.logonedigital.Nnam.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandeRepo extends JpaRepository<Commande, Integer> {
    Optional<Commande> findByReference(String reference);


}
