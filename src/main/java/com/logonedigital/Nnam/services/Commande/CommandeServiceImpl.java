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

        // 📌 Mettre à jour une commande (Version avec `Optional`)
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

        }

        // 📌 Obtenir une commande par ID
        @Override
        public Commande getCommande (Integer commandeId){
            return this.commandeRepo.findById(commandeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée !"));
        }


            // 📌 Supprimer une commande
            @Override
            public boolean DeleteCommande (Integer commandeId){
                if (commandeRepo.existsById(commandeId)) {
                    commandeRepo.deleteById(commandeId);
                    return true;
                }
                return false;

            }

            // 📌 Lister toutes les commandes
            @Override
            public List<Commande> listerCommandes () {
                return commandeRepo.findAll();
            }




}