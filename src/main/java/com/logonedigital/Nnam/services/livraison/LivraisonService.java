package com.logonedigital.Nnam.services.livraison;

import com.logonedigital.Nnam.entities.Livraison;

import java.util.List;

public interface LivraisonService {
    void addLivraison(Livraison livraison);
    void deleteLivraison(Integer idLivrison);
    void updateLivraison(Integer idLivraison, Livraison livraison );
    Livraison getLivriasonById(Integer idLivraison);
    List<Livraison> getAllLivraison();
}
