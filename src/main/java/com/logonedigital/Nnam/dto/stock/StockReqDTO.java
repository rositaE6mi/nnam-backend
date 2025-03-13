package com.logonedigital.Nnam.dto.stock;

import com.logonedigital.Nnam.entities.Stock;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockReqDTO {
    @NotBlank
    private String nom;
    @NotNull
    private Integer quantiteStock;
}
