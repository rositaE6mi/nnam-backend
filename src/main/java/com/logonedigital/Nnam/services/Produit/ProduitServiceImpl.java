package com.logonedigital.Nnam.services.Produit;

import com.logonedigital.Nnam.entities.Produit;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.repository.ProduitRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProduitServiceImpl implements ProduitService{
    private final ProduitRepo produitRepo;

    public ProduitServiceImpl(ProduitRepo produitRepo) {
        this.produitRepo = produitRepo;
    }

    @Override
    public void addProduit(Produit produit) {
        log.info("{}",produit);
        produitRepo.save(produit);
    }

    @Override
    public List<Produit> getAllProduits() {

        return produitRepo.findAll();
    }
    @Override
    public Produit getProduitById(Integer id) {
        Optional<Produit> produit = this.produitRepo.findById(id);
        if (produit.isEmpty())
            throw new ResourceNotFoundException("Resource not found ");
        return produit.get();
    }

    public void updateProduit(Integer id, Produit produit) {
       Produit produitToUpdate = this.produitRepo
               .findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Product not found by ID : " + id));
        produitToUpdate.setNomProduit(produit.getNomProduit());
        produitToUpdate.setDescription(produit.getDescription());
        produitToUpdate.setPrixU(produit.getPrixU());
        produitToUpdate.setDateExpiration(produit.getDateExpiration());
        produitRepo.save(produitToUpdate);
    }
@Override
    public void deleteProduit(Integer id) {
        Produit produit = this.produitRepo.findById(id)
                        .orElseThrow(()-> new ResourceNotFoundException("Not found exception by ID : " + id));
    this.produitRepo.delete(produit);
}

}
