package com.logonedigital.Nnam.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
public class Profil implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProfil;

    private String nomProfil;
    private String zoneGeographique;

    @OneToOne
    @JoinColumn(name = "idUtilisateur")
    @JsonIgnoreProperties("profil")
    private Utilisateur utilisateur;

    // Constructeur vide obligatoire pour Hibernate
    public Profil() {}

    public Profil(Integer idProfil, String nomProfil, String zoneGeographique, Utilisateur utilisateur, Role role) {
        this.idProfil = idProfil;
        this.nomProfil = nomProfil;
        this.zoneGeographique = zoneGeographique;
        this.utilisateur = utilisateur;
    }

    // Getters et setters
    public Integer getIdProfil() {
        return idProfil;
    }

    public String getNomProfil() {
        return nomProfil;
    }

    public String getZoneGeographique() {
        return zoneGeographique;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setIdProfil(Integer idProfil) {
        this.idProfil = idProfil;
    }

    public void setNomProfil(String nomProfil) {
        this.nomProfil = nomProfil;
    }

    public void setZoneGeographique(String zoneGeographique) {
        this.zoneGeographique = zoneGeographique;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

}

