package com.logonedigital.Nnam.services.livreur;

import com.logonedigital.Nnam.entities.Livreur;
import com.logonedigital.Nnam.exceptions.ResourceExistException;
import com.logonedigital.Nnam.exceptions.ResourceNotFoundException;
import com.logonedigital.Nnam.repositories.LivreurRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LivreurServiceImpl implements  LivreurService{

    private final LivreurRepo livreurRepo;

    public LivreurServiceImpl(LivreurRepo livreurRepo) {
        this.livreurRepo = livreurRepo;
    }

    @Override
    public void addLivreur(Livreur livreur) {
        Optional<Livreur> livreurToAdd = this.livreurRepo.findByNom(livreur.getNom());
        if(livreurToAdd.isPresent())
            throw new ResourceExistException("La ressource existe deja !");
        livreur.setCreatedAt(new Date());
        this.livreurRepo.save(livreur);
    }

    @Override
    public void deleteLivreur(Integer idLivreur) {

            Livreur livreurToDelete = this.livreurRepo.findById(idLivreur)
                    .orElseThrow(()-> new ResourceNotFoundException("La ressource n'existe pas !"));
            this.livreurRepo.delete(livreurToDelete);
    }

    @Override
    public void updateLivreur(Integer idLivreur, Livreur livreur) {
        Livreur livreurToUpdate = this.livreurRepo.findById(idLivreur)
                .orElseThrow(()-> new ResourceNotFoundException("La ressource n'existe pas !"));
        livreurToUpdate.setNom(livreur.getNom());
        livreurToUpdate.setPrenom(livreur.getPrenom());
        livreurToUpdate.setTelephone(livreur.getTelephone());
        livreurToUpdate.setUpdatedAt(new Date());
        this.livreurRepo.saveAndFlush(livreurToUpdate);
    }

    @Override
    public Livreur getById(Integer idLivreur) {
        return this.livreurRepo.findById(idLivreur)
                .orElseThrow(()-> new ResourceNotFoundException("La ressource n'existe pas !"));
    }

    @Override
    public List<Livreur> getAllLivreur() {
        return  this.livreurRepo.findAll();
    }
}
