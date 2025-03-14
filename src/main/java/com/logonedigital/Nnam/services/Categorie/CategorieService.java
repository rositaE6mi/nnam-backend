package com.logonedigital.Nnam.services.Categorie;

import com.logonedigital.Nnam.dto.categorie.CategorieReqDTO;
import com.logonedigital.Nnam.dto.categorie.CategorieResDTO;
import com.logonedigital.Nnam.entities.Categorie;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public interface CategorieService {
    Categorie addCategorie(@Valid CategorieReqDTO categorieReqDTO);
    Categorie updateCategorie(int id, Categorie categorie);
    void deleteCategorie(int idCat);
    CategorieResDTO getCategorie(int idCat);
    List<CategorieResDTO> getAllCategories();

    boolean existsById(int idCat);

    boolean existsByNomCat(@NotBlank(message = "Le nom de la categorie est obligatoire") String nomCat);

    List<Categorie> searchCategories(String nomCat, String description, Integer minProduits);
}
