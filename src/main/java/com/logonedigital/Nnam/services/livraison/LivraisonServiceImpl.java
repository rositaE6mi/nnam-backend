package com.logonedigital.Nnam.services.livraison;

import com.logonedigital.Nnam.entities.Livraison;
import com.logonedigital.Nnam.exceptions.ResourceExistException;
import com.logonedigital.Nnam.exceptions.ResourceNotFoundException;
import com.logonedigital.Nnam.repositories.LivraisonRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LivraisonServiceImpl implements LivraisonService{

    private final LivraisonRepo livraisonRepo;

    public LivraisonServiceImpl(LivraisonRepo livraisonRepo) {
        this.livraisonRepo = livraisonRepo;
    }

    @Override
    public void addLivraison(Livraison livraison) {
        Optional<Livraison> livraisonToAdd = this.livraisonRepo
                .findByAdresseDestination(livraison.getAdresseDestination());

        if(livraisonToAdd.isPresent())
            throw new ResourceExistException("La ressource existe deja!");

        livraison.setCreatedAt(new Date());
        this.livraisonRepo.save(livraison);
    }

    @Override
    public void deleteLivraison(Integer idLivraison) {

        Livraison livraisonToDelete = this.livraisonRepo.findById(idLivraison)
                .orElseThrow(()->new ResourceNotFoundException("Ressource non trouvee !"));
    this.livraisonRepo.delete(livraisonToDelete);
    }

    @Override
    public void updateLivraison(Integer idLivraison, Livraison livraison) {
        Livraison livraisonToUpdate = this.livraisonRepo.findById(idLivraison)
                .orElseThrow(()->new ResourceNotFoundException("Ressource non trouvee !"));
        livraisonToUpdate.setAdresseDestination(livraison.getAdresseDestination());
        livraisonToUpdate.setDateDeLivraison(livraison.getDateDeLivraison());
        livraisonToUpdate.setEtatDeLivraison(livraison.getEtatDeLivraison());
        livraisonToUpdate.setUpdatedAt(new Date());
        this.livraisonRepo.saveAndFlush(livraisonToUpdate);

    }

    @Override
    public Livraison getLivriasonById(Integer idLivraison) {

        return this.livraisonRepo.findById(idLivraison)
                .orElseThrow(()->new ResourceNotFoundException("Ressource non trouvee !"));
    }

    @Override
    public List<Livraison> getAllLivraison() {
        return this.livraisonRepo.findAll();
    }
}
