package com.logonedigital.Nnam.mapper;

import com.logonedigital.Nnam.dto.CategorieReqDTO;
import com.logonedigital.Nnam.dto.CategorieResDTO;
import com.logonedigital.Nnam.entities.Categorie;
import jakarta.persistence.Column;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface CategorieMapper {
    Categorie getCategorieFromCategorieReqDTO(CategorieReqDTO categorieReqDTO);
    CategorieResDTO getCategorieResDTOFromCategorie(Categorie categorie);


}
