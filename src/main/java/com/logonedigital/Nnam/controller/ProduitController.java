package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.dto.PdfExportConfigDTO;
import com.logonedigital.Nnam.dto.produit.ProduitReqDTO;
import com.logonedigital.Nnam.dto.produit.ProduitResDTO;
import com.logonedigital.Nnam.entities.Produit;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.services.Produit.ProduitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {
    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @PostMapping("/add")
    public ResponseEntity<Produit> addProduit(@Valid @RequestBody ProduitReqDTO produitReqDTO) {
        Produit savedProduit = produitService.addProduit(produitReqDTO);
        return ResponseEntity
                .status(201)
                .body(savedProduit);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProduitResDTO> updateProduit(@PathVariable int id, @RequestBody ProduitReqDTO produit) {
        ProduitResDTO updatedProduit = produitService.updateProduit(id, produit);
        return ResponseEntity
                .status(200).
                body(updatedProduit);
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
    public ResponseEntity<List<ProduitResDTO>> getAllProduits() {
        List<Produit> produit = new ArrayList<>();
        return ResponseEntity
                .status(200).
                body(this.produitService.getAllProduits());
    }
    @GetMapping("/search")
    public ResponseEntity<List<Produit>> searchProduits(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        return ResponseEntity.ok(produitService.search(nom, minPrice, maxPrice));
    }

    @GetMapping("pagination et tri/get_all")
    public ResponseEntity<Page<Produit>> getAllProduit(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nomProduit") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.ok(produitService.getAllProduit(pageable));
    }

    @PostMapping("/export-pdf")
    public ResponseEntity<?> exportProduitsToPdf(
            @RequestBody(required = false) PdfExportConfigDTO config) { // DTO optionnel

        // Créer une config par défaut si non fournie
        if (config == null) {
            config = new PdfExportConfigDTO(); // Utilise les valeurs par défaut
        }

        try {
            byte[] pdfBytes = produitService.generateProduitsPdfReport(config);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=rapport.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);

        } catch (Exception ex) {
            return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));
        }
    }
}