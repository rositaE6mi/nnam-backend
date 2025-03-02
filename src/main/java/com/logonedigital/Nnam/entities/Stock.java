package com.logonedigital.Nnam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stock{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStock;

    private String nom;
    private int quantiteStock;

    @OneToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    public int getStock() {
        return this.quantiteStock;
    }
}
