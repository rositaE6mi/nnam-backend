package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.dto.UtilisateurDTO;
import com.logonedigital.Nnam.entities.Utilisateur;
import com.logonedigital.Nnam.services.utilisateur.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping ( "api/utilisateur")

public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController (UtilisateurService utilisateurService){
        this.utilisateurService = utilisateurService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Map<String, String>> addUtilisateur(@RequestBody @Valid UtilisateurDTO utilisateurDTO) {
        this.utilisateurService.addUtilisateur(utilisateurDTO);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Utilisateur cree avec succes !");

        return ResponseEntity
                .status(201)
                .body(response);
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

    @GetMapping
    public ResponseEntity<Page<UtilisateurDTO>> getUtilisateurs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nomUtilisateur") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        // Appel du service pour récupérer les utilisateurs paginés et triés
        Page<UtilisateurDTO> utilisateurs = utilisateurService.getUtilisateurs(page, size, sortBy, sortDirection);
        return ResponseEntity.ok(utilisateurs);
    }

    // Ajoute un endpoint pour la connexion de l'administrateur
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String motDePasse) {
        Optional<String> roleOptional = utilisateurService.login(email, motDePasse);
        if (roleOptional.isPresent()) {
            String role = roleOptional.get().trim().toUpperCase();
            System.out.println("Role final : " + role); // ✅ pour debug
            if (role.contains("ADMIN")) {
                return ResponseEntity.ok("ADMIN");
            } else if (role.contains("AGRICULTEUR")) {
                return ResponseEntity.ok("AGRICULTEUR");
            } else if (role.contains("CLIENT")) {
                return ResponseEntity.ok("CLIENT");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Rôle inconnu : " + role);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants invalides");
        }
    }

    @GetMapping("/getByEmail")
    public ResponseEntity<UtilisateurDTO> getByEmail(@RequestParam String email) {
        UtilisateurDTO utilisateurDTO = utilisateurService.getByEmail(email);
        return ResponseEntity.ok(utilisateurDTO);
    }









}
