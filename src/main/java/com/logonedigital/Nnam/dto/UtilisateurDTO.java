package com.logonedigital.Nnam.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@ApiModel(description = "DTO représentant un utilisateur")
public class UtilisateurDTO {

    @ApiModelProperty(value = "ID de l'utilisateur", hidden = true) // Cela cache l'ID dans Swagger si ce n'est pas nécessaire
    private Integer idUtilisateur;

    @NotEmpty(message = "Le nom ne doit pas etre null")
    @ApiModelProperty(value = "Nom de l'utilisateur", required = true)
    private String nomUtilisateur;

    @NotEmpty(message = "Please fill this")
    @ApiModelProperty(value = "Prénom de l'utilisateur", required = true)
    private String prenomUtilisateur;

    @ApiModelProperty(value = "Date de naissance de l'utilisateur")
    private String dateNaissance;

    @ApiModelProperty(value = "Lieu de naissance de l'utilisateur")
    private String lieuNaissance;

    @ApiModelProperty(value = "Ville actuelle de l'utilisateur")
    private String villeActuelle;

    @ApiModelProperty(value = "Quartier de l'utilisateur")
    private String quartier;

    @ApiModelProperty(value = "Boîte postale de l'utilisateur")
    private String boitePostale;

    @Email(message = "Email incorrect!")
    @NotEmpty(message = "Please fill this")
    @ApiModelProperty(value = "Email de l'utilisateur", required = true)
    private String email;

    @ApiModelProperty(value = "Statut de l'utilisateur (actif ou non)")
    private Boolean statut;

    @NotEmpty(message = "Veuillez entrer un mot de passe.")
    @ApiModelProperty(value = "Mot de passe de l'utilisateur", required = true)
    private String motDePasse;

    @ApiModelProperty(value = "ID du rôle de l'utilisateur")
    private Integer idRole; // On ne stocke que l'ID du rôle

    @ApiModelProperty(value = "ID du profil de l'utilisateur")
    private Integer idProfil; // On stocke uniquement l'ID du profil

    // Constructeur avec paramètres
    public UtilisateurDTO(Integer idUtilisateur, String nomUtilisateur, String prenomUtilisateur, String dateNaissance,
                          String lieuNaissance, String villeActuelle, String quartier, String boitePostale, String email,String motDePasse,
                          Boolean statut, Integer idRole, Integer idProfil) {
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
        this.statut = statut;
        this.idRole = idRole;
        this.idProfil = idProfil;
    }

    // Constructeur vide
    public UtilisateurDTO() {}

    // Getters et setters
    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public void setPrenomUtilisateur(String prenomUtilisateur) {
        this.prenomUtilisateur = prenomUtilisateur;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getVilleActuelle() {
        return villeActuelle;
    }

    public void setVilleActuelle(String villeActuelle) {
        this.villeActuelle = villeActuelle;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getBoitePostale() {
        return boitePostale;
    }

    public void setBoitePostale(String boitePostale) {
        this.boitePostale = boitePostale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStatut() {
        return statut;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public Integer getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(Integer idProfil) {
        this.idProfil = idProfil;
    }

    public class PasswordResetRequest {
        private String token;
        private String newPassword;

    }



}

