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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Profil implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L ;

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Integer idProfil ;

    private String nomProfil;
    private String zoneGeographique ;

    @OneToOne
    @JoinColumn (name = "idUtilisateur")
    @JsonIgnoreProperties("profil")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn (name = "idRole")
    @JsonIgnoreProperties("profils")
    private Role role;
}
