package com.logonedigital.Nnam.services.Categorie;

import com.logonedigital.Nnam.dto.categorie.CategorieReqDTO;
import com.logonedigital.Nnam.dto.categorie.CategorieResDTO;
import com.logonedigital.Nnam.entities.Categorie;
import com.logonedigital.Nnam.entities.Produit;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategorieService {
    Categorie addCategorie(@Valid CategorieReqDTO categorieReqDTO);
    CategorieResDTO updateCategorie(int id, CategorieReqDTO categorieReqDTO);
    void deleteCategorie(int idCat);
    CategorieResDTO getCategorie(int idCat);
    List<CategorieResDTO> getAllCategories(List<Categorie> categories);

    boolean existsById(int idCat);

    boolean existsByNomCat(@NotBlank(message = "Le nom de la categorie est obligatoire") String nomCat);

    Page<Categorie> getAllCategorie(Pageable pageable);

    List<Categorie> searchCategories(String nomCat, String description, Integer minProduits);
}
