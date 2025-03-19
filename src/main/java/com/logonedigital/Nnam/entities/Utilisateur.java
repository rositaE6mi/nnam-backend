package com.logonedigital.Nnam.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    private String nomUtilisateur;

    private String prenomUtilisateur;

    private String dateNaissance ;
    private String lieuNaissance ;
    private String VilleActuelle ;
    private String quartier ;
    private String boitePostale ;

    private String email ;

    private Date dateDeCreation ;
    private Date dateDeModification ;
    private Boolean statut ;

    @ManyToOne
    @JoinColumn (name = "idRole")
    @JsonIgnoreProperties("utilisateurs")// Evite la recursivite infinie
    private Role role;

    @OneToOne( mappedBy = "utilisateur")
    @JsonIgnoreProperties("utilisateurs")
    private Profil profil;


}
