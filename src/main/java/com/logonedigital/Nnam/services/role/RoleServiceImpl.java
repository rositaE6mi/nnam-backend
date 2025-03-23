package com.logonedigital.Nnam.services.role;

import com.logonedigital.Nnam.Mapper.RoleMapper;
import com.logonedigital.Nnam.dto.RoleDTO;
import com.logonedigital.Nnam.entities.Role;
import com.logonedigital.Nnam.exception.ResourceExistException;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepo roleRepo;
    private RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo,RoleMapper roleMapper) {
        this.roleRepo = roleRepo;
        this.roleMapper = roleMapper;

    }


    @Override
    public RoleDTO addRole(RoleDTO roleDTO) {
        // Vérifier si le rôle existe déjà
        boolean exists = roleRepo.existsByNomRole(roleDTO.getNomRole());
        if (exists) {
            throw new ResourceExistException("Le rôle '" + roleDTO.getNomRole() + "' existe déjà !");
        }

        Role role = roleMapper.toRole(roleDTO);
        Role savedRole = roleRepo.save(role);
        return roleMapper.toRoleDTO(savedRole);
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> role = this.roleRepo.findAll();
        return this.roleMapper.toRoleDTOList(role);
    }
    @Override
    public RoleDTO getRoleById(Integer idRole) {
        Role role = roleRepo.findById(idRole)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with ID: " + idRole));

        return roleMapper.toRoleDTO(role);
    }

    @Override
    public RoleDTO updateRole(Integer idRole, RoleDTO roleDTO) {
        Role roleToUpdate = roleRepo.findById(idRole)
                .orElseThrow(() -> new ResourceNotFoundException("Rôle non trouvé avec l'ID : " + idRole));

        roleToUpdate.setNomRole(roleDTO.getNomRole());
        Role updatedRole = roleRepo.save(roleToUpdate);
        return roleMapper.toRoleDTO(updatedRole);

    }

    @Override
    public void deleteRole(Integer idRole) {
        Role role = roleRepo.findById(idRole)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with ID: " + idRole));

        roleRepo.delete(role);


    }

    @Override
    public Role convertToRole(RoleDTO roleDTO) {
        return null;
    }
}
