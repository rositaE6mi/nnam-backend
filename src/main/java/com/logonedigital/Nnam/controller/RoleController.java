package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.dto.RoleDTO;
import com.logonedigital.Nnam.entities.Role;
import com.logonedigital.Nnam.entities.Role;
import com.logonedigital.Nnam.repository.RoleRepo;
import com.logonedigital.Nnam.services.role.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("api/roles")
public class RoleController {

    private final RoleService roleService;
    @Autowired

    private RoleRepo roleRepo;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> addRole(@Valid @RequestBody RoleDTO roleDTO){
        this.roleService.addRole(roleDTO);
        return ResponseEntity
                .status(201)
                .body("Role cree avec succes");
    }

    @GetMapping(path = "/get_All")
    public ResponseEntity<List<RoleDTO>> getRole(){
        return ResponseEntity
                .status(200)
                .body(this.roleService.getAllRoles());
    }

    @GetMapping(path = "/get/{idRole}")
    public ResponseEntity <RoleDTO> getRoleById (@PathVariable Integer idRole){
        return ResponseEntity
                .status(200)
                .body(this.roleService.getRoleById(idRole));
    }

    @PutMapping (path = "/update/{idRole}")
    public ResponseEntity<String> updateRole(@PathVariable Integer idRole,@Valid @RequestBody RoleDTO roleDTO){
        this.roleService.updateRole(idRole, roleDTO);
        return ResponseEntity
                .status(200)
                .body("Role mis a jour avec succes !");
    }

    @DeleteMapping (path = "/delete/{idRole}")
    public ResponseEntity <String> deleteRole (@PathVariable Integer idRole){
        this.roleService.deleteRole(idRole);
        return ResponseEntity
                .status(200)
                .body("Role suprime avec succes");
    }
}
