package com.logonedigital.Nnam.services.Produit;

import com.logonedigital.Nnam.entities.Produit;
import java.util.List;

public interface ProduitService {
    Produit addProduit(Produit produit);
    Produit updateProduit(int id, Produit produit);
    void deleteProduit(int idProduit);
    Produit getProduit(int idProduit);
    List<Produit> getAllProduits();
}