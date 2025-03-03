package com.logonedigital.Nnam.entities;

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
    @NotEmpty(message = "Please fill this")
    @NotNull( message = "This field can't be null")
    private String nomProfil ;
    @Lob //champ volumineux
    private String photoBase64; // Stocke l'image en base 64, facile a recuperer et afficher dans une API mais prend plus d'espace en base qu'un simple chemin
    private String zoneGeographique ;

    @OneToOne
    @JoinColumn (name = "idUtilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn (name = "idRole")
    private Role role;
}
