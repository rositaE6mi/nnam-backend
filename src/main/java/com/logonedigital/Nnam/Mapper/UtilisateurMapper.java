package com.logonedigital.Nnam.Mapper;

import com.logonedigital.Nnam.dto.UtilisateurDTO;
import com.logonedigital.Nnam.entities.Role;
import com.logonedigital.Nnam.entities.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {

    // Conversion de Utilisateur en UtilisateurDTO
    @Mapping(target = "idRole", source = "role.idRole")  // Mapper l'ID du role dans UtilisateurDTO

    @Mapping(target = "motDePasse", ignore = true) // Ignorer le mot de passe lors de la conversion vers DTO

    // Assurer qu'il n'y a pas de tentatives d'ignorer des propriétés non existantes dans UtilisateurDTO
    UtilisateurDTO toUtilisateurDTO(Utilisateur utilisateur);

    // Conversion de UtilisateurDTO en Utilisateur
    @Mapping(target = "role", source = "idRole")  // Mapper l'ID de Role dans Utilisateur
    Utilisateur toUtilisateur(UtilisateurDTO utilisateurDTO);

    // Méthode personnalisée pour mapper l'ID du role en un objet Role
    @Mapping(target = "idRole", source = "idRole")  // Mapper l'ID dans un objet Role
    default Role mapIdToRole(Integer idRole) {
        if (idRole == null) {
            return null;
        }
        Role role = new Role();
        role.setIdRole(idRole);  // Initialiser le role avec l'ID
        return role;
    }

    // Conversion d'une liste d'Utilisateur en une liste de UtilisateurDTO
    List<UtilisateurDTO> toUtilisateurDTOList(List<Utilisateur> utilisateurs);

    List<UtilisateurDTO> toEmployeDtoList(List<Utilisateur> utilisateurs);
}
