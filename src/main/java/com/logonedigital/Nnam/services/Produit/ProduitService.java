package com.logonedigital.Nnam.services.Produit;

import com.logonedigital.Nnam.dto.produit.ProduitReqDTO;
import com.logonedigital.Nnam.dto.produit.ProduitResDTO;
import com.logonedigital.Nnam.entities.Produit;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ProduitService {
    Produit addProduit(@Valid ProduitReqDTO produitReqDTO);
    //ProduitResDTO getProduit(int idProduit);
    Produit updateProduit(int id, Produit produit);
    void deleteProduit(int idProduit);
    ProduitResDTO getProduit(int idProduit);
    List<Produit> getAllProduits();

    Page<Produit> getAllProduits(Pageable pageable);

    List<Produit> search(String nom, Double minPrice, Double maxPrice);
}