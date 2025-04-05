package com.logonedigital.Nnam.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ProfilDTO {
    private Integer idProfil;

    @NotEmpty(message = "Please fill this")
    private String nomProfil;
    private String zoneGeographique;

    @NotNull(message = "L'ID de l'utilisateur est obligatoire")
    private Integer idUtilisateur;

    @NotNull(message = "L'ID du rôle est obligatoire")
    private Integer idRole;

    public ProfilDTO(Integer idProfil, String nomProfil, String zoneGeographique, Integer idUtilisateur, Integer idRole) {
        this.idProfil = idProfil;
        this.nomProfil = nomProfil;
        this.zoneGeographique = zoneGeographique;
        this.idUtilisateur = idUtilisateur;
        this.idRole = idRole;
    }

    public ProfilDTO() {
    }

    public Integer getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(Integer idProfil) {
        this.idProfil = idProfil;
    }

    public String getNomProfil() {
        return nomProfil;
    }

    public void setNomProfil(String nomProfil) {
        this.nomProfil = nomProfil;
    }

    public String getZoneGeographique() {
        return zoneGeographique;
    }

    public void setZoneGeographique(String zoneGeographique) {
        this.zoneGeographique = zoneGeographique;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }
}
