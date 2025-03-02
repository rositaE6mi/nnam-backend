package com.logonedigital.Nnam.services.Produit;

import com.logonedigital.Nnam.entities.Produit;
import jakarta.validation.Valid;

import java.util.List;

public interface ProduitService {
    void addProduit(@Valid Produit produit);
    Produit getProduitById(Integer IdProduit);
    List<Produit> getAllProduits();
    //Optional<Produit> getProduitById(int id);
    void updateProduit(Integer IdProduit, Produit produit);
    void deleteProduit(Integer IdProduit);
}
