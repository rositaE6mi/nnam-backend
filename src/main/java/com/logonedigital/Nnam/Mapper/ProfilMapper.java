package com.logonedigital.Nnam.Mapper;

import com.logonedigital.Nnam.dto.ProfilDTO;
import com.logonedigital.Nnam.entities.Profil;
import org.hibernate.mapping.List;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Mapper(componentModel = "spring")
@Configuration
public interface ProfilMapper {
    Profil toProfil (ProfilDTO profilDTO);

    ProfilDTO toDTO(Profil profil);

    List<ProfilDTO> toDTOList (List<Profil> profils);
}
