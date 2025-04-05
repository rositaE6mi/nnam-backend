package com.logonedigital.Nnam.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class RoleDTO {
    private Integer idRole;

    @NotEmpty(message = "Please fill this")
    private String nomRole;

    public RoleDTO(Integer idRole, String nomRole) {
        this.idRole = idRole;
        this.nomRole = nomRole;
    }

    public RoleDTO() {

    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }
}
