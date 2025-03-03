package com.logonedigital.Nnam.services.Commande;

import com.logonedigital.Nnam.entities.Commande;
import com.logonedigital.Nnam.exception.ResourceExistException;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.repository.CommandeRepo;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepo commandeRepo;

    public CommandeServiceImpl(CommandeRepo commandeRepo) {
        this.commandeRepo = commandeRepo;
    }


    // 📌 Ajouter une nouvelle commande
<<<<<<< HEAD
    @Override
    public void addCommande(@Valid Commande commande) {
        Optional<Commande> commandeToSave = this.commandeRepo.findByReference(commande.getReference());
        if (commandeToSave.isPresent()) {
            throw new ResourceExistException("Cette commande existe déjà !");
        }

        commande.setCreatedAt(new Date());
        commande.setStatus("EN_COURS"); // Exemple de statut par défaut
        this.commandeRepo.save(commande);
    }


    @Override
    public void UpdateCommande(Integer commandeId, Commande commande) {
        // Vérifier si la commande existe
        Commande commandeToUpdate = this.commandeRepo.findById(commandeId)
                .orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée !"));

        // Mettre à jour la commande existante
        commandeToUpdate.setDateCommande(commande.getDateCommande());
        commandeToUpdate.setTotal(commande.getTotal());
        commandeToUpdate.setStatus(commande.getStatus());
        commandeToUpdate.setUpdatedAt(new Date());

        // Sauvegarder les modifications
        this.commandeRepo.saveAndFlush(commandeToUpdate);
=======


    @Override
    public void addCommande(Commande commande) {
        commandeRepo.save(commande);
>>>>>>> origin/lamairie
    }

    // 📌 Mettre à jour une commande (Version avec `Optional`)
    @Override
<<<<<<< HEAD
    public Commande getCommande(Integer commandeId) {
        return this.commandeRepo.findById(commandeId)
                .orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée !"));
    }


    @Override
    public boolean DeleteCommande(Integer commandeId) {
        Optional<Commande> commande = this.commandeRepo.findById(commandeId);
        if (commande.isPresent()) {
            this.commandeRepo.delete(commande.get());
            return true; // ✅ Retourne vrai si supprimée
        }
        return false; // ✅ Retourne faux si commande non trouvée
=======
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
>>>>>>> origin/lamairie
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
