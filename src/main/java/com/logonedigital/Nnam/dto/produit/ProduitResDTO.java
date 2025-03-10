package com.logonedigital.Nnam.dto.produit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduitResDTO {
    private int idProduit;
    private String nomProduit;
    private String description;
    private double prixU;
    private LocalDate dateExpiration;
    private String categorieNom;
    private String stockNom;
}
