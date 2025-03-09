package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.dto.ProfilDTO;
import com.logonedigital.Nnam.entities.Profil;
import com.logonedigital.Nnam.entities.Profil;
import com.logonedigital.Nnam.services.profil.ProfilService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("api/profil")

public class ProfilController {


    private final ProfilService profilService;

    public ProfilController(ProfilService profilService) {
        this.profilService = profilService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> addProfil(@Valid @RequestBody ProfilDTO profilDTO){
        this.profilService.addProfil(profilDTO);
        return ResponseEntity
                .status(201)
                .body("Created succesfull");
    }

    @GetMapping(path = "/get_All")
    public ResponseEntity<List<ProfilDTO>> getProfil(){
        return ResponseEntity
                .status(200)
                .body(this.profilService.getAllProfils());
    }

    @GetMapping(path = "/get/{idProfil}")
    public ResponseEntity <ProfilDTO> getProfil (@PathVariable Integer idProfil){
        return ResponseEntity
                .status(200)
                .body(this.profilService.getProfilById(idProfil));
    }

    @PutMapping (path = "/update/{idProfil}")
    public ResponseEntity<String> updateProfil(@PathVariable Integer idProfil,@Valid @RequestBody ProfilDTO profilDTO){
        this.profilService.updateProfil(idProfil, profilDTO);
        return ResponseEntity
                .status(202)
                .body("Profil mis a jour avec succes !");
    }

    @DeleteMapping (path = "/delete/{idProfil}")
    public ResponseEntity <String> deleteProfil (@PathVariable Integer idProfil){
        this.profilService.deleteProfil(idProfil);
        return ResponseEntity
                .status(202)
                .body("Profil suprime avec succes");
    }
}
