package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.dto.categorie.CategorieReqDTO;
import com.logonedigital.Nnam.dto.categorie.CategorieResDTO;
import com.logonedigital.Nnam.entities.Categorie;
import com.logonedigital.Nnam.entities.Produit;
import com.logonedigital.Nnam.exception.ResourceExistException;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.mapper.CategorieMapper;
import com.logonedigital.Nnam.services.Categorie.CategorieService;
import jakarta.validation.Valid;
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
            throw new ResourceExistException("Cette categorie existe deja");
        }
        Categorie savedCategorie = categorieService.addCategorie(categorieReqDTO);

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
    public ResponseEntity<CategorieResDTO> getCategorie(@PathVariable int id) {
        CategorieResDTO categorieResDTO = categorieService.getCategorie(id);
        return ResponseEntity.status(200).body(categorieResDTO);
    }

    // Récupérer toutes les catégories
    @GetMapping
    public ResponseEntity<List<CategorieResDTO>> getAllCategories() {
        List<CategorieResDTO> categories = categorieService.getAllCategories();
        return ResponseEntity.status(200).body(categories);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Categorie>> searchCategories(
            @RequestParam(required = false) String nomCat,
            @RequestParam(required = false) String description,
            @RequestParam(required = false, defaultValue = "0") Integer minProduits){
        return ResponseEntity.ok(categorieService.searchCategories(nomCat, description, minProduits));
    }

}