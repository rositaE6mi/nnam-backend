package com.logonedigital.Nnam.services.Facture;

import com.logonedigital.Nnam.dto.FactureDTO;
import com.logonedigital.Nnam.entities.Facture;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FactureService {

    // 📌 Ajouter une facture
    Facture addfacture(Facture facture);

    // 📌 Obtenir une facture par ID
    Optional<FactureDTO> getFacture(Integer id);

    // 📌 Lister toutes les factures
    List<FactureDTO> ListerFactures();

    // 📌 Mettre à jour une facture
    Facture Updatefacture(Integer id, Facture facture);

    // 📌 Supprimer une facture
    boolean DeleteFacture(Integer id);
}
