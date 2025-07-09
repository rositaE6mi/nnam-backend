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
    private final FactureService factureService;
    private final MapperCommande mapperCommande;

    public CommandeServiceImpl(CommandeRepo commandeRepo, FactureService factureService, MapperCommande mapperCommande) {
        this.commandeRepo = commandeRepo;
        this.factureService = factureService;
        this.mapperCommande = mapperCommande;
    }

    // 📌 Ajouter une commande avec génération de facture
    @Override
    public void addCommande(@Valid Commande commande) {
        Optional<Commande> commandeToSave = commandeRepo.findByReference(commande.getReference());
        if (commandeToSave.isPresent()) {
            throw new ResourceExistException("Cette commande existe déjà !");
        }

        commande.setCreatedAt(new Date());
        commande.setStatus("EN_COURS");

        Commande savedCommande = commandeRepo.save(commande);

        Facture facture = new Facture();
        facture.setCommande(savedCommande);
        facture.setMontantTotal(savedCommande.getTotal());
        facture.setDateFacturation(new Date());
        facture.setStatut("NON_PAYEE");

        factureService.addfacture(facture);
    }

    // 📌 Mettre à jour une commande
    @Override
    public void UpdateCommande(Integer commandeId, Commande commande) {
        Commande commandeToUpdate = commandeRepo.findById(commandeId)
                .orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée !"));

        commandeToUpdate.setDateCommande(commande.getDateCommande());
        commandeToUpdate.setTotal(commande.getTotal());
        commandeToUpdate.setStatus(commande.getStatus());
        commandeToUpdate.setUpdatedAt(new Date());

        commandeRepo.saveAndFlush(commandeToUpdate);
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

    // 📌 Récupérer une commande brute
    @Override
    public Commande getCommande(Integer commandeId) {
        return commandeRepo.findById(commandeId)
                .orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée !"));
    }

    // 📌 Obtenir une commande sous forme de DTO
    @Override
    public CommandeDTO getCommandeDTO(Integer commandeId) {
        Commande commande = commandeRepo.findById(commandeId)
                .orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée !"));

        return new CommandeDTO(
                commande.getCommandeId(),
                commande.getReference(),
                commande.getDateCommande(),
                commande.getStatus(),
                commande.getTotal()
        );
    }

    // 📌 Lister toutes les commandes
    @Override
    public List<CommandeDTO> listerCommandes() {
        return commandeRepo.findAll()
                .stream()
                .map(commande -> new CommandeDTO(
                        commande.getCommandeId(),
                        commande.getReference(),
                        commande.getDateCommande(),
                        commande.getStatus(),
                        commande.getTotal()
                ))
                .collect(Collectors.toList());
    }

    // 📌 Récupérer une commande par ID (Optionnel)
    @Override
    public Optional<Commande> getCommandeById(Integer id) {
        return commandeRepo.findById(id);
    }
}
