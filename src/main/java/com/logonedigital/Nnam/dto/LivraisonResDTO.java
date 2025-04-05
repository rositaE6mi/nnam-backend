package com.logonedigital.Nnam.dto;

import com.logonedigital.Nnam.entities.Livreur;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivraisonResDTO {
    @NotEmpty(message = "Please fill this")
    @NotNull(message = "This field can't be null")

    private Integer idLivraison;
    private String adresseDestination;

    private String dateDeLivraison;

    private String etatDeLivraison;

    private LivreurResDTO livreur;



}