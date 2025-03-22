package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.dto.LivraisonReqDTO;
import com.logonedigital.Nnam.dto.LivraisonResDTO;
import com.logonedigital.Nnam.dto.PageDTO;
import com.logonedigital.Nnam.entities.Livraison;
import com.logonedigital.Nnam.entities.Livreur;
import com.logonedigital.Nnam.mapper.LivraisonMapper;
import com.logonedigital.Nnam.repositories.LivraisonRepo;
import com.logonedigital.Nnam.services.livraison.LivraisonService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LivraisonController {
    private final LivraisonService livraisonService;
    private final LivraisonRepo livraisonRepo;
    private final LivraisonMapper livraisonMapper;

    public LivraisonController(LivraisonService livraisonService, LivraisonRepo livraisonRepo, LivraisonMapper livraisonMapper) {
        this.livraisonService = livraisonService;
        this.livraisonRepo = livraisonRepo;
        this.livraisonMapper = livraisonMapper;
    }



    @PostMapping(path = "api/livraison/add")
    public ResponseEntity<String> addLivraison(@Valid @RequestBody LivraisonReqDTO livraisonReqDTO){
        this.livraisonService.addLivraison(livraisonReqDTO);

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
    public ResponseEntity<String>  updateLivraison(Integer idLivraison, @Valid @RequestBody LivraisonReqDTO livraison){
        this.livraisonService.updateLivraison(idLivraison, livraison);

        return ResponseEntity
                .status(202)
                .body("Ressource modifiee !");
    }


    @GetMapping(path = "api/livraison/get_by_id'{idLivraison}")
    public ResponseEntity<LivraisonResDTO> getbyid(Integer idLivraison){
        return ResponseEntity
                .status(200)
                .body(this.livraisonService.getLivriasonById(idLivraison));

    }

    @GetMapping(path = "api/livraison/getAll")
    public ResponseEntity<List<LivraisonResDTO>> listLivraison(){
        return ResponseEntity
                .status(200)
                .body(this.livraisonService.getAllLivraison());
    }

    @PostMapping(path = "api/livraison/pagination")
    public Page<LivraisonResDTO> getAllLivraisonpagination(@RequestBody PageDTO pageDTO) {
        Pageable pageable = pageDTO.getPageable(pageDTO);
        Page<Livraison> livraisons = this.livraisonRepo.findAll(pageable);

        return livraisons.map(livraisonMapper::getLivraisonResDTOFromLivraison);
    }


    @GetMapping("/livreur/{livreurId}")
    public ResponseEntity<List<Livraison>> getLivraisonsByLivreur(@PathVariable Long livreurId) {
        List<Livraison> livraisons = livraisonService.getLivraisonsByLivreurId(livreurId);
        return ResponseEntity.ok(livraisons);
    }
}
