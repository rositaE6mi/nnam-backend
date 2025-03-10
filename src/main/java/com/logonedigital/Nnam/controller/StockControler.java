package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.entities.Categorie;
import com.logonedigital.Nnam.entities.Stock;
import com.logonedigital.Nnam.services.Stock.StockService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StockControler {
    @Autowired
    private final StockService stockService;
    public StockControler(StockService stockService){
        this.stockService = stockService;
    }
/*
    @PostMapping("api/stock/add")
    public ResponseEntity<Stock> addStock(@Valid @RequestBody Stock stock){
        Stock result = stockService.addStock(stock);
        return ResponseEntity
                .status(201)
                .body(result);
    }

    @PutMapping("/update")
    public ResponseEntity<Stock> updateStock(@RequestBody int id, Stock stock) {
        Stock updatedStock = this.stockService.updateStock(id, stock);
        return ResponseEntity.status(200).body(updatedStock);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable int id) {
        stockService.deleteStock(id);
        return ResponseEntity.status(204).build();
    }*/

    @GetMapping(path ="/stock/get_all")
    public ResponseEntity<List<Stock>> getAllStock(){
        List<Stock> stock = stockService.getAllStock();
        return ResponseEntity
                .status(200)
                .body(stockService.getAllStock());
    }

    @GetMapping("api/stock/get_by_id/{id}")
    public ResponseEntity<Stock> getStock( @PathVariable int id) {
        Stock stock = stockService.getStock(id);
        return ResponseEntity
                .status(200)
                .body(stock);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Stock>> searchStocks(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) int minQuantite,
            @RequestParam(required = false) int maxQuantite){
        return ResponseEntity.ok(stockService.searchStocks(nom, minQuantite, maxQuantite));
    }

}
