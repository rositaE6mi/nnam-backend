package com.logonedigital.Nnam.services.livreur;

import com.logonedigital.Nnam.entities.Livreur;
import com.logonedigital.Nnam.repositories.LivreurRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LivreurServiceImpl implements  LivreurService{

    private final LivreurRepo livreurRepo;

    public LivreurServiceImpl(LivreurRepo livreurRepo) {
        this.livreurRepo = livreurRepo;
    }

    @Override
    public void addLivreur(Livreur livreur) {
        livreur.setCreatedAt(new Date());
        this.livreurRepo.save(livreur);
    }

    @Override
    public void deleteLivreur(Integer idLivreur) {
            Livreur livreurToDelete = this.livreurRepo.findById(idLivreur).get();
            this.livreurRepo.delete(livreurToDelete);
    }

    @Override
    public void updateLivreur(Integer idLivreur, Livreur livreur) {
        Livreur livreurToUpdate = this.livreurRepo.findById(idLivreur).get();
        livreurToUpdate.setNom(livreur.getNom());
        livreurToUpdate.setPrenom(livreur.getPrenom());
        livreurToUpdate.setTelephone(livreur.getTelephone());
        livreurToUpdate.setUpdatedAt(new Date());
        this.livreurRepo.saveAndFlush(livreurToUpdate);
    }

    @Override
    public Livreur getById(Integer idLivreur) {
        return this.livreurRepo.findById(idLivreur).get();
    }

    @Override
    public List<Livreur> getAllLivreur() {
        return  this.livreurRepo.findAll();
    }
}
