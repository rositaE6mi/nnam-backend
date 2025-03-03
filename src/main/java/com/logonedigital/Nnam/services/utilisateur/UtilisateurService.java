package com.logonedigital.Nnam.services.utilisateur;

import com.logonedigital.Nnam.entities.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    void addUtilisateur (Utilisateur utilisateur);
    Utilisateur getUtilisateur (int idUtilisateur);
    List <Utilisateur> getUtilisateurs();
    void updateUtilisateur (Integer idUtilisateur, Utilisateur utilisateur);
    void deleteUtilisateur (Integer idUtilisateur);
}
