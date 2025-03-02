package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.entities.Role;
import com.logonedigital.Nnam.entities.Role;
import com.logonedigital.Nnam.services.role.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("api/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> addRole(@RequestBody Role role){
        this.roleService.addRole(role);
        return ResponseEntity
                .status(201)
                .body("Un role ajoute avec succes !");
    }

    @GetMapping(path = "/get_All")
    public ResponseEntity<List<Role>> getRole(){
        return ResponseEntity
                .status(200)
                .body(this.roleService.getRoles());
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity <Role> getRole (@PathVariable int idRole){
        return ResponseEntity
                .status(200)
                .body(this.roleService.getRole(idRole));
    }

    @PutMapping (path = "/update/{id}")
    public ResponseEntity<String> updateRole(@PathVariable int idRole, @RequestBody Role role){
        this.roleService.updateRole(idRole, role);
        return ResponseEntity
                .status(200)
                .body("Role mis a jour avec succes !");
    }

    @DeleteMapping (path = "/delete/{id}")
    public ResponseEntity <String> deleteRole (@PathVariable int idRole){
        this.roleService.deleteRole(idRole);
        return ResponseEntity
                .status(200)
                .body("Role suprime avec succes");
    }
}
