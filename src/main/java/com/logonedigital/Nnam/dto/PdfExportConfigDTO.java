package com.logonedigital.Nnam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PdfExportConfigDTO {
    private boolean includeStockDetails;
    private boolean includeCategoryDetails;
    private String headerText="Rapport des produits";
    private String footerText="Généré le" + LocalDate.now().format(DateTimeFormatter.ISO_DATE);
}
