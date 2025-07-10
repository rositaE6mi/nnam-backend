package com.logonedigital.Nnam.services.passwordReset;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to, String subject, String body) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("jarodak47@gmail.com"); // Remplace par ton adresse email configurée
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true); // true pour envoyer un email en HTML

            javaMailSender.send(message);
        } catch (MessagingException e) {
            System.out.println("Test-du-mail-sender");
            throw new RuntimeException("Erreur lors de l'envoi de l'email", e);
        }
    }

    public void sendResetPasswordEmail(String email, String token) {
        System.out.println("sendResetPasswordEmail1" +email);
        String resetLink = "http://localhost:8083/swagger-ui/index.html#/password-reset-controller/forgotPassword" + token;
        String subject = "Réinitialisation de votre mot de passe";
        String body = "<p>Bonjour,</p>"
                + "<p>Cliquez sur le lien ci-dessous pour réinitialiser votre mot de passe :</p>"
                + "<a href=\"" + resetLink + "\">Réinitialiser mon mot de passe</a>"
                + "<p>Si vous n'avez pas demandé de réinitialisation, ignorez cet email.</p>";

        sendEmail(email, subject, body);
    }
}

