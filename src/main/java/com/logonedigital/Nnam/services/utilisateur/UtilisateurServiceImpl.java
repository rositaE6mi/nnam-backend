/*package com.logonedigital.Nnam.services.utilisateur;

import com.logonedigital.Nnam.Mapper.UtilisateurMapper;
import com.logonedigital.Nnam.dto.UtilisateurDTO;
import com.logonedigital.Nnam.entities.Utilisateur;
import com.logonedigital.Nnam.exception.ResourceExistException;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.repository.UtilisateurRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Override
    public Page<UtilisateurDTO> getUtilisateurs(int page, int size, String sortBy, String sortDirection) {
        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        // Récupérer la page des utilisateurs depuis le repository
        Page<Utilisateur> utilisateursPage = utilisateurRepo.findAll(pageable);

        // Mapper la page des entités Utilisateur vers la page des DTO UtilisateurDTO
        Page<UtilisateurDTO> utilisateurDTOPage = utilisateursPage.map(utilisateurMapper::toUtilisateurDTO);

        return utilisateurDTOPage;
    }

    public UtilisateurRepo getUtilisateurRepo() {
        return utilisateurRepo;
    }
}

*/
package com.logonedigital.Nnam.services.utilisateur;

import com.logonedigital.Nnam.Mapper.UtilisateurMapper;
import com.logonedigital.Nnam.dto.UtilisateurDTO;
import com.logonedigital.Nnam.entities.Utilisateur;
import com.logonedigital.Nnam.exception.ResourceExistException;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.repository.UtilisateurRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepo utilisateurRepo;
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
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateurDTO.getMotDePasse()));
        Utilisateur savedUtilisateur = utilisateurRepo.save(utilisateur);
        return utilisateurMapper.toUtilisateurDTO(savedUtilisateur);
    }

    @Override
    public List<UtilisateurDTO> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurRepo.findAll();
        return utilisateurMapper.toUtilisateurDtoList(utilisateurs);
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
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateurDTO.getMotDePasse()));
        Utilisateur updatedUtilisateur = utilisateurRepo.save(utilisateur);
        return utilisateurMapper.toUtilisateurDTO(updatedUtilisateur);
    }

    @Override
    public void deleteUtilisateur(Integer id) {
        Utilisateur utilisateur = utilisateurRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'ID : " + id));
        utilisateurRepo.delete(utilisateur);
    }

    @Override
    public Page<UtilisateurDTO> getUtilisateurs(int page, int size, String sortBy, String sortDirection) {
        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<Utilisateur> utilisateursPage = utilisateurRepo.findAll(pageable);
        return utilisateursPage.map(utilisateurMapper::toUtilisateurDTO);
    }

    @Override
    /*public Optional<String> login(String email, String motDePasse) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepo.findByEmail(email);
        if (utilisateurOptional.isPresent()) {
            Utilisateur utilisateur = utilisateurOptional.get();
            if (passwordEncoder.matches(motDePasse, utilisateur.getMotDePasse())) {
                String role = utilisateur.getRole().getNomRole().trim().toUpperCase(); // ✅ normalisation ici
                System.out.println("Role trouvé : " + role); // pour vérification console
                return Optional.of(role);
            }
        }
        return Optional.empty();
    }*/
    public Optional<String> login(String email, String motDePasse) {
        Optional<Utilisateur> userOpt = utilisateurRepo.findByEmail(email);
        if (userOpt.isPresent()) {
            Utilisateur user = userOpt.get();
            if (passwordEncoder.matches(motDePasse, user.getMotDePasse())) {
                String role = "";
                if (user.getRole().getIdRole() == 4) role = "ADMIN";
                else if (user.getRole().getIdRole() == 2) role = "AGRICULTEUR";
                else if (user.getRole().getIdRole() == 3) role = "CLIENT";
                return Optional.of(role);
            }
        }
        return Optional.empty();
    }

    @Override
    public UtilisateurDTO getByEmail(String email) {
        Utilisateur utilisateur = utilisateurRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'email : " + email));
        return utilisateurMapper.toUtilisateurDTO(utilisateur);
    }




}
