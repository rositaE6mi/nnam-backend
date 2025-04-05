package com.logonedigital.Nnam.dto;

public record LigneCommandeDTO(
        Integer ligneId,
        int quantite,
        int prixUnitaire,
        double totalLigne
) {
}
