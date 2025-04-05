package com.logonedigital.Nnam.services.utilisateur;

import com.logonedigital.Nnam.dto.UtilisateurDTO;
import com.logonedigital.Nnam.entities.Utilisateur;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UtilisateurService {
    UtilisateurDTO addUtilisateur (UtilisateurDTO utilisateurDTO);
    List<UtilisateurDTO> getAllUtilisateurs();
    UtilisateurDTO getUtilisateurById(Integer id);
    UtilisateurDTO updateUtilisateur(Integer id, UtilisateurDTO utilisateurDTO);
    void deleteUtilisateur(Integer id);
    Page<UtilisateurDTO> getUtilisateurs(int page, int size, String sortBy, String sortDirection);

}
