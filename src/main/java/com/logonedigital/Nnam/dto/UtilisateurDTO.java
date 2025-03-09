package com.logonedigital.Nnam.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class UtilisateurDTO {
    private Integer idUtilisateur;

    @NotEmpty(message = "Please fill this")
    private String nomUtilisateur;

    @NotEmpty(message = "Please fill this")
    private String prenomUtilisateur;

    private String dateNaissance ;
    private String lieuNaissance ;
    private String VilleActuelle ;
    private String quartier ;
    private String boitePostale ;

    @Email(message = "email isn't correct!")
    @NotEmpty (message = "Please fill this")
    private String email ;

    private Date dateDeCreation ;
    private Date dateDeModification ;
    private Boolean statut ;

    private Integer idRole; // On ne stocke que Id du role
}
