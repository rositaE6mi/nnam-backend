package com.logonedigital.Nnam.services.livreur;

import com.logonedigital.Nnam.dto.LivreurReqDTO;
import com.logonedigital.Nnam.dto.LivreurResDTO;
import com.logonedigital.Nnam.entities.Livreur;

import java.util.List;

public interface LivreurService {

    void addLivreur(LivreurReqDTO livreurReqDTO);
    void deleteLivreur(Integer idLivreur);
    void updateLivreur(Integer idLivreur, LivreurReqDTO livreurReqDTO);
    LivreurResDTO getById(Integer idLivreur);
    List<LivreurResDTO> getAllLivreur();
}
