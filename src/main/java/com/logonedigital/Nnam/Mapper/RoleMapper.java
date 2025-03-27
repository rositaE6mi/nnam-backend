package com.logonedigital.Nnam.Mapper;

import com.logonedigital.Nnam.dto.RoleDTO;
import com.logonedigital.Nnam.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    // Conversion de RoleDTO en Role
    @Mapping(target = "idRole", source = "idRole")
    Role toRole(RoleDTO roleDTO);

    // Conversion de Role en RoleDTO
    @Mapping(target = "idRole", source = "idRole")
    @Named("defaultToRoleDTO") // On utilise un nom spécifique pour cette méthode
    RoleDTO toRoleDTO(Role role);

    // Méthode personnalisée pour mapper Role en RoleDTO
    @Named("customToRoleDTO")
    default RoleDTO customToRoleDTO(Role role) {
        if (role == null) {
            return null;
        }
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setIdRole(role.getIdRole());
        roleDTO.setNomRole(role.getNomRole());
        // N'inclut pas `dateDeCreation` et `dateDeModification`
        return roleDTO;
    }

    // Conversion de liste de Role en liste de RoleDTO en utilisant la méthode personnalisée
    @Mapping(target = "utilisateurs", ignore = true)  // Si tu ne veux pas inclure ces propriétés
    List<RoleDTO> toRoleDTOList(List<Role> roles);
}


