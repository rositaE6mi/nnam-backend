package com.logonedigital.Nnam.mapper;

import com.logonedigital.Nnam.dto.CommandeReqDTO;
import com.logonedigital.Nnam.entities.Commande;
import org.springframework.stereotype.Component;

@Component
// 🔹 Convertir un CommandeRequestDTO en une entité Commande
public class MapperCommande {
    public Commande toEntity (CommandeReqDTO commandeReqDTO){
        Commande commande = new Commande();
        commande.setCommandeId(commandeReqDTO.commandeId());
        commande.setDateCommande(commandeReqDTO.dateCommande());
        commande.setReference(commandeReqDTO.reference());
        commande.setStatus(commandeReqDTO.status());
        commande.setTotal(commandeReqDTO.total());
        return commande;
    }

}
