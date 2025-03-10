package com.logonedigital.Nnam.mapper;

import com.logonedigital.Nnam.dto.categorie.CategorieReqDTO;
import com.logonedigital.Nnam.dto.categorie.CategorieResDTO;
import com.logonedigital.Nnam.entities.Categorie;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface CategorieMapper {
    Categorie getCategorieFromCategorieReqDTO(CategorieReqDTO categorieReqDTO);
    CategorieResDTO getCategorieResDTOFromCategorie(Categorie categorie);


}
