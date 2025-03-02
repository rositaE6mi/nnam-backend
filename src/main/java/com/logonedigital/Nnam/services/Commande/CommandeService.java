package com.logonedigital.Nnam.services.Commande;

import com.logonedigital.Nnam.entities.Commande;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CommandeService {
    void addCommande(@Valid Commande commande);
    void UpdateCommande(Integer commandeId, Commande commande);
    Commande getCommande(Integer id);
    boolean DeleteCommande(Integer commandeId);
    List<Commande> listerCommandes();

    // 📌 Obtenir une commande par ID
    Optional<Commande> getCommandeById(Integer commandeId);
}
