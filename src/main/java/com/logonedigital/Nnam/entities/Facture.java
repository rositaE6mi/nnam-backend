package com.logonedigital.Nnam.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Facture implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "La date de facture est obligatoire")
    private Integer factureId;
    @NotNull(message = "La date de facture est obligatoire")
    private Date dateFacture;
    @Positive(message = "Le montant total doit être positif")
    private int montantTotal;

    @OneToOne
    @JoinColumn(name = "commandeId") // Clé étrangère vers la commande
    @NotNull(message = "La commande associée est obligatoire")
    private Commande commande;
}
