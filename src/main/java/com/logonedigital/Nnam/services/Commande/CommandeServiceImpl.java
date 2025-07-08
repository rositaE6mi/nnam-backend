package com.logonedigital.Nnam.services.Commande;

import com.logonedigital.Nnam.dto.CommandeDTO;
import com.logonedigital.Nnam.entities.Commande;
import com.logonedigital.Nnam.entities.Facture;
import com.logonedigital.Nnam.exception.ResourceExistException;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.mapper.MapperCommande;
import com.logonedigital.Nnam.repository.CommandeRepo;
import com.logonedigital.Nnam.services.Facture.FactureService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepo commandeRepo;
    private final FactureService factureService; // Injection du service Facture
    private final MapperCommande mapperCommande;

    public CommandeServiceImpl(CommandeRepo commandeRepo, FactureService factureService, MapperCommande mapperCommande) {
        this.commandeRepo = commandeRepo;
        this.factureService = factureService;
        this.mapperCommande = mapperCommande;
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
       Commande savedCommande = this.commandeRepo.save(commande);

        // ✅ Génération automatique de la facture après l'enregistrement de la commande
        Facture facture = new Facture();
        facture.setCommande(savedCommande);
        facture.setMontantTotal(savedCommande.getTotal());
        facture.setDateFacturation(new Date());
        facture.setStatut("NON_PAYEE"); // Statut par défaut

        factureService.addfacture(facture); // Appel du service pour sauvegarder la facture
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


        // Sauvegarder les modifications
        this.commandeRepo.saveAndFlush(commandeToUpdate);



    @Override
    public void addCommande(Commande commande) {
        commandeRepo.save(commande);

    }

    // 📌 Mettre à jour une commande (Version avec `Optional`)
    @Override

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
        }

        // 📌 Obtenir une commande par ID
        @Override
        public CommandeDTO getCommandeDTO(Integer commandeId) {
            // Récupérer la commande
            Commande commande = this.commandeRepo.findById(commandeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée !"));

            // Mapper la Commande en CommandeDTO

            // Retourner le DTO
            return new CommandeDTO(
                    commande.getCommandeId(),
                    commande.getReference(),
                    commande.getDateCommande(),
                    commande.getStatus(),
                    commande.getTotal()
            );
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
            public List<CommandeDTO> listerCommandes() {
                return commandeRepo.findAll()
                        .stream()
                        .map(commande -> new CommandeDTO( // ✅ Conversion correcte
                                commande.getCommandeId(),
                                commande.getReference(),
                                commande.getDateCommande(),
                                commande.getStatus(),
                                commande.getTotal()
                        ))
                        .collect(Collectors.toList()); // ✅ Retourne une List<CommandeDTO>

            }

    @Override
    public Optional<Commande> getCommandeById(Integer id) {
        return commandeRepo.findById(id);
    }
}