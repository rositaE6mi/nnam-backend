package com.logonedigital.Nnam.services.role;

import com.logonedigital.Nnam.dto.RoleDTO;

import java.util.List;

public interface RoleService {
        RoleDTO addRole(RoleDTO roleDTO);
        List <RoleDTO>getAllRoles();
        RoleDTO getRoleById(Integer idRole);
        RoleDTO updateRole(Integer idRole, RoleDTO roleDTO);
        void deleteRole(Integer idRole);

}
