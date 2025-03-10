package com.logonedigital.Nnam.services.Categorie;

import com.logonedigital.Nnam.entities.Categorie;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public interface CategorieService {
    Categorie addCategorie(Categorie categorie);
    Categorie updateCategorie(int id, Categorie categorie);
    void deleteCategorie(int idCat);
    Categorie getCategorie(int idCat);
    List<Categorie> getAllCategories();

    boolean existsById(int idCat);

    boolean existsByNomCat(@NotBlank(message = "Le nom de la categorie est obligatoire") String nomCat);

    List<Categorie> searchCategories(String nomCat, String description, Integer minProduits);
}
