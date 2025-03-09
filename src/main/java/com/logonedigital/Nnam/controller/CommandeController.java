package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.dto.CommandeDTO;
import com.logonedigital.Nnam.entities.Commande;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.services.Commande.CommandeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/commandes")
@Validated // Optionnel : permet d'activer la validation sur toute la classe
public class CommandeController {

    @Autowired
    private CommandeService commandeService;
    public CommandeController(CommandeService commandeService){
        this.commandeService = commandeService;
    }
    // 📌 Créer une nouvelle commande
    @PostMapping
    public ResponseEntity<String> addCommande(@Valid @RequestBody Commande commande) {
       this.commandeService.addCommande(commande);
        return ResponseEntity
                .status(201)
                .body("commande ajouter avec succes");
    }

    // 📌 Obtenir une commande par ID
    @GetMapping("/{id}")
    public ResponseEntity<CommandeDTO> getCommande(@PathVariable("id") Integer commandeId) {
        CommandeDTO commande = commandeService.getCommandeDTO(commandeId); // ✅ Appelle le service au lieu de lui-même
        return ResponseEntity.ok(commande);
    }
    // 📌 Mettre à jour une commande (200 OK ou 404 Not Found)
    @PutMapping("/{id}")
    public ResponseEntity<?> UpdateCommande(@PathVariable Integer id, @RequestBody Commande commande) {
        try {
            commandeService.UpdateCommande(id, commande);
            return new ResponseEntity<>("Commande mise à jour avec succès", HttpStatus.OK); // Retourne 200 OK
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); // Retourne 404 Not Found avec un message d'erreur
        }
    }

    // 📌 Supprimer une commande (204 No Content ou 404 Not Found)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteCommande(@PathVariable Integer id) {
        boolean deleted = commandeService.DeleteCommande(id);
        if (deleted) {
            return ResponseEntity.ok("La commande a été supprimée avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La commande n'a pas été trouvée");
        }
    }


    // 📌 Lister toutes les commandes (200 OK ou 204 No Content si liste vide)
    @GetMapping
    public ResponseEntity<List<CommandeDTO>> listerCommandes() {
        List<CommandeDTO> commandes = commandeService.listerCommandes(); // ✅ Utilise List<CommandeDTO>
        return commandes.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(commandes);
    }

}
