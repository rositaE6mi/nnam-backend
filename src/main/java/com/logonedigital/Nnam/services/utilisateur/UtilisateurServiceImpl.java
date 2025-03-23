package com.logonedigital.Nnam.services.utilisateur;

import com.logonedigital.Nnam.Mapper.UtilisateurMapper;
import com.logonedigital.Nnam.dto.UtilisateurDTO;
import com.logonedigital.Nnam.entities.Utilisateur;
import com.logonedigital.Nnam.exception.ResourceExistException;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.repository.UtilisateurRepo;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import jakarta.persistence.Column;

import java.util.Date;
import java.util.stream.Collectors;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{private final UtilisateurRepo utilisateurRepo;


    private final UtilisateurMapper utilisateurMapper;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurServiceImpl(UtilisateurRepo utilisateurRepo, UtilisateurMapper utilisateurMapper, PasswordEncoder passwordEncoder) {
        this.utilisateurRepo = utilisateurRepo;
        this.utilisateurMapper = utilisateurMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UtilisateurDTO addUtilisateur(UtilisateurDTO utilisateurDTO) {
        if (utilisateurRepo.existsByEmail(utilisateurDTO.getEmail())) {
            throw new ResourceExistException("Un utilisateur avec cet email existe déjà !");
        }

        Utilisateur utilisateur = utilisateurMapper.toUtilisateur(utilisateurDTO);
        // Cryptage du mot de passe avant sauvegarde
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateurDTO.getMotDePasse()));
        Utilisateur savedUtilisateur = utilisateurRepo.save(utilisateur); // `dateDeCreation` et `dateDeModification` sont gérés par @PrePersist
        return utilisateurMapper.toUtilisateurDTO(savedUtilisateur);
    }

    @Override
    public List<UtilisateurDTO> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurRepo.findAll();
        return utilisateurMapper.toEmployeDtoList(utilisateurs);
    }

    @Override
    public UtilisateurDTO getUtilisateurById(Integer idUtilisateur) {
        Utilisateur utilisateur = utilisateurRepo.findById(idUtilisateur)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'ID : " + idUtilisateur));
        return utilisateurMapper.toUtilisateurDTO(utilisateur);
    }

    @Override
    public UtilisateurDTO updateUtilisateur(Integer id, UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = utilisateurRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'ID : " + id));

        if (!utilisateur.getEmail().equals(utilisateurDTO.getEmail()) && utilisateurRepo.existsByEmail(utilisateurDTO.getEmail())) {
            throw new ResourceExistException("L'email '" + utilisateurDTO.getEmail() + "' est déjà utilisé !");
        }

        utilisateur.setNomUtilisateur(utilisateurDTO.getNomUtilisateur());
        utilisateur.setPrenomUtilisateur(utilisateurDTO.getPrenomUtilisateur());
        utilisateur.setEmail(utilisateurDTO.getEmail());

        Utilisateur updatedUtilisateur = utilisateurRepo.save(utilisateur); // `dateDeModification` est gérée par @PreUpdate
        return utilisateurMapper.toUtilisateurDTO(updatedUtilisateur);
    }

    @Override
    public void deleteUtilisateur(Integer id) {
        Utilisateur utilisateur = utilisateurRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'ID : " + id));
        utilisateurRepo.delete(utilisateur);
    }

    public UtilisateurRepo getUtilisateurRepo() {
        return utilisateurRepo;
    }
}
