package com.logonedigital.Nnam.dto.categorie;

import com.logonedigital.Nnam.dto.produit.ProduitResDTO;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorieResDTO {
    private int idCat;
    private String nomCat;
    private String description;
    private List<ProduitResDTO> produits;
}
