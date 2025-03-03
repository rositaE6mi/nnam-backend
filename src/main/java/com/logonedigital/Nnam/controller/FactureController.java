package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.entities.Facture;
import com.logonedigital.Nnam.services.Facture.FactureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/factures")
public class FactureController {
    @Autowired
    private FactureService factureService;

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
    public ResponseEntity<Facture> getFacture(@PathVariable Integer id){
        Optional<Facture> facture = factureService.getFacture(id);
        return facture.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    // 📌 Lister toutes les factures
    @GetMapping
    public ResponseEntity<List<Facture>> ListerFactures(){
        List<Facture> factures = factureService.ListerFactures();
        return factures.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(factures);
    }
    // 📌 Mettre à jour une facture
    @PutMapping("/{id}")
    public ResponseEntity<Facture> UpdateFacture(@PathVariable Integer id, @RequestBody Facture factures){
        Facture updateFacture = factureService.Updatefacture(id, factures);
        return ResponseEntity.ok(updateFacture);

    }
    // 📌 Supprimer une facture
    // 📌 Supprimer une facture
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteFacture(@PathVariable("id") Integer id) {
        boolean deleted = factureService.DeleteFacture(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    }
