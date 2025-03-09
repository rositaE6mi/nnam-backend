package com.logonedigital.Nnam.services.role;

import com.logonedigital.Nnam.dto.RoleDTO;
import com.logonedigital.Nnam.entities.Role;
import com.logonedigital.Nnam.exception.ResourceExistException;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.repository.RoleRepo;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepo roleRepo;

    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;

    }


    @Override
    public RoleDTO addRole(RoleDTO roleDTO) {
        // Vérifier si le rôle existe déjà
        boolean exists = roleRepo.existsByNomRole(roleDTO.getNomRole());
        if (exists) {
            throw new ResourceExistException("Le rôle '" + roleDTO.getNomRole() + "' existe déjà !");
        }

        Role role = new Role();
        role.setNomRole(roleDTO.getNomRole());

        Role savedRole = roleRepo.save(role);

        roleDTO.setIdRole(savedRole.getIdRole());

        return roleDTO;
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        return roleRepo.findAll().stream().map(role ->{
            RoleDTO dto = new RoleDTO();
            dto.setIdRole(role.getIdRole());
            dto.setNomRole(role.getNomRole());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public RoleDTO getRoleById(Integer idRole) {
        Role role = roleRepo.findById(idRole)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with ID: " + idRole));

        RoleDTO dto = new RoleDTO();
        dto.setIdRole(role.getIdRole());
        dto.setNomRole(role.getNomRole());

        return dto;
    }

    @Override
    public RoleDTO updateRole(Integer idRole, RoleDTO roleDTO) {
        Role roleToUpdate = roleRepo.findById(idRole)
                .orElseThrow(() -> new ResourceNotFoundException("Rôle non trouvé avec l'ID : " + idRole));

        // Vérifier si un autre rôle avec le même nom existe déjà
        boolean exists = roleRepo.existsByNomRole(roleDTO.getNomRole());
        if (exists && !roleToUpdate.getNomRole().equals(roleDTO.getNomRole())) {
            throw new ResourceExistException("Le rôle '" + roleDTO.getNomRole() + "' existe déjà !");
        }

        roleToUpdate.setNomRole(roleDTO.getNomRole());

        Role updatedRole = roleRepo.save(roleToUpdate);

        RoleDTO updatedRoleDTO = new RoleDTO();
        updatedRoleDTO.setIdRole(updatedRole.getIdRole());
        updatedRoleDTO.setNomRole(updatedRole.getNomRole());

        return updatedRoleDTO;

    }

    @Override
    public void deleteRole(Integer idRole) {
        Role role = roleRepo.findById(idRole)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with ID: " + idRole));

        roleRepo.delete(role);


    }
}
