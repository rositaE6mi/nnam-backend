package com.logonedigital.Nnam.services.Produit;

import com.logonedigital.Nnam.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ProduitService {
    Produit addProduit(Produit produit);
    Produit updateProduit(int id, Produit produit);
    void deleteProduit(int idProduit);
    Produit getProduit(int idProduit);
    List<Produit> getAllProduits();

    Page<Produit> getAllProduits(Pageable pageable);

    List<Produit> search(String nom, Double minPrice, Double maxPrice);
}