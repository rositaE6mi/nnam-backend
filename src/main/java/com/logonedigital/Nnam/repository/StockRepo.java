package com.logonedigital.Nnam.repository;

import com.logonedigital.Nnam.entities.Produit;
import com.logonedigital.Nnam.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepo extends JpaRepository<Stock, Integer> {
}
