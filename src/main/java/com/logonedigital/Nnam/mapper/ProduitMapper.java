package com.logonedigital.Nnam.mapper;

import com.logonedigital.Nnam.dto.produit.ProduitReqDTO;
import com.logonedigital.Nnam.dto.produit.ProduitResDTO;
import com.logonedigital.Nnam.entities.Produit;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface ProduitMapper {
    Produit getProduitFromProduitReqDTO(ProduitReqDTO produitReqDTO);
    ProduitResDTO getProduitResDTOFromProduit(Produit produit);
}
