package com.logonedigital.Nnam.mapper;


import com.logonedigital.Nnam.dto.LivraisonReqDTO;
import com.logonedigital.Nnam.dto.LivraisonResDTO;
import com.logonedigital.Nnam.entities.Livraison;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface LivraisonMapper {

    Livraison getLivraisonFromLivraisonDTO(LivraisonReqDTO livraisonReqDTO);
    LivraisonResDTO getLivraisonResDTOFromLivraison(Livraison livraison);
    List<LivraisonResDTO> getAllLivraisonResDTOFromAllLIvraison(List<Livraison> livraisons);



}
