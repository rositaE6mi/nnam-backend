package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.entities.Commande;
import com.logonedigital.Nnam.services.Commande.CommandeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/commandes")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;
    public CommandeController(CommandeService commandeService){
        this.commandeService = commandeService;
    }
    // 📌 Créer une nouvelle commande
    @PostMapping
    public ResponseEntity<String> addCommande(@Valid @RequestBody CommandeService commandeService) {
       this.commandeService.addCommande(commandeService);
        return ResponseEntity
                .status(201)
                .body("commande ajouter avec succes");
    }

    // 📌 Obtenir une commande par ID
    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommande(@PathVariable Integer commandeId) {
        return ResponseEntity
                .status(200)
                .body(this.getCommande(commandeId).getBody());
    }
    // 📌 Mettre à jour une commande (200 OK ou 404 Not Found)
    @PutMapping("/{id}")
    public ResponseEntity<Commande> UpdateCommande(@PathVariable Integer id, @RequestBody Commande commande) {
        Optional<Commande> UpdateCommande = commandeService.UpdateCommande(id, commande);
        return UpdateCommande
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 📌 Supprimer une commande (204 No Content ou 404 Not Found)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteCommande(@PathVariable Integer id) {
        boolean deleted = commandeService.DeleteCommande(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // 📌 Lister toutes les commandes (200 OK ou 204 No Content si liste vide)
    @GetMapping
    public ResponseEntity<List<Commande>> listerCommandes() {
        List<Commande> commandes = commandeService.listerCommandes();
        return commandes.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(commandes, HttpStatus.OK);
    }
}
