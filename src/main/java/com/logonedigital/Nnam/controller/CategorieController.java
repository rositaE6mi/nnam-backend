package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.entities.Categorie;
import com.logonedigital.Nnam.services.Categorie.CategorieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {

    @Autowired
    private CategorieService categorieService;

    // Ajouter une catégorie
    @PostMapping
    public ResponseEntity<Categorie> addCategorie(@Valid @RequestBody Categorie categorie) {
        Categorie savedCategorie = categorieService.addCategorie(categorie);
        return ResponseEntity.status(201).body(savedCategorie);
    }

    // Mettre à jour une catégorie
    @PutMapping("/{id}")
    public ResponseEntity<Categorie> updateCategorie(
            @PathVariable int id,
            @Valid @RequestBody Categorie categorie) {
        Categorie updatedCategorie = categorieService.updateCategorie(id, categorie);
        return ResponseEntity.status(200).body(updatedCategorie);
    }

    // Supprimer une catégorie
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable int id) {
        categorieService.deleteCategorie(id);
        return ResponseEntity.status(204).build();
    }

    // Récupérer une catégorie par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Categorie> getCategorie(@PathVariable int id) {
        Categorie categorie = categorieService.getCategorie(id);
        return ResponseEntity.status(200).body(categorie);
    }

    // Récupérer toutes les catégories
    @GetMapping
    public ResponseEntity<List<Categorie>> getAllCategories() {
        List<Categorie> categories = categorieService.getAllCategories();
        return ResponseEntity.status(200).body(categories);
    }
}