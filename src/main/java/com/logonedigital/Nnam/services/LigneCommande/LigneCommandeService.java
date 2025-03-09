package com.logonedigital.Nnam.services.LigneCommande;

import com.logonedigital.Nnam.dto.LigneCommandeDTO;
import com.logonedigital.Nnam.entities.LigneCommande;

import java.util.List;
import java.util.Optional;

public interface LigneCommandeService {
    // 📌 Ajouter une ligne de commande
    LigneCommande ajouterLigneCommande(LigneCommande ligneCommande);

    // 📌 Obtenir une ligne de commande par ID
    Optional<LigneCommandeDTO> obtenirLigneCommandeParId(Integer id);

    // 📌 Lister toutes les lignes de commande
    List<LigneCommandeDTO> listerToutesLesLignesCommandes();

    // 📌 Mettre à jour une ligne de commande
    LigneCommande mettreAJourLigneCommande(Integer id, LigneCommande ligneCommande);

    // 📌 Supprimer une ligne de commande
    boolean supprimerLigneCommande(Integer id);
}
