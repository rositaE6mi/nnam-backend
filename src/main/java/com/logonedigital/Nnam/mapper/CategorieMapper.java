package com.logonedigital.Nnam.mapper;

import com.logonedigital.Nnam.dto.categorie.CategorieReqDTO;
import com.logonedigital.Nnam.dto.categorie.CategorieResDTO;
import com.logonedigital.Nnam.entities.Categorie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface CategorieMapper {
    @Mapping(target = "idCat", ignore = true) //ceci car id est auto-increment
    Categorie getCategorieFromCategorieReqDTO(CategorieReqDTO categorieReqDTO);

    @Mapping(source ="id", target = "idCat") //car lentite un champs
    CategorieResDTO getCategorieResDTOFromCategorie(Categorie categorie);


}
