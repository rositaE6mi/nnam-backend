package com.logonedigital.Nnam.Mapper;

import com.logonedigital.Nnam.dto.ProfilDTO;
import com.logonedigital.Nnam.dto.UtilisateurDTO;
import com.logonedigital.Nnam.dto.RoleDTO;
import com.logonedigital.Nnam.entities.Profil;
import com.logonedigital.Nnam.entities.Role;
import com.logonedigital.Nnam.entities.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfilMapper {

    ProfilMapper INSTANCE = Mappers.getMapper(ProfilMapper.class);

    // Conversion de ProfilDTO en Profil
    @Mapping(target = "utilisateur", source = "idUtilisateur", qualifiedByName = "mapIdToUtilisateur")
    @Mapping(target = "role", source = "idRole", qualifiedByName = "mapIdToRole") // Utilisation de idRole et non de role
    Profil toProfil(ProfilDTO profilDTO);

    // Conversion de Profil en ProfilDTO
    @Mapping(target = "idUtilisateur", source = "utilisateur.idUtilisateur")
    @Mapping(target = "idRole", source = "role.idRole")
    ProfilDTO toProfilDTO(Profil profil);

    // Conversion d'une liste de Profils en une liste de ProfilDTOs
    List<ProfilDTO> toProfilDTOList(List<Profil> profils);

    // Méthode pour convertir un idUtilisateur en objet Utilisateur
    @Named("mapIdToUtilisateur")
    default Utilisateur mapIdToUtilisateur(Integer idUtilisateur) {
        if (idUtilisateur == null) {
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdUtilisateur(idUtilisateur);
        return utilisateur;
    }

    // Méthode pour convertir un idRole en objet Role
    @Named("mapIdToRole")
    default Role mapIdToRole(Integer idRole) {
        if (idRole == null) {
            return null;
        }
        Role role = new Role();
        role.setIdRole(idRole);
        return role;
    }
}
