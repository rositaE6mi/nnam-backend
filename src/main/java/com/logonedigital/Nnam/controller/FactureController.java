package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.dto.FactureDTO;
import com.logonedigital.Nnam.entities.Facture;
import com.logonedigital.Nnam.services.Facture.FactureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/factures")
@Validated // Optionnel : permet d'activer la validation sur toute la classe
public class FactureController {

    private final FactureService factureService;

    @Autowired
    public FactureController(FactureService factureService) {
        this.factureService = factureService;
    }

    // 📌 Créer une nouvelle facture
    @PostMapping
    public ResponseEntity<Facture> addFacture(@Valid@ RequestBody Facture facture){
       Facture newFacture = factureService.addfacture(facture);
       return ResponseEntity.ok(newFacture);
    }
    // 📌 Obtenir une facture par ID
    @GetMapping("/{id}")
    public ResponseEntity<FactureDTO> getFacture(@PathVariable Integer id){
        Optional<FactureDTO> facture = factureService.getFacture(id);
        return facture.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    // 📌 Lister toutes les factures
    @GetMapping
    public ResponseEntity<List<FactureDTO>> ListerFactures() {
        // Appel du service pour obtenir la liste des Factures
        List<FactureDTO> factures = factureService.ListerFactures();
        // Vérification si la liste est vide
        if (factures.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retourne un status 204 No Content si vide
        }
        // Sinon, retourne la liste de FactureDTO avec un statut 200 OK
        return ResponseEntity.ok(factures);
    }

    // 📌 Mettre à jour une facture
    @PutMapping("/{id}")
    public ResponseEntity<Facture> UpdateFacture(@PathVariable Integer id, @RequestBody Facture factures){
        Facture updateFacture = factureService.Updatefacture(id, factures);
        return ResponseEntity.ok(updateFacture);

    }

    // 📌 Supprimer une facture
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteFacture(@PathVariable("id") Integer id) {
        boolean deleted = factureService.DeleteFacture(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    }
