package com.logonedigital.Nnam.dto;

import java.util.Date;

public record FactureDTO(
         Integer factureId,
         Date dateFacture,
         int montantTotal
) {
}
