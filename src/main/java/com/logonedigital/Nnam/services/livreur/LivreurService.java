package com.logonedigital.Nnam.services.livreur;

import com.logonedigital.Nnam.entities.Livreur;

import java.util.List;

public interface LivreurService {

    void addLivreur(Livreur livreur);
    void deleteLivreur(Integer idLivreur);
    void updateLivreur(Integer idLivreur, Livreur livreur);
    Livreur getById(Integer idLivreur);
    List<Livreur> getAllLivreur();
}
