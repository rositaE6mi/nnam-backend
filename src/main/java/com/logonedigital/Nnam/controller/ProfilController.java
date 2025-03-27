package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.entities.Profil;
import com.logonedigital.Nnam.entities.Profil;
import com.logonedigital.Nnam.services.profil.ProfilService;
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
    public ResponseEntity<Profil> addProfil(@RequestBody Profil profil){
        Profil saveProfil = profilService.save(profil);
        return ResponseEntity.ok(saveProfil);
    }

    @GetMapping(path = "/get_All")
    public ResponseEntity<List<Profil>> getProfil(){
        return ResponseEntity
                .status(200)
                .body(this.profilService.getProfil());
    }

    @GetMapping(path = "/get/{idProfil}")
    public ResponseEntity <Profil> getProfil (@PathVariable int idProfil){
        return ResponseEntity
                .status(200)
                .body(this.profilService.getProfil(idProfil));
    }

    @PutMapping (path = "/update/{idProfil}")
    public ResponseEntity<String> updateProfil(@PathVariable int idProfil, @RequestBody Profil profil){
        this.profilService.updateProfil(idProfil, profil);
        return ResponseEntity
                .status(200)
                .body("Profil mis a jour avec succes !");
    }

    @DeleteMapping (path = "/delete/{idProfil}")
    public ResponseEntity <String> deleteProfil (@PathVariable int idProfil){
        this.profilService.deleteProfil(idProfil);
        return ResponseEntity
                .status(200)
                .body("Profil suprime avec succes");
    }
}
