package com.logonedigital.Nnam.services.utilisateur;

import com.logonedigital.Nnam.Mapper.UtilisateurMapper;
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
    private UtilisateurMapper utilisateurMapper;

    public UtilisateurServiceImpl (UtilisateurRepo utilisateurRepo , UtilisateurMapper utilisateurMapper){
        this.utilisateurRepo = utilisateurRepo;
        this.utilisateurMapper = utilisateurMapper;
    }


    @Override
    public UtilisateurDTO addUtilisateur(UtilisateurDTO utilisateurDTO) {

        // Vérification si l'utilisateur existe déjà avec cet email
        boolean exists = utilisateurRepo.existsByEmail(utilisateurDTO.getEmail());
        if (exists) {
            throw new ResourceExistException("Un utilisateur avec cet email existe déjà !");
        }

        // Convertir le DTO en entité Utilisateur
        Utilisateur utilisateur = utilisateurMapper.toUtilisateur(utilisateurDTO);

        // Sauvegarde en base de données
        Utilisateur savedUtilisateur = utilisateurRepo.save(utilisateur);

        // Retourner l'entité sauvegardée sous forme de DTO
        return utilisateurMapper.toDTO(savedUtilisateur);

    }

    @Override
    public List<UtilisateurDTO> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurRepo.findAll();
      return this.utilisateurMapper.toEmployeDtoList(utilisateurs);
    }

    @Override
    public UtilisateurDTO getUtilisateurById(Integer idUtilisateur) {
        Utilisateur utilisateur = utilisateurRepo.findById(idUtilisateur)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'ID : " + idUtilisateur));

        UtilisateurDTO utilisateurDTO = utilisateurMapper.toDTO(utilisateur);

        utilisateurDTO.setIdUtilisateur(utilisateur.getIdUtilisateur());
        utilisateurDTO.setNomUtilisateur(utilisateur.getNomUtilisateur());
        utilisateurDTO.setEmail(utilisateur.getEmail());
        utilisateurDTO.setPrenomUtilisateur(utilisateur.getPrenomUtilisateur());
        utilisateurDTO.setBoitePostale(utilisateurDTO.getBoitePostale());
        utilisateurDTO.setLieuNaissance(utilisateurDTO.getLieuNaissance());
        utilisateurDTO.setVilleActuelle(utilisateurDTO.getVilleActuelle());
        utilisateurDTO.setQuartier(utilisateurDTO.getQuartier());
        utilisateurDTO.setDateDeCreation(utilisateur.getDateDeCreation());
        utilisateurDTO.setDateDeModification(utilisateur.getDateDeModification());
        utilisateurDTO.setStatut(utilisateur.getStatut());
        //utilisateurDTO.setIdRole(utilisateur.getRole());

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
