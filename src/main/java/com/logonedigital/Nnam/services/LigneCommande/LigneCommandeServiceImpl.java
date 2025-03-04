package com.logonedigital.Nnam.services.LigneCommande;

import com.logonedigital.Nnam.entities.LigneCommande;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.repository.LigneCommandeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LigneCommandeServiceImpl implements LigneCommandeService {

    private final LigneCommandeRepo ligneCommandeRepo;

    public LigneCommandeServiceImpl(LigneCommandeRepo ligneCommandeRepo) {
        this.ligneCommandeRepo = ligneCommandeRepo;
    }

    // 📌 Ajouter une ligne de commande
    @Override
    public LigneCommande ajouterLigneCommande(LigneCommande ligneCommande) {
        ligneCommande.setTotalLigne(ligneCommande.getQuantite() * ligneCommande.getPrixUnitaire());
        return ligneCommandeRepo.save(ligneCommande);
    }

    // 📌 Obtenir une ligne de commande par ID
    @Override
    public Optional<LigneCommande> obtenirLigneCommandeParId(Integer id) {
        return ligneCommandeRepo.findById(id);
    }

    // 📌 Lister toutes les lignes de commande
    @Override
    public List<LigneCommande> listerToutesLesLignesCommandes() {
        return ligneCommandeRepo.findAll();
    }

    // 📌 Mettre à jour une ligne de commande
    @Override
    public LigneCommande mettreAJourLigneCommande(Integer id, LigneCommande ligneCommande) {
        return ligneCommandeRepo.findById(id).map(existingLigne -> {
            existingLigne.setQuantite(ligneCommande.getQuantite());
            existingLigne.setPrixUnitaire(ligneCommande.getPrixUnitaire());
            existingLigne.setTotalLigne(ligneCommande.getQuantite() * ligneCommande.getPrixUnitaire());
            return ligneCommandeRepo.save(existingLigne);
        }).orElseThrow(() -> new ResourceNotFoundException("LigneCommande non trouvée avec ID : " + id));
    }

    // 📌 Supprimer une ligne de commande
    @Override
    public boolean supprimerLigneCommande(Integer id) {
        if (ligneCommandeRepo.existsById(id)) {
            ligneCommandeRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
