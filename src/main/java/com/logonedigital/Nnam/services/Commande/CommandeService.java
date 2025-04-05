package com.logonedigital.Nnam.services.Commande;

import com.logonedigital.Nnam.dto.CommandeDTO;
import com.logonedigital.Nnam.entities.Commande;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CommandeService {
    void addCommande(@Valid Commande commande);
    Optional<Commande> getCommandeById(Integer id);
    void UpdateCommande(Integer commandeId, Commande commande);
    CommandeDTO getCommandeDTO(Integer id);
    boolean DeleteCommande(Integer commandeId);
    List<CommandeDTO> listerCommandes();

}
