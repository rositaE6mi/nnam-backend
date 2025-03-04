package com.logonedigital.Nnam.repositories;

import com.logonedigital.Nnam.entities.Livreur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreurRepo extends JpaRepository<Livreur, Integer> {
}
