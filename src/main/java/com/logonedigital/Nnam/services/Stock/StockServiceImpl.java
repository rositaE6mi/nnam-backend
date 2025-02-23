package com.logonedigital.Nnam.services.Stock;

import com.logonedigital.Nnam.entities.Stock;
import com.logonedigital.Nnam.repository.StockRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService{
    private final StockRepo stockRepo;


    @Override
    public int getStock (Integer idStock) {
        Stock stock = stockRepo.findById(idStock)
                .orElseThrow(() -> new RuntimeException("Stock not found! "));
        return stock.getStock();
    }

}
