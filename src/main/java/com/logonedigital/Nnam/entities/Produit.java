package com.logonedigital.Nnam.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.bridge.Message;

import java.util.Date;
//refaire les validations sur les entites
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduit;

    @NotEmpty(message = "Please fill this")
    private String nomProduit;

    @NotEmpty(message = "Please fill this")
    private String description;

    @Column(nullable = false)
    @NotNull(message = "Price cannot be null")
    private Double prixU; //

    @Column(nullable = false)
    @NotNull(message = "Date cannot be null")
    private Date dateExpiration;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = false)
    private Categorie categorie;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "stock_id", unique = true) //
    private Stock stock;
}