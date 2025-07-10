package com.logonedigital.Nnam.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    // 🔹 Constructeur par défaut
    public PasswordResetToken() {}

    // 🔹 Constructeur avec paramètres
    public PasswordResetToken(Utilisateur utilisateur, String token) {
        this.utilisateur = utilisateur;
        this.token = token;
        this.expiryDate = LocalDateTime.now().plusHours(1); // Expire après 1 heure
    }

    // 🔹 Vérifie si le token est expiré
    public boolean isExpired() {
        return expiryDate.isBefore(LocalDateTime.now());
    }

    // 🔹 Réinitialiser la date d'expiration
    public void updateExpiryDate() {
        this.expiryDate = LocalDateTime.now().plusHours(1);
    }

    // ✅ Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}
