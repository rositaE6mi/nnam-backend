package com.logonedigital.Nnam.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotEmpty(message = "please fill this")
    @NotNull(message = "this fill couldn't be!")
    private String nom;

    @NotNull(message = "this fill couldn't be!")
    @Column(nullable = false)
    private int quantiteStock;
    @JsonBackReference
    @OneToOne(mappedBy = "stock")
    private Produit produit;
}