package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.dto.LivreurReqDTO;
import com.logonedigital.Nnam.dto.LivreurResDTO;
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
        public ResponseEntity<String> addLivreur(@Valid @RequestBody LivreurReqDTO livreurReqDTO ){
            this.livreurService.addLivreur(livreurReqDTO);
        return ResponseEntity
                .status(200)
                .body("Livreur ajoute!");

    }

    @PutMapping(path = "api/livreur/update/{idLivreur}")
    public ResponseEntity<String> update (Integer idLivreur, @Valid @RequestBody LivreurReqDTO livreurReqDTO){
        this.livreurService.updateLivreur(idLivreur, livreurReqDTO);

        return ResponseEntity
                .status(200)
                .body("Livreur modifie!");
    }

    @GetMapping(path = "api/livreur/getById/{idLivreur}")
    public ResponseEntity<LivreurResDTO> getByid (Integer idLivreur){


        return ResponseEntity
                .status(200)
                .body(this.livreurService.getById(idLivreur));
    }

    @GetMapping(path = "api/livreur/getAll")
    public ResponseEntity<List<LivreurResDTO>> getAll(){


        return ResponseEntity
                .status(200)
                .body(this.livreurService.getAllLivreur());
    }
}
