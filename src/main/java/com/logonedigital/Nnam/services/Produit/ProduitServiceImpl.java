package com.logonedigital.Nnam.services.Produit;

import com.logonedigital.Nnam.dto.produit.ProduitReqDTO;
import com.logonedigital.Nnam.dto.produit.ProduitResDTO;
import com.logonedigital.Nnam.entities.Categorie;
import com.logonedigital.Nnam.entities.Produit;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.mapper.ProduitMapper;
import com.logonedigital.Nnam.repository.CategorieRepo;
import com.logonedigital.Nnam.repository.ProduitRepo;
import com.logonedigital.Nnam.repository.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitServiceImpl implements ProduitService {

    @Autowired
    private ProduitRepo produitRepository;

    @Autowired
    private CategorieRepo categorieRepository;

    @Autowired
    private StockRepo stockRepository;
    @Autowired
    private ProduitMapper produitMapper;

    @Override
    public Produit addProduit(ProduitReqDTO produitReqDTO) {
        // Vérifiez que la catégorie existe
        Categorie categorie = categorieRepository.findById(produitReqDTO.getCategorieId())
                .orElseThrow(() -> new ResourceNotFoundException("Catégorie non trouvée avec l'ID : " + produitReqDTO.getCategorieId() ));

        Produit produit = produitMapper.getProduitFromProduitReqDTO(produitReqDTO);
        produit.setCategorie(categorie);
        produit.setStock(stockRepository.save(produit.getStock()));//composition stricte

/*
        // Vérifiez que le stock est fourni
        if (produit.getStock() == null) {
            throw new ResourceNotFoundException("Le stock est obligatoire pour créer un produit.");
        }

        // Associez la catégorie et le stock au produit
        produit.setCategorie(categorie);
        produit.setStock(stockRepository.save(produit.getStock())); // Sauvegardez le stock
*/
        // Sauvegardez le produit
        return produitRepository.save(produit);
    }

    @Override
    public Produit updateProduit(int id, Produit produit) {
        Produit existingProduit = produitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'ID : " + id));

        // Mettez à jour les champs du produit
        existingProduit.setNomProduit(produit.getNomProduit());
        existingProduit.setDescription(produit.getDescription());
        existingProduit.setPrixU(produit.getPrixU());
        existingProduit.setDateExpiration(produit.getDateExpiration());

        // Mettez à jour la catégorie si elle est fournie
        if (produit.getCategorie() != null) {
            Categorie categorie = categorieRepository.findById(produit.getCategorie().getIdCat())
                    .orElseThrow(() -> new ResourceNotFoundException("Catégorie non trouvée avec l'ID : " + produit.getCategorie().getIdCat()));
            existingProduit.setCategorie(categorie);
        }

        // Mettez à jour le stock si il est fourni
        if (produit.getStock() != null) {
            existingProduit.setStock(stockRepository.save(produit.getStock()));
        }

        // Sauvegardez le produit mis à jour
        return produitRepository.save(existingProduit);
    }

    @Override
    public void deleteProduit(int idProduit) {
        if (!produitRepository.existsById(idProduit)) {
            throw new ResourceNotFoundException("Produit non trouvé avec l'ID : " + idProduit);
        }
        produitRepository.deleteById(idProduit);
    }

    @Override
    public Produit getProduit(int idProduit) {
        return produitRepository.findById(idProduit)
                .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'ID : " + idProduit));
    }

    @Override
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    @Override
    public Page<Produit> getAllProduits(Pageable pageable){
        return produitRepository.findAll(pageable);
    }

    @Override
    public List<Produit> search(String nom, Double minPrice, Double maxPrice) {
        if (nom != null && minPrice != null){
            return produitRepository.findByNomProduitContainingAndPrixUBetween(nom, minPrice, maxPrice);
        }
        return produitRepository.findAll();
    }


}