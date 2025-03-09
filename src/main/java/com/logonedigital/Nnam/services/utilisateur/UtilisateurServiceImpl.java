package com.logonedigital.Nnam.services.utilisateur;

import com.logonedigital.Nnam.entities.Utilisateur;
import com.logonedigital.Nnam.repository.UtilisateurRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{
    private final UtilisateurRepo utilisateurRepo;

    public UtilisateurServiceImpl (UtilisateurRepo utilisateurRepo){
        this.utilisateurRepo = utilisateurRepo;
    }


    @Override
    public void addUtilisateur(Utilisateur utilisateur) {
        utilisateurRepo.save(utilisateur);
    }

    @Override
    public Utilisateur getUtilisateur(int idUtilisateur) {
        return utilisateurRepo.findById(idUtilisateur).orElse(null);
    }

    @Override
    public List<Utilisateur> getUtilisateurs() {
        return utilisateurRepo.findAll();
    }

    @Override
    public void updateUtilisateur(Integer idUtilisateur, Utilisateur utilisateur) {
        Utilisateur existingUser = utilisateurRepo.findById(idUtilisateur).orElse(null);

    }

    @Override
    public void deleteUtilisateur(Integer idUtilisateur) {
        utilisateurRepo.deleteById(idUtilisateur);

    }
}
