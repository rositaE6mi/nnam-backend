package com.logonedigital.Nnam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategorieResDTO {
    private int idCat;
    private String nomCat;
    private String description;
}
