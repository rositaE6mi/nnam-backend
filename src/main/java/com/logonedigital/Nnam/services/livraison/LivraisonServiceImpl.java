package com.logonedigital.Nnam.services.livraison;

import com.logonedigital.Nnam.dto.LivraisonReqDTO;
import com.logonedigital.Nnam.dto.LivraisonResDTO;
import com.logonedigital.Nnam.entities.Livraison;
import com.logonedigital.Nnam.entities.Livreur;
import com.logonedigital.Nnam.exceptions.ResourceExistException;
import com.logonedigital.Nnam.exceptions.ResourceNotFoundException;
import com.logonedigital.Nnam.mapper.LivraisonMapper;
import com.logonedigital.Nnam.repositories.LivraisonRepo;
import com.logonedigital.Nnam.repositories.LivreurRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LivraisonServiceImpl implements LivraisonService{

    private final LivraisonRepo livraisonRepo;
    private final LivraisonMapper livraisonMapper;
    private final LivreurRepo livreurRepo;

    public LivraisonServiceImpl(LivraisonRepo livraisonRepo, LivraisonMapper livraisonMapper, LivreurRepo livreurRepo) {
        this.livraisonRepo = livraisonRepo;
        this.livraisonMapper = livraisonMapper;
        this.livreurRepo = livreurRepo;
    }

    @Override
    public void addLivraison(LivraisonReqDTO livraisonReqDTO) {
        Optional<Livraison> livraisonToAdd = this.livraisonRepo
                .findByAdresseDestination(livraisonReqDTO.getAdresseDestination());

        if(livraisonToAdd.isPresent())
            throw new ResourceExistException("La ressource existe deja!");

        Livreur livreur = this.livreurRepo.findById(livraisonReqDTO.getLivreurId())
                .orElseThrow(()-> new ResourceExistException("La ressource existe deja!"));
    Livraison livraison = this.livraisonMapper.getLivraisonFromLivraisonDTO(livraisonReqDTO);
        livraison.setCreatedAt(new Date());
        livraison.setLivreur(livreur);
        this.livraisonRepo.save(livraison);
    }

    @Override
    public void deleteLivraison(Integer idLivraison) {

        Livraison livraisonToDelete = this.livraisonRepo.findById(idLivraison)
                .orElseThrow(()->new ResourceNotFoundException("Ressource non trouvee !"));
        this.livraisonRepo.delete(livraisonToDelete);
    }

    @Override
    public void updateLivraison(Integer idLivraison, LivraisonReqDTO livraison) {
        Livraison livraisonToUpdate = this.livraisonRepo.findById(idLivraison)
                .orElseThrow(()->new ResourceNotFoundException("Ressource non trouvee !"));
        livraisonToUpdate.setAdresseDestination(livraison.getAdresseDestination());
        livraisonToUpdate.setDateDeLivraison(livraison.getDateDeLivraison());
        livraisonToUpdate.setEtatDeLivraison(livraison.getEtatDeLivraison());
        livraisonToUpdate.setUpdatedAt(new Date());
        this.livraisonRepo.saveAndFlush(livraisonToUpdate);

    }

    @Override
    public LivraisonResDTO getLivriasonById(Integer idLivraison) {
       Livraison livraison= this.livraisonRepo.findById(idLivraison)
                .orElseThrow(()->new ResourceNotFoundException("Ressource non trouvee !"));
        return this.livraisonMapper.getLivraisonResDTOFromLivraison(livraison);
    }

    @Override
    public List<LivraisonResDTO> getAllLivraison() {
        List<Livraison> livraisons = this.livraisonRepo.findAll();
        return this.livraisonMapper.getAllLivraisonResDTOFromAllLIvraison(livraisons);
    }

    @Override
    public List<Livraison> getLivraisonsByLivreurId(Long livreurId) {
        return livraisonRepo.findLivraisonsByLivreurId(livreurId);
    }
}
