package com.logonedigital.Nnam.services.livraison;

import com.logonedigital.Nnam.entities.Livraison;
import com.logonedigital.Nnam.repositories.LivraisonRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LivraisonServiceImpl implements LivraisonService{

    private final LivraisonRepo livraisonRepo;

    public LivraisonServiceImpl(LivraisonRepo livraisonRepo) {
        this.livraisonRepo = livraisonRepo;
    }

    @Override
    public void addLivraison(Livraison livraison) {
        livraison.setCreatedAt(new Date());
        this.livraisonRepo.save(livraison);
    }

    @Override
    public void deleteLivraison(Integer idLivraison) {
        Livraison livraisonToDelete = this.livraisonRepo.findById(idLivraison).get();
    this.livraisonRepo.delete(livraisonToDelete);
    }

    @Override
    public void updateLivraison(Integer idLivraison, Livraison livraison) {
        Livraison livraisonToUpdate = this.livraisonRepo.findById(idLivraison).get();
        livraisonToUpdate.setAdresseDestination(livraison.getAdresseDestination());
        livraisonToUpdate.setDateDeLivraison(livraison.getDateDeLivraison());
        livraisonToUpdate.setEtatDeLivraison(livraison.getEtatDeLivraison());
        livraisonToUpdate.setUpdatedAt(new Date());
        this.livraisonRepo.saveAndFlush(livraisonToUpdate);

    }

    @Override
    public Livraison getLivriasonById(Integer idLivraison) {
        return this.livraisonRepo.findById(idLivraison).get();
    }

    @Override
    public List<Livraison> getAllLivraison() {
        return this.livraisonRepo.findAll();
    }
}
