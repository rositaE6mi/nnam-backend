package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.dto.categorie.CategorieReqDTO;
import com.logonedigital.Nnam.dto.categorie.CategorieResDTO;
import com.logonedigital.Nnam.dto.produit.ProduitResDTO;
import com.logonedigital.Nnam.entities.Categorie;
import com.logonedigital.Nnam.entities.Produit;
import com.logonedigital.Nnam.exception.ResourceExistException;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.mapper.CategorieMapper;
import com.logonedigital.Nnam.services.Categorie.CategorieService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        /*if (categorieService.existsByNomCat(categorieReqDTO.getNomCat())){
            throw new ResourceExistException("Cette categorie existe deja");
        }*/
        Categorie savedCategorie = categorieService.addCategorie(categorieReqDTO);

        CategorieResDTO response = categorieMapper.getCategorieResDTOFromCategorie(savedCategorie);
        return ResponseEntity
                .status(201).
                body(response);
    }

    // Mettre à jour une catégorie
    @PutMapping("/{id}")
    public ResponseEntity<CategorieResDTO> updateCategorie(
            @PathVariable int id,
            @Valid @RequestBody CategorieReqDTO categorie) {
        CategorieResDTO updatedCategorie = categorieService.updateCategorie(id, categorie);
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
        List<Categorie> categories = new ArrayList<>();
        return ResponseEntity
                .status(200)
                .body(this.categorieService.getAllCategories(categories));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Categorie>> searchCategories(
            @RequestParam(required = false) String nomCat,
            @RequestParam(required = false) String description,
            @RequestParam(required = false, defaultValue = "0") Integer minProduits){
        return ResponseEntity.ok(categorieService.searchCategories(nomCat, description, minProduits));
    }

    @GetMapping("pagination et tri/get_all")
    public ResponseEntity<Page<Categorie>> getAllCategorie(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nomCat") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.ok(categorieService.getAllCategorie(pageable));
    }

}