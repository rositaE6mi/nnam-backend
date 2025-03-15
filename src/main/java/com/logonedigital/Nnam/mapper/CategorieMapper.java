package com.logonedigital.Nnam.mapper;

import com.logonedigital.Nnam.dto.categorie.CategorieReqDTO;
import com.logonedigital.Nnam.dto.categorie.CategorieResDTO;
import com.logonedigital.Nnam.entities.Categorie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring", uses = {ProduitMapper.class, StockMapper.class})//uses = ProduitMapper.class permet a MapStruct de use ke produitMapper pour convertir produit en produitResDTO
//@Configuration
public interface CategorieMapper {
   @Mapping(target = "idCat", ignore = true)
    @Mapping(target = "produits", ignore = true)
    Categorie getCategorieFromCategorieReqDTO(CategorieReqDTO categorieReqDTO);

    @Mapping(source = "produits", target = "produits")
    CategorieResDTO getCategorieResDTOFromCategorie(Categorie categorie);


}
