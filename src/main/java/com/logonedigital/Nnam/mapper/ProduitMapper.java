package com.logonedigital.Nnam.mapper;

import com.logonedigital.Nnam.dto.produit.ProduitReqDTO;
import com.logonedigital.Nnam.dto.produit.ProduitResDTO;
import com.logonedigital.Nnam.entities.Produit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Mapper(componentModel = "spring")
//@Configuration
public interface ProduitMapper {
    @Mapping(target = "idProduit", ignore = true)
    @Mapping(target = "categorie", ignore = true)
    @Mapping(target = "stock", ignore = true)
    Produit getProduitFromProduitReqDTO(ProduitReqDTO produitReqDTO);

    @Mapping(source = "categorie.idCat", target = "categorieId")
    @Mapping(source = "stock", target = "stock")
    ProduitResDTO getProduitResDTOFromProduit(Produit produit);


   // List<ProduitResDTO> getProduitResDTOFromProduit(List<Produit> produits);
}
