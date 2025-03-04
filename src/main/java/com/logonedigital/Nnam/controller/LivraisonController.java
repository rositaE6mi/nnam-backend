package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.entities.Livraison;
import com.logonedigital.Nnam.services.livraison.LivraisonService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LivraisonController {
    private final LivraisonService livraisonService;

    public LivraisonController(LivraisonService livraisonService) {
        this.livraisonService = livraisonService;
    }



    @PostMapping(path = "api/livraison/add")
    public ResponseEntity<String> addLivraison(@Valid @RequestBody Livraison livraison){
        this.livraisonService.addLivraison(livraison);

        return  ResponseEntity
                .status(200)
                .body("Livraison ajoute!");
    }

    @PutMapping(path = "api/livraison/deleteById/{idLivraison}")
    public ResponseEntity<String> deleteLivraison(@PathVariable  Integer idLivraison){
        this.livraisonService.deleteLivraison(idLivraison);
        return ResponseEntity
                .status(202)
                .body("Ressource supprimee !");
    }

    @PutMapping(path = "api/livraison/update/{idLivraison}")
    public ResponseEntity<String>  updateLivraison(Integer idLivraison, @Valid @RequestBody Livraison livraison){
        this.livraisonService.updateLivraison(idLivraison, livraison);

        return ResponseEntity
                .status(202)
                .body("Ressource modifiee !");
    }


    @GetMapping(path = "api/livraison/get_by_id'{idLivraison}")
    public ResponseEntity<Livraison> getbyid(Integer idLivraison){
        return ResponseEntity
                .status(200)
                .body(this.livraisonService.getLivriasonById(idLivraison));

    }

    @GetMapping(path = "api/livraison/getAll")
    public ResponseEntity<List<Livraison>> listLivraison(){
        return ResponseEntity
                .status(200)
                .body(this.livraisonService.getAllLivraison());
    }
}
