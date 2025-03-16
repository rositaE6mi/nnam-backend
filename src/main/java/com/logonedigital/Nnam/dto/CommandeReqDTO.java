package com.logonedigital.Nnam.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record CommandeReqDTO(
        Integer commandeId,

        @Schema(description = "Référence de la commande", example = "CMD12345")
        @NotBlank(message = "La référence est obligatoire")
        @Size(min = 3, max = 50, message = "La référence doit avoir entre 3 et 50 caractères")
        String reference,

        Date dateCommande,

        @Schema(description = "Statut de la commande", example = "EN_COURS")
        @NotBlank(message = "Le statut est obligatoire")
        String status,

        @Schema(description = "Montant total de la commande", example = "1500")
        @Positive(message = "Le total doit être positif")
        int total
) {
}
