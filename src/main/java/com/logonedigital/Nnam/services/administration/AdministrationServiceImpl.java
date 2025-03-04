package com.logonedigital.Nnam.services.administration;

import com.logonedigital.Nnam.entities.Administration;
import com.logonedigital.Nnam.repositories.AdministrationRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class AdministrationServiceImpl implements AdministrationService{
    private final AdministrationRepo administrationRepo;

    public AdministrationServiceImpl(AdministrationRepo administrationRepo){
        this.administrationRepo = administrationRepo;
    }


    @Override
    public void addAdministration(Administration administration) {
        administration.setCreatedAt(new Date());
        this.administrationRepo.save(administration);
    }

    @Override
    public void deleteAdministration(Integer idAdministration) {
        Administration administrationToDelete = this.administrationRepo.findById(idAdministration).get();
        this.administrationRepo.delete(administrationToDelete);
    }

    @Override
    public void updateAdministration(Integer idAdministration, Administration administration) {
        Administration administrationToUpdate= this.administrationRepo.findById(idAdministration).get();
        administrationToUpdate.setNom(administration.getNom());
        administrationToUpdate.setUpdatedAt(new Date());
        this.administrationRepo.saveAndFlush(administrationToUpdate);
    }

    @Override
    public Administration getAdministrationById(Integer idAdministration) {
        Administration getAdministration = this.administrationRepo.findById(idAdministration).get();
        return getAdministration;
    }

    @Override
    public List<Administration> getAllAdministration() {
        return this.administrationRepo.findAll();
    }
}
