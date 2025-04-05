package com.logonedigital.Nnam.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

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
    private Integer factureId; // ID généré automatiquement

    @NotNull(message = "La date de facturation est obligatoire")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dateFacturation;

    @Positive(message = "Le montant total doit être positif")
    private int montantTotal;

    @NotNull(message = "Le statut de la facture est obligatoire")
    private String statut; // "NON_PAYEE", "PAYEE", etc.

    @OneToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;


    @PrePersist
    protected void onCreate() {
        if (dateFacturation == null) {
            dateFacturation = new Date(); // Initialiser avec la date actuelle
        }
    }
}
