package com.logonedigital.Nnam.Mapper;

import com.logonedigital.Nnam.dto.ProfilDTO;
import com.logonedigital.Nnam.entities.Profil;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Mapper(componentModel = "spring")
@Configuration
public interface ProfilMapper {
    Profil toProfil (ProfilDTO profilDTO);

    List<ProfilDTO> toProfilDTOList (List<Profil> profils);

    ProfilDTO toProfilDTO (Profil profil);




}
