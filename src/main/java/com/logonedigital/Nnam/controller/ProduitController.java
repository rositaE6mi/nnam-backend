package com.logonedigital.Nnam.controller;


import com.logonedigital.Nnam.entities.Produit;
import com.logonedigital.Nnam.services.Produit.ProduitService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/produits")

public class ProduitController {
    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {

        this.produitService = produitService;
    }

    @PostMapping("api/produit/add")
    public ResponseEntity<String> addProduit(@Valid @RequestBody Produit produit) {
        this.produitService.addProduit(produit);
        return ResponseEntity
                .status(201)
                .body("Created successfully !");
    }

    @GetMapping("api/produit/get_all")
    public ResponseEntity<List<Produit>> getAllProduits(){
        return ResponseEntity
                .status(200)
                .body(this.produitService.getAllProduits());
    }

    @GetMapping("api/produit/get_by_id/{id}")
    public ResponseEntity<Produit> getProduitById(@Parameter(description = "ID du produit") @PathVariable Integer id) {
        return ResponseEntity
                .status(200)
                .body(this.produitService.getProduitById(id));
    }

    @PutMapping(path="api/produit/update_by_id/{id}")
    public ResponseEntity<String> updateProduit(@PathVariable Integer id, @RequestBody Produit produit) {
        produitService.updateProduit(id, produit);
        return ResponseEntity
                .status(202)
                .body("Update successfully");
    }

    @DeleteMapping("api/produit/delete_by_id/{id}")
    public ResponseEntity<String> deleteProduit(@Parameter(description = "ID du produit") @PathVariable Integer id) {
        produitService.deleteProduit(id);
        return ResponseEntity
                .status(202)
                .body("Delete successfully");
    }

}
