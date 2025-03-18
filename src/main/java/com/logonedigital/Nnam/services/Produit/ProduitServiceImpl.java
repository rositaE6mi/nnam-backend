package com.logonedigital.Nnam.services.Produit;

import com.logonedigital.Nnam.dto.produit.ProduitReqDTO;
import com.logonedigital.Nnam.dto.produit.ProduitResDTO;
import com.logonedigital.Nnam.entities.Categorie;
import com.logonedigital.Nnam.entities.Produit;
import com.logonedigital.Nnam.entities.Stock;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.mapper.ProduitMapper;
import com.logonedigital.Nnam.mapper.StockMapper;
import com.logonedigital.Nnam.repository.CategorieRepo;
import com.logonedigital.Nnam.repository.ProduitRepo;
import com.logonedigital.Nnam.repository.StockRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepo produitRepository;
    private final CategorieRepo categorieRepository;
    private final StockRepo stockRepository;
    private final ProduitMapper produitMapper;
    private final StockMapper stockMapper;

    public ProduitServiceImpl(ProduitRepo produitRepository, CategorieRepo categorieRepository, StockRepo stockRepository, ProduitMapper produitMapper, StockMapper stockMapper) {
        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
        this.stockRepository = stockRepository;
        this.produitMapper = produitMapper;
        this.stockMapper = stockMapper;
    }

    @Override
    public Produit addProduit(ProduitReqDTO produitReqDTO) {
        // Vérifiez que la catégorie existe
        Categorie categorie = this.categorieRepository
                .findById(produitReqDTO.getCategorieId())
                .orElseThrow(() -> new ResourceNotFoundException("Catégorie non trouvée avec l'ID : " + produitReqDTO.getCategorieId() ));

        //creons le stock
        Stock stock = new Stock();
        stock.setNom(produitReqDTO.getStock().getNom());
        stock.setQuantiteStock(produitReqDTO.getStock().getQuantiteStock());
        Stock savedStock = stockRepository.save(stock);
        //creons le produit
        Produit produit = produitMapper.getProduitFromProduitReqDTO(produitReqDTO);
        produit.setCategorie(categorie);
        produit.setStock(savedStock);

        return produitRepository.save(produit);

    }

    @Override
    public ProduitResDTO updateProduit(int idProduit, ProduitReqDTO produitReqDTO) {
        Produit existingProduit = produitRepository.findById(idProduit)
                .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'ID : " + idProduit));

        // Mise à jour les champs du produit
        existingProduit.setNomProduit(produitReqDTO.getNomProduit());
        existingProduit.setDescription(produitReqDTO.getDescription());
        existingProduit.setPrixU(produitReqDTO.getPrixU());
        existingProduit.setDateExpiration(produitReqDTO.getDateExpiration());

        // Mise à jour la catégorie si elle est fournie sans ecraser ce qui etait deja la
        if (produitReqDTO.getCategorieId() != null) {
            Categorie categorie = categorieRepository.findById(produitReqDTO.getCategorieId())
                    .orElseThrow(() -> new ResourceNotFoundException("Catégorie non trouvée avec l'ID : " + produitReqDTO.getCategorieId()));
            existingProduit.setCategorie(categorie);
        }

        // Mise à jour le stock si il est fourni
        if (produitReqDTO.getStock() != null) {
            Stock existingStock = existingProduit.getStock();
            existingStock.setNom(produitReqDTO.getStock().getNom());
            existingStock.setQuantiteStock(produitReqDTO.getStock().getQuantiteStock());
            stockRepository.save(existingStock); // MàJ sans créer de nouvelle entité
        }

        // Sauvegarde de produit mis à jour
        produitRepository.save(existingProduit);
        return produitMapper.getProduitResDTOFromProduit(existingProduit);
    }

    @Override
     public void deleteProduit(int idProduit) {
        Produit produit = produitRepository.findById(idProduit)
                .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'ID : " + idProduit));
        produitRepository.delete(produit);
        this.produitMapper.getProduitResDTOFromProduit(produit);
    }


@Override
public ProduitResDTO getProduit(int idProduit) {
    Produit produit = produitRepository.findProduitWithStock(idProduit);

    if (produit == null) {
        throw new ResourceNotFoundException("Produit non trouvé avec l'ID : " + idProduit);
    }

    ProduitResDTO dto = produitMapper.getProduitResDTOFromProduit(produit);

    if (produit.getStock() != null) {
        dto.setStock(stockMapper.toDTO(produit.getStock())); // Plus jamais NULL !
    }

    return dto;
}

    @Override
    public List<ProduitResDTO> getAllProduits(List<Produit> produits) {
        produits = this.produitRepository.findAll();
        return this.produitMapper.toDtoProduitList(produits);
    }

    @Override
    public Page<Produit> getAllProduit(Pageable pageable){
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