package com.logonedigital.Nnam.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProfilDTO {
    private Integer idProfil;

    @NotEmpty(message = "Please fill this")
    private String nomProfil;

    private Integer idUtilisateur;
    private Integer idRole;
}
