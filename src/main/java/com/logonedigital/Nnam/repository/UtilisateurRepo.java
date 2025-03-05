package com.logonedigital.Nnam.repository;

import com.logonedigital.Nnam.entities.Utilisateur;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepo extends JpaRepository <Utilisateur, Integer> {
    boolean existsByEmail(@Email(message = "email isn't correct!") @NotEmpty(message = "Please fill this") String email);
}
