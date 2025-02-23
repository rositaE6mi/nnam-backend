package com.logonedigital.Nnam.entities;

import jakarta.persistence.*;
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
    private Integer factureId;
    private Date dateFacture;
    private int montantTotal;

    @OneToOne
    @JoinColumn(name = "commandeId") // Clé étrangère vers la commande
    private Commande commande;
}
