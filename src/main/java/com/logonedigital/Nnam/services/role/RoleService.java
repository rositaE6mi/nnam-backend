package com.logonedigital.Nnam.services.role;

import com.logonedigital.Nnam.entities.Role;
import com.logonedigital.Nnam.entities.Role;

import java.util.List;

public interface RoleService {
        void addRole (Role role);
        Role getRole (int idRole);
        List<Role> getRoles();
        void updateRole (Integer idRole, Role role);
        void deleteRole (Integer idRole);
}
