package com.logonedigital.Nnam.repository;

import com.logonedigital.Nnam.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepo extends JpaRepository<Stock, Integer> {
    boolean existsByNom(String nom);
    //@Query("SELECT s FROM Stock s WHERE s.nom BEETWEN :minQuantiteStock AND :maxQuantiteStock;")
    List<Stock> findByNomContainingAndQuantiteStockBetween(String nom, Integer minQuantiteStock, Integer maxQuantiteStock);
}