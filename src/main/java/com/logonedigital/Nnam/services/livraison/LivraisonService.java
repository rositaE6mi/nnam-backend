package com.logonedigital.Nnam.services.livraison;

import com.logonedigital.Nnam.dto.LivraisonReqDTO;
import com.logonedigital.Nnam.dto.LivraisonResDTO;
import com.logonedigital.Nnam.entities.Livraison;

import java.util.List;

public interface LivraisonService {
    void addLivraison(LivraisonReqDTO livraisonReqDTO);
    void deleteLivraison(Integer idLivrison);
    void updateLivraison(Integer idLivraison, LivraisonReqDTO livraison );
    LivraisonResDTO getLivriasonById(Integer idLivraison);
    List<LivraisonResDTO> getAllLivraison();
}
