package com.logonedigital.Nnam.repositories;

import com.logonedigital.Nnam.entities.Administration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrationRepo extends JpaRepository<Administration, Integer> {
}
