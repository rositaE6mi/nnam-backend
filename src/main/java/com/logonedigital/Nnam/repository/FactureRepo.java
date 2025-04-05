package com.logonedigital.Nnam.repository;

import com.logonedigital.Nnam.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepo extends JpaRepository <Facture,Integer> {
}
