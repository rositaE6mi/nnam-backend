package com.logonedigital.Nnam.dto.categorie;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
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
