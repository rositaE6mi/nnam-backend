package com.logonedigital.Nnam.dto.produit;

import com.logonedigital.Nnam.entities.Stock;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitReqDTO {
    @NotBlank private String nomProduit;
    @NotBlank
    private String description;
    @Positive
    private double prixU;
    @Future
    private LocalDate dateExpiration;
    @NotNull
    private Integer categorieId;
    @NotNull private Stock stock;
}
