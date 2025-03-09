package com.logonedigital.Nnam.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategorieReqDTO {
    @NotBlank(message = "Le nom de la categorie est obligatoire")
    private String nomCat;

    @NotBlank(message = "La description est obligatoire")
    private String description;
}
