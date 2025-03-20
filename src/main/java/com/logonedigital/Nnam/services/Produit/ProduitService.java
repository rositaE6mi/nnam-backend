package com.logonedigital.Nnam.services.Produit;

import com.logonedigital.Nnam.dto.PdfExportConfigDTO;
import com.logonedigital.Nnam.dto.produit.ProduitReqDTO;
import com.logonedigital.Nnam.dto.produit.ProduitResDTO;
import com.logonedigital.Nnam.entities.Produit;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ProduitService {
    Produit addProduit(@Valid ProduitReqDTO produitReqDTO);

    ProduitResDTO updateProduit(int id, ProduitReqDTO produitReqDTO);

    void deleteProduit(int idProduit);
    ProduitResDTO getProduit(int idProduit);
    //List<Produit> getAllProduits();

    List<ProduitResDTO> getAllProduits();

    Page<Produit> getAllProduit(Pageable pageable);

    List<Produit> search(String nom, Double minPrice, Double maxPrice);

    byte[] generateProduitsPdfReport(PdfExportConfigDTO config) throws Exception;

}