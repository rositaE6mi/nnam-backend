package com.logonedigital.Nnam.repository;

import com.logonedigital.Nnam.entities.Utilisateur;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepo extends JpaRepository <Utilisateur, Integer> {// Vérifie si un email existe déjà dans la base de données
    boolean existsByEmail(String email);

    // Recherche un utilisateur par email en utilisant Optional pour éviter les erreurs de null
    @Query("SELECT u FROM Utilisateur u WHERE u.email = :email")
    Optional<Utilisateur> findByEmail(@Param("email") String email);
}
