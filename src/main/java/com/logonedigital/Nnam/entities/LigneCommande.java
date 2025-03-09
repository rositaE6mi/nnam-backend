package com.logonedigital.Nnam.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @Min(value = 1, message = "La quantité doit être positive et supérieure à 0")
    private int quantite;
    @Positive(message = "Le prix unitaire doit être un nombre positif")
    private int prixUnitaire;
    @Positive(message = "Le total de la ligne doit être un nombre positif")
    private double totalLigne;

    @ManyToOne
    @JoinColumn(name = "commandeId") // Clé étrangère dans la table LigneCommande
    @NotNull(message = "La commande associée est obligatoire")
    private Commande commande;
}

