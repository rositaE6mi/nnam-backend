package com.logonedigital.Nnam.services.profil;

import com.logonedigital.Nnam.dto.ProfilDTO;

import java.util.List;

public interface ProfilService {

        ProfilDTO addProfil(ProfilDTO profilDTO);
        List<ProfilDTO> getAllProfils();
        ProfilDTO getProfilById(Integer idProfil);
        ProfilDTO updateProfil(Integer idProfil, ProfilDTO profilDTO);
        void deleteProfil(Integer idProfil);

}
