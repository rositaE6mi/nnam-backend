package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.entities.Livreur;
import com.logonedigital.Nnam.services.livreur.LivreurService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LivreurController {
    private final LivreurService livreurService;


    public LivreurController(LivreurService livreurService) {
        this.livreurService = livreurService;
    }

    @PutMapping(path = "api/livreur/deleteById/{idLivreur}")
    public ResponseEntity<String> deleteLivreur(@PathVariable Integer idLivreur){
        this.livreurService.deleteLivreur(idLivreur);
        return ResponseEntity
                .status(200)
                .body("Livreur supprime!");
    }

    @PostMapping(path = "api/livreur/add")
        public ResponseEntity<String> addLivreur(@Valid @RequestBody Livreur livreur ){
            this.livreurService.addLivreur(livreur);
        return ResponseEntity
                .status(200)
                .body("Livreur ajoute!");

    }

    @PutMapping(path = "api/livreur/update/{idLivreur}")
    public ResponseEntity<String> update (Integer idLivreur, @Valid @RequestBody Livreur livreur){
        this.livreurService.updateLivreur(idLivreur, livreur);

        return ResponseEntity
                .status(200)
                .body("Livreur modifie!");
    }

    @GetMapping(path = "api/livreur/getById/{idLivreur}")
    public ResponseEntity<Livreur> getByid (Integer idLivreur){


        return ResponseEntity
                .status(200)
                .body(this.livreurService.getById(idLivreur));
    }

    @GetMapping(path = "api/livreur/getAll")
    public ResponseEntity<List<Livreur>> getAll(){


        return ResponseEntity
                .status(200)
                .body(this.livreurService.getAllLivreur());
    }
}
