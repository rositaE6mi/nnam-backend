package com.logonedigital.Nnam.Mapper;

import com.logonedigital.Nnam.dto.UtilisateurDTO;
import com.logonedigital.Nnam.entities.Utilisateur;
import com.logonedigital.Nnam.repository.UtilisateurRepo;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Mapper(componentModel = "spring")
@Configuration
public interface UtilisateurMapper {
    Utilisateur toUtilisateur (UtilisateurDTO utilisateurDTO);

    UtilisateurDTO toDTO (Utilisateur utilisateur);

    List <UtilisateurDTO> toEmployeDtoList(List<Utilisateur> utilisateurs);


}
