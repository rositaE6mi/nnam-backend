package com.logonedigital.Nnam.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class LivreurResDTO {
    private Integer idLivreur;
    @NotEmpty(message = "Please fill this")
    @NotNull( message = "This field can't be null")
    private String nom;

    private String prenom;

    private String telephone;

    public LivreurResDTO() {
    }

    public LivreurResDTO(Integer idLivreur, String nom, String prenom, String telephone) {
        this.idLivreur = idLivreur;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
    }

    public Integer getIdLivreur() {
        return idLivreur;
    }

    public void setIdLivreur(Integer idLivreur) {
        this.idLivreur = idLivreur;
    }

    public @NotEmpty(message = "Please fill this") @NotNull(message = "This field can't be null") String getNom() {
        return nom;
    }

    public void setNom(@NotEmpty(message = "Please fill this") @NotNull(message = "This field can't be null") String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
