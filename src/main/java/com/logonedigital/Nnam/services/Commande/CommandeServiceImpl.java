package com.logonedigital.Nnam.services.Commande;

import com.logonedigital.Nnam.entities.Commande;
import com.logonedigital.Nnam.repository.CommandeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepo commandeRepo;

    public CommandeServiceImpl(CommandeRepo commandeRepo) {
        this.commandeRepo = commandeRepo;
    }


    // 📌 Ajouter une nouvelle commande


    @Override
    public void addCommande(Commande commande) {
        commandeRepo.save(commande);
    }

    // 📌 Mettre à jour une commande (Version avec `Optional`)
    @Override
    public Optional<Commande> UpdateCommande(Integer commandeId, Commande commande) {
        return commandeRepo.findById(commandeId).map(existingCommande -> {
            existingCommande.setDateCommande(commande.getDateCommande());
            existingCommande.setStatus(commande.getStatus());
            existingCommande.setTotal(commande.getTotal());
            existingCommande.setFacture(commande.getFacture());
            existingCommande.setLigneCommande(commande.getLigneCommande());
            return Optional.of(commandeRepo.save(existingCommande));
        }).orElse(Optional.empty());
    }

    // 📌 Supprimer une commande
    @Override
    public boolean DeleteCommande(Integer commandeId) {
        if (commandeRepo.existsById(commandeId)) {
            commandeRepo.deleteById(commandeId);
            return true;
        }
        return false;
    }

    // 📌 Lister toutes les commandes
    @Override
    public List<Commande> listerCommandes() {
        return commandeRepo.findAll();
    }

    // 📌 Obtenir une commande par ID
    @Override
    public Optional<Commande> getCommandeById(Integer commandeId) {
        return commandeRepo.findById(commandeId);
    }

}
