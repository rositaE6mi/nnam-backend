package com.logonedigital.Nnam.mapper;

import com.logonedigital.Nnam.dto.produit.ProduitReqDTO;
import com.logonedigital.Nnam.dto.produit.ProduitResDTO;
import com.logonedigital.Nnam.entities.Produit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Mapper(componentModel = "spring", uses = {StockMapper.class})
public interface ProduitMapper {
    Produit getProduitFromProduitReqDTO(ProduitReqDTO produitReqDTO);

    @Mapping( source = "stock.id", target = "stock.idStock")
    //@Mapping( target = "stock.idStock", ignore = true)
    @Mapping(source = "categorie.idCat", target = "categorieId")
    ProduitResDTO getProduitResDTOFromProduit(Produit produit);
    @Mapping(source = "categorie.idCat", target = "categorieId")
   List<ProduitResDTO> toDtoProduitList(List<Produit> produits);
}
