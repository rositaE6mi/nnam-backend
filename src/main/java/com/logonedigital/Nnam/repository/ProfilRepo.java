package com.logonedigital.Nnam.repository;

import com.logonedigital.Nnam.entities.Profil;
import com.logonedigital.Nnam.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilRepo extends JpaRepository<Profil, Integer> {
}
