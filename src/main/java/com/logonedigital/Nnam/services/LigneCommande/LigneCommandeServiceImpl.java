package com.logonedigital.Nnam.services.LigneCommande;

import com.logonedigital.Nnam.dto.LigneCommandeDTO;
import com.logonedigital.Nnam.dto.LigneCommandeReqDTO;
import com.logonedigital.Nnam.entities.Commande;
import com.logonedigital.Nnam.entities.LigneCommande;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.mapper.MapperLigneCommande;
import com.logonedigital.Nnam.repository.LigneCommandeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LigneCommandeServiceImpl implements LigneCommandeService {

    private final LigneCommandeRepo ligneCommandeRepo;
    private final MapperLigneCommande mapperLigneCommande;

    public LigneCommandeServiceImpl(LigneCommandeRepo ligneCommandeRepo, MapperLigneCommande mapperLigneCommande) {
        this.ligneCommandeRepo = ligneCommandeRepo;
        this.mapperLigneCommande = mapperLigneCommande;
    }

    // 📌 Ajouter une ligne de commande
    @Override
    public LigneCommande ajouterLigneCommande(LigneCommande ligneCommande) {
        ligneCommande.setTotalLigne(ligneCommande.getQuantite() * ligneCommande.getPrixUnitaire());
        return ligneCommandeRepo.save(ligneCommande);
    }

    // 📌 Obtenir une ligne de commande par ID
    @Override
    public Optional<LigneCommandeDTO> obtenirLigneCommandeParId(Integer id) {
        return ligneCommandeRepo.findById(id)
                .map(ligneCommande -> new LigneCommandeDTO(
                        ligneCommande.getLigneId(),
                        ligneCommande.getQuantite(),
                        ligneCommande.getPrixUnitaire(),
                        ligneCommande.getTotalLigne()
                )); // ✅ Retourne bien un Optional<LigneCommandeDTO>
    }


    // 📌 Lister toutes les lignes de commande
    @Override
    public List<LigneCommandeDTO> listerToutesLesLignesCommandes() {

        return ligneCommandeRepo.findAll()
                .stream().map(LigneCommande -> new LigneCommandeDTO(
                        LigneCommande.getLigneId(),
                        LigneCommande.getQuantite(),
                        LigneCommande.getPrixUnitaire(),
                        LigneCommande.getTotalLigne()))
                .collect(Collectors.toList());
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
