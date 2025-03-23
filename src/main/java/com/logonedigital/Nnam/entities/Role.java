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
import java.util.List;

@Entity
public class Role implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRole ;
    private String nomRole ;

    @OneToMany(mappedBy = "role")
    @JsonIgnoreProperties ("role")// Empeche les boucles infinies
    private List<Utilisateur> utilisateurs;

    @OneToMany(mappedBy = "role")
    @JsonIgnoreProperties ("role")// Empeche les boucles infinies
    private List<Profil> profils;

    //  Constructeur vide obligatoire pour Hibernate
    public Role() {

    }


    public Role(Integer idRole, String nomRole, List<Utilisateur> utilisateurs, List<Profil> profils) {
        this.idRole = idRole;
        this.nomRole = nomRole;
        this.utilisateurs = utilisateurs;
        this.profils = profils;
    }

    public Role(Integer idRole) {
    }


    public Integer getIdRole() {
        return idRole;
    }

    public String getNomRole() {
        return nomRole;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public List<Profil> getProfils() {
        return profils;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public void setProfils(List<Profil> profils) {
        this.profils = profils;
    }
}
