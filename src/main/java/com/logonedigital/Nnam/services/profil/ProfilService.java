package com.logonedigital.Nnam.services.profil;

import com.logonedigital.Nnam.entities.Profil;

import java.util.List;

public interface ProfilService {

        void addProfil (Profil profil);
        Profil getProfil (int idProfil);
        List<Profil> getProfil();
        void updateProfil (Integer idProfil, Profil profil);
        void deleteProfil (Integer idProfil);
}
