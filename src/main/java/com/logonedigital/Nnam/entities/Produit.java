package com.logonedigital.Nnam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Produit implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduit;

    private String nomProduit;
    private String description;
    private double prixU;
    private Date dateExpiration;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;
    @OneToOne(mappedBy = "produit", cascade = CascadeType.ALL)
    private Stock stock;

}
