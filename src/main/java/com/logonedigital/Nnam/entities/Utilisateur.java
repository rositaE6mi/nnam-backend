package com.logonedigital.Nnam.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Utilisateur implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUtilisateur;
    @NotEmpty (message = "Please fill this")
    @NotNull ( message = "This field can't be null")
    private String nomUtilisateur;
    private String prenomUtilisateur;
    private String dateNaissance ;
    private String lieuNaissance ;
    private String VilleActuelle ;
    private String quartier ;
    private String boitePostale ;
    @Email (message = "email isn't correct!")
    private String email ;
    private Date dateDeCreation ;
    private Date dateDeModification ;
    private Boolean statut ;

    @ManyToOne
    @JoinColumn (name = "idRole")
    private Role role;

}
