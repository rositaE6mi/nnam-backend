package com.logonedigital.Nnam.services.profil;

import com.logonedigital.Nnam.Mapper.ProfilMapper;
import com.logonedigital.Nnam.dto.ProfilDTO;
import com.logonedigital.Nnam.entities.Profil;
import com.logonedigital.Nnam.exception.ResourceExistException;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.repository.ProfilRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfilServiceImpl implements ProfilService{
    private final ProfilRepo profilRepo;
    private ProfilMapper profilMapper;


    public ProfilServiceImpl (ProfilRepo profilRepo,ProfilMapper profilMapper){
        this.profilRepo = profilRepo;
        this.profilMapper = profilMapper;
    }


    @Override
    public ProfilDTO addProfil(ProfilDTO profilDTO) {

        // Vérification si un profil avec le même nom existe déjà
        boolean exists = profilRepo.existsByNomProfil(profilDTO.getNomProfil());
        if (exists) {
            throw new ResourceExistException("Le profil '" + profilDTO.getNomProfil() + "' existe déjà !");
        }

        Profil profil = new Profil();
        profil.setNomProfil(profilDTO.getNomProfil());
        profil.setIdProfil(profil.getIdProfil());
        Profil savedProfil = profilRepo.save(profil);

        return this.profilMapper.toProfilDTO(savedProfil);

    }

    @Override
    public List<ProfilDTO> getAllProfils() {
        List<Profil> profils = profilRepo.findAll();
        return this.profilMapper.toProfilDTOList(profils);

    }

    @Override
    public ProfilDTO getProfilById(Integer idProfil) {
        Profil profil = profilRepo.findById(idProfil)
                .orElseThrow(() -> new ResourceNotFoundException("Profil non trouvé avec l'ID : " + idProfil));

        ProfilDTO profilDTO = new ProfilDTO();
        profilDTO.setIdProfil(profil.getIdProfil());
        profilDTO.setNomProfil(profil.getNomProfil());
        return profilDTO;

    }

    @Override
    public ProfilDTO updateProfil(Integer idProfil, ProfilDTO profilDTO) {
        Profil profilToUpdate = profilRepo.findById(idProfil)
                .orElseThrow(() -> new ResourceNotFoundException("Profil non trouvé avec l'ID : " + idProfil));

        // Vérification si le nom du profil existe déjà (sauf si c'est le même)
        boolean exists = profilRepo.existsByNomProfil(profilDTO.getNomProfil());
        if (exists && !profilToUpdate.getNomProfil().equals(profilDTO.getNomProfil())) {
            throw new ResourceExistException("Le profil '" + profilDTO.getNomProfil() + "' existe déjà !");
        }

        profilToUpdate.setNomProfil(profilDTO.getNomProfil());

        Profil updatedProfil = profilRepo.save(profilToUpdate);

        ProfilDTO updatedProfilDTO = new ProfilDTO();
        updatedProfilDTO.setIdProfil(updatedProfil.getIdProfil());
        updatedProfilDTO.setNomProfil(updatedProfil.getNomProfil());

        return updatedProfilDTO;

    }

    @Override
    public void deleteProfil(Integer idProfil) {
        Profil profil = profilRepo.findById(idProfil)
                .orElseThrow(() -> new ResourceNotFoundException("Profil non trouvé avec l'ID : " + idProfil));
        profilRepo.delete(profil);


    }
}
