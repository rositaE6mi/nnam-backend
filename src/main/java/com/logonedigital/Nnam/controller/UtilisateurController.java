package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.entities.Utilisateur;
import com.logonedigital.Nnam.services.utilisateur.UtilisateurService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
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
    public ResponseEntity<String> addUtilisateur(@Valid @RequestBody Utilisateur utilisateur){
        this.utilisateurService.addUtilisateur(utilisateur);
        return ResponseEntity
                .status(201)
                .body("Utilisateur cree avec succes !");
    }

    @GetMapping(path = "/get_All")
    public ResponseEntity<List<Utilisateur>> getUtilisateur(){
        return ResponseEntity
                .status(200)
                .body(this.utilisateurService.getUtilisateurs());
    }

    @GetMapping(path = "/get/{idUtilisateur}")
    public ResponseEntity <Utilisateur> getUtilisateur (@PathVariable int idUtilisateur){
        return ResponseEntity
                .status(200)
                .body(this.utilisateurService.getUtilisateur(idUtilisateur));
    }

    @PutMapping (path = "/update/{idUtilisateur}")
    public ResponseEntity<String> updateUtilisateur(@PathVariable int idUtilisateur, @RequestBody Utilisateur utilisateur){
        this.utilisateurService.updateUtilisateur(idUtilisateur, utilisateur);
        return ResponseEntity
                .status(200)
                .body("Utilisateur mis a jour avec succes !");
    }

    @DeleteMapping (path = "/delete/{idUtilisateur}")
    public ResponseEntity <String> deleteUtilisateur (@PathVariable int idUtilisateur){
        this.utilisateurService.deleteUtilisateur(idUtilisateur);
        return ResponseEntity
                .status(200)
                .body("Utilisateur suprime avec succes");
    }
}
