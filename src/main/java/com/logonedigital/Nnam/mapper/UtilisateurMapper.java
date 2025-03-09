package com.logonedigital.Nnam.mapper;

import com.logonedigital.Nnam.dto.UtilisateurDTO;
import com.logonedigital.Nnam.entities.Utilisateur;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Mapper(componentModel = "spring")
@Configuration
public interface UtilisateurMapper {
    Utilisateur toUtilisateur(UtilisateurDTO utilisateurDTO);

    UtilisateurDTO  toDto(Utilisateur utilisateur);

    List<UtilisateurDTO> toDtoList(List<Utilisateur> utilisateurs);

}
