package com.logonedigital.Nnam.mapper;

import com.logonedigital.Nnam.dto.LivreurReqDTO;
import com.logonedigital.Nnam.dto.LivreurResDTO;
import com.logonedigital.Nnam.entities.Livreur;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
//@Component
public interface LivreurMapper {
    Livreur getLivreurFromLivreurReqDTO(LivreurReqDTO livreurReqDTO);
    LivreurResDTO getLivreurResDTOFromLivreur(Livreur livreur);
    List<LivreurResDTO> getAllLivreurResDTOFromALlLivreur(List<Livreur> livreurs);
}
