package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.dto.produit.ProduitReqDTO;
import com.logonedigital.Nnam.dto.produit.ProduitResDTO;
import com.logonedigital.Nnam.entities.Produit;
import com.logonedigital.Nnam.services.Produit.ProduitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @PostMapping("/add")
    public ResponseEntity<Produit> addProduit(@Valid @RequestBody ProduitReqDTO produitReqDTO) {
        Produit savedProduit = produitService.addProduit(produitReqDTO);
        return ResponseEntity.status(201).body(savedProduit);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable int id, @RequestBody Produit produit) {
        Produit updatedProduit = produitService.updateProduit(id, produit);
        return ResponseEntity.status(200).body(updatedProduit);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable int id) {
        produitService.deleteProduit(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProduitResDTO> getProduit(@PathVariable int id) {
        ProduitResDTO produit = produitService.getProduit(id);
        return ResponseEntity.status(200).body(produit);
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<Produit>> getAllProduits() {
        List<Produit> produits = produitService.getAllProduits();
        return ResponseEntity.status(200).body(produits);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Produit>> searchProduits(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        return ResponseEntity.ok(produitService.search(nom, minPrice, maxPrice));
    }

    @GetMapping("pagination et tri/get_all")
    public ResponseEntity<Page<Produit>> getAllProduits(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nomProduit") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.ok(produitService.getAllProduits(pageable));
    }

}