package com.logonedigital.Nnam.dto.stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockResDTO {
    private Integer idStock;
    private String nom;
    private Integer quantiteStock;
}
