package com.logonedigital.Nnam.services.profil;

import com.logonedigital.Nnam.entities.Profil;
import com.logonedigital.Nnam.repository.ProfilRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfilServiceImpl implements ProfilService{
    private final ProfilRepo profilRepo;

    public ProfilServiceImpl (ProfilRepo profilRepo){
        this.profilRepo = profilRepo;
    }

    @Override
    public void addProfil(Profil profil) {
        profilRepo.save(profil);

    }

    @Override
    public Profil getProfil(int idProfil) {
        return profilRepo.findById(idProfil).orElse(null);
    }

    @Override
    public List<Profil> getProfil() {
        return profilRepo.findAll();
    }

    @Override
    public void updateProfil(Integer idProfil, Profil profil) {
        Profil existingProfil = profilRepo.findById(idProfil).orElse(null);

    }

    @Override
    public void deleteProfil(Integer idProfil) {
        profilRepo.deleteById(idProfil);

    }

    @Override
    public Profil save(Profil profil) {
        return null;
    }
}
