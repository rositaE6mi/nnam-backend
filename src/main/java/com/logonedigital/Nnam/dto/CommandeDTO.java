package com.logonedigital.Nnam.dto;

import java.util.Date;

public record CommandeDTO(
        Integer commandeId,
        String reference,
        Date dateCommande,
        String status,
        int total
) {
}
