package com.logonedigital.Nnam.services.Facture;

import com.logonedigital.Nnam.dto.FactureDTO;
import com.logonedigital.Nnam.entities.Facture;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.repository.FactureRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FactureServiceImpl implements FactureService {
    private final FactureRepo factureRepo;

    public FactureServiceImpl(FactureRepo factureRepo) {
        this.factureRepo = factureRepo;
    }
    // 📌 Ajouter une facture
    @Override
    public Facture addfacture(Facture facture){
        facture.setDateFacturation(new Date());
        return factureRepo.save(facture);
 }
    // 📌 Obtenir une facture par ID
    @Override
    public Optional<FactureDTO> getFacture(Integer id){
        return factureRepo.findById(id)
                .map(Facture -> new FactureDTO(
                        Facture.getFactureId(),
                        Facture.getDateFacturation(),
                        Facture.getMontantTotal()
                ));
    }
    // 📌 Lister toutes les factures
    @Override
    public List<FactureDTO> ListerFactures(){
        return factureRepo.findAll()
                .stream().map(Facture -> new FactureDTO(
                        Facture.getFactureId(),
                        Facture.getDateFacturation(),
                        Facture.getMontantTotal()
                        ))
                .collect(Collectors.toList());
    }
    // 📌 Mettre à jour une facture
    @Override
    public Facture Updatefacture(Integer id, Facture facture){
        return factureRepo.findById(id).map(existingFacture ->{
            existingFacture.setDateFacturation(facture.getDateFacturation());
            existingFacture.setMontantTotal(facture.getMontantTotal());
            return factureRepo.save(existingFacture);
        }).orElseThrow(() -> new ResourceNotFoundException("Facture non trouvée avec ID : " + id));
    }
    @Override
    public boolean DeleteFacture(Integer id){
        if (factureRepo.existsById(id)){
            factureRepo.deleteById(id);
            return true;
        }
        return false;
    }

}