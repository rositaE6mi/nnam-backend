package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.services.Stock.StockService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class StockControler {
    private final StockService stockService;
    @GetMapping(path ="/stock/{idstock}")
    public ResponseEntity<Integer> getStock( @Parameter(description = "ID du stock")  @PathVariable Integer idStock){
        return ResponseEntity
                .status(200)
                .body(this.stockService.getStock(idStock));

    }
}
