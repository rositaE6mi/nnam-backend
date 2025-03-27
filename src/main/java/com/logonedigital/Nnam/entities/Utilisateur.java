package com.logonedigital.Nnam.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
public class Utilisateur implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUtilisateur;

    private String nomUtilisateur;

    private String prenomUtilisateur;

    private String dateNaissance ;
    private String lieuNaissance ;
    private String villeActuelle ;
    private String quartier ;
    private String boitePostale ;

    private String email ;

    @Column(nullable = false) // Assure que le mot de passe ne peut pas être NULL
    @JsonIgnore // Empêche l'exposition du mot de passe dans les réponses JSON
    private String motDePasse;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dateDeCreation;

    @Column(nullable = false)
    private LocalDateTime dateDeModification;

    private Boolean statut;

    @ManyToOne
    @JoinColumn(name = "idRole")
    @JsonIgnoreProperties("utilisateurs") // Évite la récursivité infinie
    private Role role;

    @OneToOne(mappedBy = "utilisateur")
    @JsonIgnoreProperties("utilisateur")
    private Profil profil;

    // Constructeur vide obligatoire pour Hibernate
    public Utilisateur() {}

    // Constructeur avec paramètres
    public Utilisateur(Integer idUtilisateur, String nomUtilisateur, String prenomUtilisateur, String dateNaissance,
                       String lieuNaissance, String villeActuelle, String quartier, String boitePostale,
                       String email,String motDePasse, LocalDateTime dateDeCreation, LocalDateTime dateDeModification,
                       Boolean statut, Role role, Profil profil) {
        this.idUtilisateur = idUtilisateur;
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.villeActuelle = villeActuelle;
        this.quartier = quartier;
        this.boitePostale = boitePostale;
        this.email = email;
        this.motDePasse = motDePasse;
        this.dateDeCreation = dateDeCreation;
        this.dateDeModification = dateDeModification;
        this.statut = statut;
        this.role = role;
        this.profil = profil;
    }


    // Initialisation automatique des dates avant insertion
    @PrePersist
    protected void onCreate() {
        dateDeCreation = LocalDateTime.now();
        dateDeModification = LocalDateTime.now();
    }

    // Mise à jour automatique de la date de modification
    @PreUpdate
    protected void onUpdate() {
        dateDeModification = LocalDateTime.now();
    }

    // Getters
    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public String getVilleActuelle() {
        return villeActuelle;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getQuartier() {
        return quartier;
    }

    public String getBoitePostale() {
        return boitePostale;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getDateDeCreation() {
        return dateDeCreation;
    }

    public LocalDateTime getDateDeModification() {
        return dateDeModification;
    }

    public Boolean getStatut() {
        return statut;
    }

    public Role getRole() {
        return role;
    }

    public Profil getProfil() {
        return profil;
    }

    // Setters
    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public void setPrenomUtilisateur(String prenomUtilisateur) {
        this.prenomUtilisateur = prenomUtilisateur;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public void setVilleActuelle(String villeActuelle) {
        this.villeActuelle = villeActuelle;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public void setBoitePostale(String boitePostale) {
        this.boitePostale = boitePostale;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateDeCreation(LocalDateTime dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    public void setDateDeModification(LocalDateTime dateDeModification) {
        this.dateDeModification = dateDeModification;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
