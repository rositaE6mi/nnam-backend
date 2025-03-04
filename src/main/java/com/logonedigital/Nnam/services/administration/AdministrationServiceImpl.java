package com.logonedigital.Nnam.services.administration;

import com.logonedigital.Nnam.entities.Administration;
import com.logonedigital.Nnam.exceptions.ResourceExistException;
import com.logonedigital.Nnam.exceptions.ResourceNotFoundException;
import com.logonedigital.Nnam.repositories.AdministrationRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class AdministrationServiceImpl implements AdministrationService{
    private final AdministrationRepo administrationRepo;

    public AdministrationServiceImpl(AdministrationRepo administrationRepo){
        this.administrationRepo = administrationRepo;
    }


    @Override
    public void addAdministration(Administration administration) {
        Optional<Administration> administrationToAdd = Optional.ofNullable(this.administrationRepo.findByNom(administration.getNom()));

        if(administrationToAdd.isPresent())
            throw new ResourceExistException("La ressource exite deja!");

        administration.setCreatedAt(new Date());
        this.administrationRepo.save(administration);
    }

    @Override
    public void deleteAdministration(Integer idAdministration) {
        Administration administrationToDelete = this.administrationRepo.findById(idAdministration)
                .orElseThrow(()-> new ResourceNotFoundException("Ressource non trouvee !"));
        this.administrationRepo.delete(administrationToDelete);
    }

    @Override
    public void updateAdministration(Integer idAdministration, Administration administration) {
        Administration administrationToUpdate= this.administrationRepo.findById(idAdministration)
                .orElseThrow(()-> new ResourceNotFoundException("Ressource non trouvee !"));
        administrationToUpdate.setNom(administration.getNom());
        administrationToUpdate.setUpdatedAt(new Date());
        this.administrationRepo.saveAndFlush(administrationToUpdate);
    }

    @Override
    public Administration getAdministrationById(Integer idAdministration) {
        Administration getAdministration = this.administrationRepo.findById(idAdministration)
                .orElseThrow(()-> new ResourceNotFoundException("Ressource non trouvee !"));
        return getAdministration;
    }

    @Override
    public List<Administration> getAllAdministration() {
        return this.administrationRepo.findAll();
    }
}
