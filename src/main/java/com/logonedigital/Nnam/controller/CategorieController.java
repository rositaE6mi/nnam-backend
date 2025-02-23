package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.entities.Categorie;
import com.logonedigital.Nnam.entities.Produit;
import com.logonedigital.Nnam.services.Categorie.CategorieService;
import com.logonedigital.Nnam.services.Produit.ProduitService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategorieController {
    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {

        this.categorieService = categorieService ;
    }

    @PostMapping(path = "api/categorie/add")
    public ResponseEntity<String> addCategorie(@Valid @RequestBody Categorie categorie) {
        this.categorieService.addCategorie(categorie);
        return ResponseEntity
                .status(201)
                .body("Created successfully !");
    }

    @GetMapping(path = "api/categorie/get_all")
    public ResponseEntity<List<Categorie>> getAllCategories(){
        return ResponseEntity
                .status(200)
                .body(this.categorieService.getAllCategories());
    }

    @GetMapping(path = "api/categorie/get_by_id/{id}")
    public ResponseEntity<Categorie> getCategorieById(@Parameter(description = "ID de categorie")@PathVariable Integer id) {
        return ResponseEntity
                .status(200)
                .body(this.categorieService.getCategorieById(id));
    }

    @PutMapping(path = "api/categorie/update_by_id/{id}")
    public ResponseEntity<String> updateCategorie(@PathVariable Integer id, @RequestBody Categorie categorie) {
        return ResponseEntity
                .status(202)
                .body("Update successfully");
    }

    @DeleteMapping(path = "api/categorie/delete_by_id/{id}")
    public ResponseEntity<String> deleteCategorie(@Parameter(description = "ID de categorie") @PathVariable Integer id) {
        return ResponseEntity
                .status(202)
                .body("Delete successfully");
    }

}
