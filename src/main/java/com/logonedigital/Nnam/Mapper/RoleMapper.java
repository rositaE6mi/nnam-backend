package com.logonedigital.Nnam.Mapper;

import com.logonedigital.Nnam.dto.RoleDTO;
import com.logonedigital.Nnam.entities.Role;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Mapper(componentModel = "spring")
@Configuration
public interface RoleMapper {
    Role toRole(RoleDTO roleDTO);

    RoleDTO toDTO(Role role);

    List<RoleDTO> toDTOList (List<Role> roles);

}
