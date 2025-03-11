package com.logonedigital.Nnam.services.role;

import com.logonedigital.Nnam.entities.Role;
import com.logonedigital.Nnam.repository.RoleRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepo roleRepo;

    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;

    }

    @Override
    public void addRole(Role role) {
        roleRepo.save(role);

    }

    @Override
    public Role getRole(int idRole) {
        return roleRepo.findById(idRole).orElse(null);
    }

    @Override
    public List<Role> getRoles() {
        return roleRepo.findAll();
    }

    @Override
    public void updateRole(Integer idRole, Role role) {
        Role existingRole = roleRepo.findById(idRole).orElse(null);

    }

    @Override
    public void deleteRole(Integer idRole) {
        roleRepo.deleteById(idRole);

    }
}
