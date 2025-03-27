package com.logonedigital.Nnam.services.passwordReset;

public interface PasswordResetService {

    void requestPasswordReset(String email);
    void resetPassword(String token, String newPassword);

    // Envoie un email avec un lien de réinitialisation de mot de passe
    boolean sendPasswordResetEmail(String email);
}
