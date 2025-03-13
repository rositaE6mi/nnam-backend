package com.logonedigital.Nnam.repository;

import com.logonedigital.Nnam.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepo extends JpaRepository<Stock, Integer> {
    boolean existsByNom(String nom);

    List<Stock> findByNomContainingAndQuantiteStockBetween(String nom, Integer minQuantiteStock, Integer maxQuantiteStock);
}