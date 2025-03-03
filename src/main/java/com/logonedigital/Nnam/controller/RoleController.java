package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.entities.Role;
import com.logonedigital.Nnam.entities.Role;
import com.logonedigital.Nnam.repository.RoleRepo;
import com.logonedigital.Nnam.services.role.RoleService;
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
    public ResponseEntity<Role> addRole(@RequestBody Role role){
        Role savedRole = roleRepo.save(role);
        return ResponseEntity.ok(savedRole);
    }

    @GetMapping(path = "/get_All")
    public ResponseEntity<List<Role>> getRole(){
        return ResponseEntity
                .status(200)
                .body(this.roleService.getRoles());
    }

    @GetMapping(path = "/get/{idRole}")
    public ResponseEntity <Role> getRole (@PathVariable int idRole){
        return ResponseEntity
                .status(200)
                .body(this.roleService.getRole(idRole));
    }

    @PutMapping (path = "/update/{idRole}")
    public ResponseEntity<String> updateRole(@PathVariable int idRole, @RequestBody Role role){
        this.roleService.updateRole(idRole, role);
        return ResponseEntity
                .status(200)
                .body("Role mis a jour avec succes !");
    }

    @DeleteMapping (path = "/delete/{idRole}")
    public ResponseEntity <String> deleteRole (@PathVariable int idRole){
        this.roleService.deleteRole(idRole);
        return ResponseEntity
                .status(200)
                .body("Role suprime avec succes");
    }
}
