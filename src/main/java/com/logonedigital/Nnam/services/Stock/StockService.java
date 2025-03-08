package com.logonedigital.Nnam.services.Stock;

import com.logonedigital.Nnam.entities.Stock;

import java.util.List;

public interface StockService {
    Stock addStock(Stock stock);
    Stock updateStock(int id, Stock stock);
    void deleteStock(int id);
    Stock getStock(int id);

    List<Stock> getAllStock();

    boolean existsById(int id);
}
