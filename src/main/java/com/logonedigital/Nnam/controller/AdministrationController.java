package com.logonedigital.Nnam.controller;


import com.logonedigital.Nnam.entities.Administration;
import com.logonedigital.Nnam.services.administration.AdministrationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdministrationController {
    private final AdministrationService administrationService;

    public AdministrationController(AdministrationService administrationService) {
        this.administrationService = administrationService;
    }

    @PostMapping(path = "api/administration/add")
    public ResponseEntity<String> add(@Valid @RequestBody Administration administration){
        this.administrationService.addAdministration(administration);

        return ResponseEntity
                .status(200)
                .body("L'administration a ete ajoutee!");
    }

    @PutMapping(path = "api/administration/delete_by_id/{idAdministration}")
    public ResponseEntity<String> deleteAdministration(Integer idAdministration){
        this.administrationService.deleteAdministration(idAdministration);

        return ResponseEntity
                .status(202)
                .body("L'administration a ete supprimee!");

    }

    @PutMapping(path = "api/administration/update/{idAdministration}")
    public  ResponseEntity<String> update(Integer idAdministration, Administration administration){
        this.administrationService.updateAdministration(idAdministration, administration);
        return ResponseEntity
                .status(202)
                .body("L'administration a ete modifiee!");

    }

    @GetMapping(path = "api/admininstration/get_by_id/{idAdministration}")
    public  ResponseEntity<Administration> getbyid(Integer idAdministration){
        return ResponseEntity
                .status(202)
                .body(this.administrationService.getAdministrationById(idAdministration));
    }

    @GetMapping(path = "api/administration/get_all")
    public  ResponseEntity<List<Administration>> getAllAdministration(){
        return ResponseEntity
                .status(202)
                .body(this.administrationService.getAllAdministration());
    }
}
