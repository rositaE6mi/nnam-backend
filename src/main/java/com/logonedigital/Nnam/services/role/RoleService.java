package com.logonedigital.Nnam.services.role;

import com.logonedigital.Nnam.dto.RoleDTO;
import com.logonedigital.Nnam.entities.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
        RoleDTO addRole(RoleDTO roleDTO);
        List <RoleDTO>getAllRoles();
        RoleDTO getRoleById(Integer idRole);
        RoleDTO updateRole(Integer idRole, RoleDTO roleDTO);
        void deleteRole(Integer idRole);

        Role convertToRole(RoleDTO roleDTO);
}
