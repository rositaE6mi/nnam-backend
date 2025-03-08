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

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCat;
    @NotEmpty(message = "please fill this")
    @NotNull(message = "this fill couldn't be!")
    @Column( nullable = false, unique = true)
    private String nomCat;
    @NotEmpty(message = "please fill this")
    @NotNull(message = "this fill couldn't be!")
    private String description;
    @JsonManagedReference
    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produit> produits;
}