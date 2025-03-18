package com.logonedigital.Nnam.dto.produit;

import com.logonedigital.Nnam.dto.stock.StockResDTO;
import lombok.*;

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
    private int categorieId;//pour eviter kes reference circulaires //mapping depuis Categorie.idCat
    private StockResDTO stock;//creation dun DTO pour stock
}
