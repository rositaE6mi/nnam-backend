package com.logonedigital.Nnam.services.LigneCommande;

import com.logonedigital.Nnam.entities.LigneCommande;

import java.util.List;
import java.util.Optional;

public interface LigneCommandeService {
    // 📌 Ajouter une ligne de commande
    LigneCommande ajouterLigneCommande(LigneCommande ligneCommande);

    // 📌 Obtenir une ligne de commande par ID
    Optional<LigneCommande> obtenirLigneCommandeParId(Integer id);

    // 📌 Lister toutes les lignes de commande
    List<LigneCommande> listerToutesLesLignesCommandes();

    // 📌 Mettre à jour une ligne de commande
    LigneCommande mettreAJourLigneCommande(Integer id, LigneCommande ligneCommande);

    // 📌 Supprimer une ligne de commande
    boolean supprimerLigneCommande(Integer id);
}
