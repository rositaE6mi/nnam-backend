package com.logonedigital.Nnam.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfilDTO {
    private Integer idProfil;

    @NotEmpty(message = "Please fill this")
    private String nomProfil;

    private Integer idUtilisateur;
    private Integer idRole;
}
