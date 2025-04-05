package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.dto.LivraisonResDTO;
import com.logonedigital.Nnam.dto.LivreurReqDTO;
import com.logonedigital.Nnam.dto.LivreurResDTO;
import com.logonedigital.Nnam.dto.PageDTO;
import com.logonedigital.Nnam.entities.Livraison;
import com.logonedigital.Nnam.entities.Livreur;
import com.logonedigital.Nnam.mapper.LivreurMapper;
import com.logonedigital.Nnam.repository.LivreurRepo;
import com.logonedigital.Nnam.services.livreur.LivreurService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LivreurController {
    private final LivreurService livreurService;
    private final LivreurRepo livreurRepo;
    private final LivreurMapper livreurMapper;


    public LivreurController(LivreurService livreurService, LivreurRepo livreurRepo, LivreurMapper livreurMapper) {
        this.livreurService = livreurService;
        this.livreurRepo = livreurRepo;
        this.livreurMapper = livreurMapper;
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

    @PostMapping(path = "api/livreur/pagination")
    public Page<LivreurResDTO> getAllLivraisonpagination(@RequestBody PageDTO pageDTO) {
        Pageable pageable = pageDTO.getPageable(pageDTO);
        Page<Livreur> livreurs = this.livreurRepo.findAll(pageable);

        return livreurs.map(livreurMapper::getLivreurResDTOFromLivreur);
    }


    @PostMapping("api/livreur/search")
    public ResponseEntity<List<LivreurResDTO>> searchLivreurs(@RequestBody LivreurReqDTO searchDTO) {
        List<LivreurResDTO> result = livreurService.searchLivreurs(searchDTO);
        return ResponseEntity.ok(result);
    }
}
