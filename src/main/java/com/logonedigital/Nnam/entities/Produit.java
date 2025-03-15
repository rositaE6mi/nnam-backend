package com.logonedigital.Nnam.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.aspectj.bridge.Message;

import java.util.Date;
//refaire les validations sur les entites
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduit;


    @NotEmpty(message = "please fill this")
    @NotNull(message = "this fill couldn't be null")
    private String nomProduit;
    @NotEmpty(message = "please fill this")
    @NotNull(message = "this fill couldn't be!")
    private String description;

    @Column(nullable = false)
    @NotNull(message = "this fill couldn't be!")
    private double prixU;

    @Column(nullable = false)
    @NotNull(message = "date couldn't be null")
    private Date dateExpiration;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = false)
    private Categorie categorie;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;
}