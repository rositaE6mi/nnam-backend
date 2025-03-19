package com.logonedigital.Nnam.services.Stock;

import com.logonedigital.Nnam.dto.stock.StockReqDTO;
import com.logonedigital.Nnam.entities.Stock;
import com.logonedigital.Nnam.exception.ResourceExistException;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.repository.StockRepo;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StockServiceImpl implements StockService{

   private final StockRepo stockRepository;

    public StockServiceImpl(StockRepo stockRepository) {
        this.stockRepository = stockRepository;
    }


    @Override
    public Stock addStock(@Valid Stock stock) {
        if (stockRepository.existsByNom(stock.getNom())) {
            throw new ResourceExistException("Un stock avec le nom '" + stock.getNom() + "' existe déjà.");
        }
        return stockRepository.save(stock);
    }

    @Override
    public Stock updateStock(int id, Stock stock) {
        Stock existingStock = stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock non trouvé avec l'ID : " + id));
        existingStock.setNom(stock.getNom());
        existingStock.setQuantiteStock(stock.getQuantiteStock());
        return stockRepository.save(existingStock);
    }

    @Override
    public void deleteStock(int id) {
        if (!stockRepository.existsById(id)) {
            throw new ResourceNotFoundException("Stock non trouvé avec l'ID : " + id);
        }
        stockRepository.deleteById(id);
    }

    @Override
    public Stock getStock(int id) {
        return stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock non trouvé avec l'ID : " + id));
    }

    @Override
    public List<Stock> getAllStock () {
        return  stockRepository.findAll();
    }

    @Override
    public boolean existsById(int id) {
        return stockRepository.existsById(id);
    }

    @Override
    public List<Stock> searchStocks(String nom, Integer minQuantiteStock, Integer maxQuantiteStock) {
      /*  if (nom != null && minQuantiteStock != null && maxQuantiteStock != null){
            return stockRepository.findByNomContainingAndMinQuantiteStockBetween(nom, minQuantiteStock,maxQuantiteStock);
        }*/
        return stockRepository.findByNomContainingAndQuantiteStockBetween(nom, minQuantiteStock, maxQuantiteStock);
    }
   /* private final StockRepo stockRepo;

    public StockServiceImpl(StockRepo stockRepo){
        this.stockRepo = stockRepo;
    }

    @Override
    public String addStock(Stock stock){
        stockRepo.save(stock);
        return "Stock ajouté avec succès !";
    }

    @Override
    public Stock getStockById(Integer id) {
        return stockRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock non trouvé avec l'ID : " + id));
    }
    @Override
    public List<Stock> getAllStock () {
        return  stockRepo.findAll();
    } */


}
