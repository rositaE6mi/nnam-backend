package com.logonedigital.Nnam.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public record LigneCommandeReqDTO(
        Integer ligneId,
        @Min(value = 1, message = "La quantité doit être positive et supérieure à 0")
        int quantite,

        @Positive(message = "Le prix unitaire doit être un nombre positif")
        int prixUnitaire,

        @Positive(message = "Le totalLigne doit être un nombre positif")
        double totalLigne,

        Integer commandeId // Clé étrangère pour lier la ligne à une commande
) {
}
