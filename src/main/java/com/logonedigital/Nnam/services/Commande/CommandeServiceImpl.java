package com.logonedigital.Nnam.services.Commande;

import com.logonedigital.Nnam.entities.Commande;
import com.logonedigital.Nnam.repository.CommandeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepo commandeRepo;


    public CommandeServiceImpl(CommandeRepo commandeRepo) {
        this.commandeRepo = commandeRepo;
    }


    @Override
    public void addCommande(CommandeService commandeService) {

    }

    @Override
    public Optional<Commande> UpdateCommande(Integer commandeId, Commande commande) {
        return Optional.empty();
    }

    @Override
    public boolean DeleteCommande(Integer commandeId) {
        return false;
    }

    @Override
    public List<Commande> listerCommandes() {
        return List.of();
    }
}
