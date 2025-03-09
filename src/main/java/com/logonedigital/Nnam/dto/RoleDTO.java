package com.logonedigital.Nnam.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO {
    private Integer idRole;

    @NotEmpty(message = "Please fill this")
    private String nomRole;
}
