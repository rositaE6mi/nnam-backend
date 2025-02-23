package com.logonedigital.Nnam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LigneCommande implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ligneId;
    private int quantite;
    private int prixUnitaire;
    private double totalLigne;

    @ManyToOne
    @JoinColumn(name = "commandeId") // Clé étrangère dans la table LigneCommande
    private Commande commande;
}

