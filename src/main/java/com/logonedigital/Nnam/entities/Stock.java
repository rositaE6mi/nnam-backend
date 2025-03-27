package com.logonedigital.Nnam.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStock;

    @Column(nullable = false)
    @NotEmpty(message = "Please fill this")
    private String nom;

    @Column(nullable = false)
    private int quantiteStock;

    @JsonBackReference
    @OneToOne(mappedBy = "stock")
    private Produit produit;
}