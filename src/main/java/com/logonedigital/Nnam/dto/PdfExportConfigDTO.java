package com.logonedigital.Nnam.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PdfExportConfigDTO {
    @Schema(
            description = "Inclure les détails du stock",
            example = "true",
            defaultValue = "true"
    )
    private boolean includeStockDetails = true;

    @Schema(
            description = "Texte d'en-tête du rapport",
            example = "Rapport des produits - NNAM",
            defaultValue = "Rapport des produits"
    )
    private String headerText = "Rapport des produits";

    @Schema(
            description = "Texte de pied de page",
            example = "Généré le 2024-03-20 | Service Production",
            defaultValue = "Document confidentiel"
    )
    private String footerText = "Document confidentiel";}
