package com.logonedigital.Nnam.services.livreur;

import com.logonedigital.Nnam.dto.LivreurReqDTO;
import com.logonedigital.Nnam.dto.LivreurResDTO;
import com.logonedigital.Nnam.entities.Livreur;
import com.logonedigital.Nnam.exception.ResourceExistException;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.mapper.LivreurMapper;
import com.logonedigital.Nnam.repository.LivreurRepo;
import com.logonedigital.Nnam.specification.LivreurSpecification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LivreurServiceImpl implements  LivreurService{

    private final LivreurRepo livreurRepo;
    private final LivreurMapper livreurMapper;

    public LivreurServiceImpl(LivreurRepo livreurRepo, LivreurMapper livreurMapper) {
        this.livreurRepo = livreurRepo;
        this.livreurMapper = livreurMapper;
    }

    @Override
    public void addLivreur(LivreurReqDTO livreurReqDTO) {
        Optional<Livreur> livreurToAdd = this.livreurRepo.findByNom(livreurReqDTO.getNom());
        if(livreurToAdd.isPresent())
            throw new ResourceExistException("La ressource existe deja !");

        Livreur livreur = this.livreurMapper.getLivreurFromLivreurReqDTO(livreurReqDTO);
        livreur.setCreatedAt(new Date());
        this.livreurRepo.save(livreur);
    }

    @Override
    public void deleteLivreur(Integer idLivreur) {

            Livreur livreurToDelete = this.livreurRepo.findById(idLivreur)
                    .orElseThrow(()-> new ResourceNotFoundException("La ressource n'existe pas !"));
            this.livreurRepo.delete(livreurToDelete);
    }

    @Override
    public void updateLivreur(Integer idLivreur, LivreurReqDTO livreurReqDTO) {
        Livreur livreurToUpdate = this.livreurRepo.findById(idLivreur)
                .orElseThrow(()-> new ResourceNotFoundException("La ressource n'existe pas !"));
        livreurToUpdate.setNom(livreurReqDTO.getNom());
        livreurToUpdate.setPrenom(livreurReqDTO.getPrenom());
        livreurToUpdate.setTelephone(livreurReqDTO.getTelephone());
        livreurToUpdate.setUpdatedAt(new Date());
        this.livreurRepo.saveAndFlush(livreurToUpdate);
    }

    @Override
    public LivreurResDTO getById(Integer idLivreur) {
        Livreur livreur= this.livreurRepo.findById(idLivreur)
                .orElseThrow(()-> new ResourceNotFoundException("La ressource n'existe pas !"));
        return this.livreurMapper.getLivreurResDTOFromLivreur(livreur);
    }

    @Override
    public List<LivreurResDTO> getAllLivreur() {
        List<Livreur> livreurs = this.livreurRepo.findAll();
        return  this.livreurMapper.getAllLivreurResDTOFromALlLivreur(livreurs);
    }

    @Override
    public List<LivreurResDTO> searchLivreurs(LivreurReqDTO searchDTO) {
        LivreurSpecification spec = new LivreurSpecification(searchDTO);
        List<Livreur> livreurs = livreurRepo.findAll(spec);
        return livreurMapper.getAllLivreurResDTOFromALlLivreur(livreurs);
    }
}
