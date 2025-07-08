package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.dto.produit.ProduitResDTO;
import com.logonedigital.Nnam.dto.stock.StockResDTO;
import com.logonedigital.Nnam.entities.Categorie;
import com.logonedigital.Nnam.entities.Produit;
import com.logonedigital.Nnam.entities.Stock;
import com.logonedigital.Nnam.services.Stock.StockService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<List<StockResDTO>> getAllStock(){
        List<Stock> stock = new ArrayList<>();
        return ResponseEntity
                .status(200)
                .body(this.stockService.getAllStock());
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
            @RequestParam(required = false) Integer minQuantiteStock,
            @RequestParam(required = false) Integer maxQuantiteStock){
        return ResponseEntity.ok(stockService.searchStocks(nom, minQuantiteStock, maxQuantiteStock));
    }

}
