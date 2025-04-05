package com.logonedigital.Nnam.services.passwordReset;

import com.logonedigital.Nnam.entities.PasswordResetToken;
import com.logonedigital.Nnam.entities.Utilisateur;
import com.logonedigital.Nnam.repository.PasswordResetTokenRepository;
import com.logonedigital.Nnam.repository.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetServiceImpl implements PasswordResetService{

    @Autowired
    private UtilisateurRepo utilisateurRepos;  // Correct name

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepo;  // Correct name

    @Autowired
    private MailService mailService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void requestPasswordReset(String email) {
        Utilisateur utilisateur = utilisateurRepos.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email non trouvé"));

        // Générer un token de réinitialisation
        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken(utilisateur, token);

        // Sauvegarder le token en base
        passwordResetTokenRepo.save(passwordResetToken);

        // Envoyer l'email
        mailService.sendEmail(
                utilisateur.getEmail(),
                "Réinitialisation du mot de passe",
                "Cliquez sur ce lien pour réinitialiser votre mot de passe : http://localhost:8080/reset-password?token=" + token
        );
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        Optional<PasswordResetToken> resetTokenOpt = passwordResetTokenRepo.findByToken(token);

        if (resetTokenOpt.isPresent()) {
            PasswordResetToken resetToken = resetTokenOpt.get();

            if (isTokenExpired(resetToken)) {
                throw new RuntimeException("Token expiré");
            }

            Utilisateur utilisateur = resetToken.getUtilisateur();
            utilisateur.setMotDePasse(passwordEncoder.encode(newPassword));
            utilisateurRepos.save(utilisateur);

            passwordResetTokenRepo.delete(resetToken);
        } else {
            throw new RuntimeException("Token invalide");
        }
    }

    @Override
    public boolean sendPasswordResetEmail(String email) {
        Optional<Utilisateur> utilisateur = utilisateurRepos.findByEmail(email);
        if (utilisateur.isPresent()) {
            String token = generateResetToken();
            PasswordResetToken resetToken = new PasswordResetToken(utilisateur.get(), token);
            passwordResetTokenRepo.save(resetToken);
            sendResetEmail(utilisateur.get(), token);
            return true;
        }
        return false;
    }

    private String generateResetToken() {
        return UUID.randomUUID().toString();
    }

    private boolean isTokenExpired(PasswordResetToken token) {
        return token.getExpiryDate().isBefore(LocalDateTime.now());
    }

    private void sendResetEmail(Utilisateur utilisateur, String token) {
        // Appeler la méthode correcte pour envoyer l'email
        mailService.sendResetPasswordEmail(utilisateur.getEmail(), token);
    }
}