package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.dto.CategorieReqDTO;
import com.logonedigital.Nnam.dto.CategorieResDTO;
import com.logonedigital.Nnam.entities.Categorie;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.mapper.CategorieMapper;
import com.logonedigital.Nnam.services.Categorie.CategorieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {


    private final CategorieService categorieService;
    private final CategorieMapper categorieMapper;

    public CategorieController(CategorieService categorieService, CategorieMapper categorieMapper) {
        this.categorieService = categorieService;
        this.categorieMapper = categorieMapper;
    }

    // Ajouter une catégorie
    @PostMapping
    public ResponseEntity<CategorieResDTO> addCategorie(@Valid @RequestBody CategorieReqDTO categorieReqDTO) {
        //verifions l'existence du nom
        if (categorieService.existsByNomCat(categorieReqDTO.getNomCat())){
            throw new ResourceNotFoundException("Cette categorie existe deja");
        }

        //conversion DTO -> Entity
        Categorie categorie = categorieMapper.getCategorieFromCategorieReqDTO(categorieReqDTO);
        //sauvegarde
        Categorie savedCategorie = categorieService.addCategorie(categorie);
        //conversion entity->DTO
        CategorieResDTO response = categorieMapper.getCategorieResDTOFromCategorie(savedCategorie);

        return ResponseEntity
                .status(201).
                body(response);
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