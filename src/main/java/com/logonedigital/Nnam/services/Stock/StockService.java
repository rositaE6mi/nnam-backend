package com.logonedigital.Nnam.services.Stock;

import com.logonedigital.Nnam.entities.Categorie;
import jakarta.validation.Valid;

import java.util.List;

public interface StockService {
    int getStock(Integer idStock);
}
