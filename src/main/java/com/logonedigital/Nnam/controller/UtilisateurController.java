package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.dto.UtilisateurDTO;
import com.logonedigital.Nnam.entities.Utilisateur;
import com.logonedigital.Nnam.services.utilisateur.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ( "api/utilisateur")

public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController (UtilisateurService utilisateurService){
        this.utilisateurService = utilisateurService;
    }

    @PostMapping (path = "/add")
    public ResponseEntity<String> addUtilisateur(@RequestBody @Valid UtilisateurDTO utilisateurDTO){
        this.utilisateurService.addUtilisateur(utilisateurDTO);
        return ResponseEntity
                .status(201)
                .body("Utilisateur cree avec succes !");
    }

    @GetMapping(path = "/get_All")
    public ResponseEntity<List<UtilisateurDTO>> getUtilisateurs(){
        return ResponseEntity
                .status(200)
                .body(this.utilisateurService.getAllUtilisateurs());
    }

    @GetMapping(path = "/get/{idUtilisateur}")
    public ResponseEntity <UtilisateurDTO> getUtilisateurById(@PathVariable Integer idUtilisateur){
        return ResponseEntity
                .status(200)
                .body(this.utilisateurService.getUtilisateurById(idUtilisateur));
    }

    @PutMapping (path = "/update/{idUtilisateur}")
    public ResponseEntity<String> updateUtilisateur(@PathVariable Integer idUtilisateur,@Valid @RequestBody UtilisateurDTO utilisateurDTO){
        this.utilisateurService.updateUtilisateur(idUtilisateur, utilisateurDTO);
        return ResponseEntity
                .status(200)
                .body("Utilisateur mis a jour avec succes !");
    }

    @DeleteMapping (path = "/delete/{idUtilisateur}")
    public ResponseEntity <String> deleteUtilisateur (@PathVariable Integer idUtilisateur){
        this.utilisateurService.deleteUtilisateur(idUtilisateur);
        return ResponseEntity
                .status(200)
                .body("Utilisateur suprime avec succes");
    }
}
