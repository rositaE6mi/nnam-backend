package com.logonedigital.Nnam.dto.categorie;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategorieResDTO {
    private int idCat;
    private String nomCat;
    private String description;
}
