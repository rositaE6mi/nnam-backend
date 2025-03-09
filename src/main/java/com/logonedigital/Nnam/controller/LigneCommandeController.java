package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.dto.LigneCommandeDTO;
import com.logonedigital.Nnam.entities.LigneCommande;
import com.logonedigital.Nnam.services.LigneCommande.LigneCommandeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lignes-commandes")
@Validated // Optionnel : permet d'activer la validation sur toute la classe
public class LigneCommandeController {

    private final LigneCommandeService ligneCommandeService;

    public LigneCommandeController(LigneCommandeService ligneCommandeService) {
        this.ligneCommandeService = ligneCommandeService;
    }

    // 📌 Ajouter une ligne de commande
    @PostMapping
    public ResponseEntity<LigneCommande> ajouterLigneCommande(@RequestBody LigneCommande ligneCommande) {
        LigneCommande nouvelleLigne = ligneCommandeService.ajouterLigneCommande(ligneCommande);
        return ResponseEntity.ok(nouvelleLigne);
    }

    // 📌 Obtenir une ligne de commande par ID
    @GetMapping("/{id}")
    public ResponseEntity<LigneCommandeDTO> obtenirLigneCommandeParId(@PathVariable Integer id) {
        Optional<LigneCommandeDTO> ligneCommande = ligneCommandeService.obtenirLigneCommandeParId(id);
        return ligneCommande.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 📌 Lister toutes les lignes de commande
    @GetMapping
    public ResponseEntity<List<LigneCommandeDTO>> listerToutesLesLignesCommandes() {
        List<LigneCommandeDTO> lignes = ligneCommandeService.listerToutesLesLignesCommandes();
        return lignes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lignes);
    }

    // 📌 Mettre à jour une ligne de commande
    @PutMapping("/{id}")
    public ResponseEntity<LigneCommande> mettreAJourLigneCommande(@PathVariable Integer id, @RequestBody LigneCommande ligneCommande) {
        LigneCommande updatedLigne = ligneCommandeService.mettreAJourLigneCommande(id, ligneCommande);
        return ResponseEntity.ok(updatedLigne);
    }

    // 📌 Supprimer une ligne de commande
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerLigneCommande(@PathVariable Integer id) {
        boolean deleted = ligneCommandeService.supprimerLigneCommande(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
