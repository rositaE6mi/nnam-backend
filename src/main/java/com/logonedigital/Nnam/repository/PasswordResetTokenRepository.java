package com.logonedigital.Nnam.repository;

import com.logonedigital.Nnam.entities.PasswordResetToken;
import com.logonedigital.Nnam.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
    void deleteByUtilisateur(Utilisateur utilisateur);
}
