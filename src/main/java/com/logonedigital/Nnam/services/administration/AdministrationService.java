package com.logonedigital.Nnam.services.administration;

import com.logonedigital.Nnam.entities.Administration;

import java.util.List;

public interface AdministrationService {
    void addAdministration(Administration administration);
    void deleteAdministration(Integer idAdministration);
    void updateAdministration(Integer idAdministration, Administration administration);
    Administration getAdministrationById(Integer idAdministration);
    List<Administration> getAllAdministration();

}
