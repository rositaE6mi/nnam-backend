package com.logonedigital.Nnam.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Commande implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commandeId;
    private Date createdAt;
    private Date UpdatedAt;
    @NotBlank(message = "La référence est obligatoire") // Vérifie que la chaîne n'est pas vide
    @Size(min = 3, max = 50, message = "La référence doit avoir entre 3 et 50 caractères") // Longueur minimale et maximale
    private String reference;
    @NotNull(message = "La date de commande est obligatoire")
    private Date dateCommande;
    @NotBlank(message = "Le statut est obligatoire")
    private String status;
    @Positive(message = "Le total doit être un nombre positif") // Vérifie que le total est positif
    private int total;

    @OneToOne
    private Facture facture;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // Ignore cette relation lors de la sérialisation JSON
    private List<LigneCommande> ligneCommande = new ArrayList<>();
}