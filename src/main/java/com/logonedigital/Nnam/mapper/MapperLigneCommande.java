package com.logonedigital.Nnam.mapper;

import com.logonedigital.Nnam.dto.LigneCommandeReqDTO;
import com.logonedigital.Nnam.entities.Commande;
import com.logonedigital.Nnam.entities.LigneCommande;
import org.springframework.stereotype.Component;

@Component
public class MapperLigneCommande {
    public LigneCommande toEntity(LigneCommandeReqDTO ligneCommandeReqDTO, Commande commande){
        LigneCommande ligneCommande = new LigneCommande();
        if (ligneCommandeReqDTO.commandeId() == null) {
            throw new IllegalArgumentException("L'ID de la commande ne peut pas être nul");
        }
        ligneCommande.setLigneId(ligneCommandeReqDTO.ligneId());
        ligneCommande.setTotalLigne(ligneCommandeReqDTO.totalLigne());
        ligneCommande.setQuantite(ligneCommandeReqDTO.quantite());
        ligneCommande.setPrixUnitaire(ligneCommandeReqDTO.prixUnitaire());
        ligneCommande.setTotalLigne(ligneCommandeReqDTO.quantite() * ligneCommandeReqDTO.prixUnitaire());
        ligneCommande.setCommande(commande);
        return  ligneCommande;
    }

}
