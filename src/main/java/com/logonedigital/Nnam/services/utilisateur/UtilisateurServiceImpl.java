package com.logonedigital.Nnam.services.utilisateur;

import com.logonedigital.Nnam.dto.UtilisateurDTO;
import com.logonedigital.Nnam.entities.Utilisateur;
import com.logonedigital.Nnam.exception.ResourceExistException;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.repository.UtilisateurRepo;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{
    private final UtilisateurRepo utilisateurRepo;

    public UtilisateurServiceImpl (UtilisateurRepo utilisateurRepo){
        this.utilisateurRepo = utilisateurRepo;
    }


    @Override
    public UtilisateurDTO addUtilisateur(UtilisateurDTO utilisateurDTO) {

        // Vérification si l'utilisateur existe déjà avec cet email
        boolean exists = utilisateurRepo.existsByEmail(utilisateurDTO.getEmail());
        if (exists) {
            throw new ResourceExistException("Un utilisateur avec cet email existe déjà !");
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNomUtilisateur(utilisateurDTO.getNomUtilisateur());
        utilisateur.setEmail(utilisateurDTO.getEmail());

        Utilisateur savedUtilisateur = utilisateurRepo.save(utilisateur);

        UtilisateurDTO savedUtilisateurDTO = new UtilisateurDTO();
        savedUtilisateurDTO.setIdUtilisateur(savedUtilisateur.getIdUtilisateur());
        savedUtilisateurDTO.setNomUtilisateur(savedUtilisateur.getNomUtilisateur());
        savedUtilisateurDTO.setEmail(savedUtilisateur.getEmail());

        return savedUtilisateurDTO;

    }

    @Override
    public List<UtilisateurDTO> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurRepo.findAll();
        return utilisateurs.stream().map(utilisateur -> {
            UtilisateurDTO dto = new UtilisateurDTO();
            dto.setIdUtilisateur(utilisateur.getIdUtilisateur());
            dto.setNomUtilisateur(utilisateur.getNomUtilisateur());
            dto.setEmail(utilisateur.getEmail());
            return dto;
        }).collect(Collectors.toList());

    }

    @Override
    public UtilisateurDTO getUtilisateurById(Integer id) {
        Utilisateur utilisateur = utilisateurRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'ID : " + id));

        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setIdUtilisateur(utilisateur.getIdUtilisateur());
        utilisateurDTO.setNomUtilisateur(utilisateur.getNomUtilisateur());
        utilisateurDTO.setEmail(utilisateur.getEmail());
        return utilisateurDTO;

    }

    @Override
    public UtilisateurDTO updateUtilisateur(Integer id, UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateurToUpdate = utilisateurRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'ID : " + id));

        // Vérification si l'email est déjà utilisé par un autre utilisateur
        boolean exists = utilisateurRepo.existsByEmail(utilisateurDTO.getEmail());
        if (exists && !utilisateurToUpdate.getEmail().equals(utilisateurDTO.getEmail())) {
            throw new ResourceExistException("L'email '" + utilisateurDTO.getEmail() + "' est déjà utilisé !");
        }

        utilisateurToUpdate.setNomUtilisateur(utilisateurDTO.getNomUtilisateur());
        utilisateurToUpdate.setEmail(utilisateurDTO.getEmail());

        Utilisateur updatedUtilisateur = utilisateurRepo.save(utilisateurToUpdate);

        UtilisateurDTO updatedUtilisateurDTO = new UtilisateurDTO();
        updatedUtilisateurDTO.setIdUtilisateur(updatedUtilisateur.getIdUtilisateur());
        updatedUtilisateurDTO.setNomUtilisateur(updatedUtilisateur.getNomUtilisateur());
        updatedUtilisateurDTO.setEmail(updatedUtilisateur.getEmail());

        return updatedUtilisateurDTO;


    }

    @Override
    public void deleteUtilisateur(Integer id) {
        Utilisateur utilisateur = utilisateurRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'ID : " + id));
        utilisateurRepo.delete(utilisateur);


    }
}
