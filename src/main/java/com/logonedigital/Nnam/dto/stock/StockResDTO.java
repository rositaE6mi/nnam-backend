package com.logonedigital.Nnam.dto.stock;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockResDTO {
    private Integer idStock;
    private String nom;
    private Integer quantiteStock;
}
