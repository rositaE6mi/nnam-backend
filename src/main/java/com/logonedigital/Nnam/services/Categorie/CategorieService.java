package com.logonedigital.Nnam.services.Categorie;

import com.logonedigital.Nnam.entities.Categorie;
import jakarta.validation.Valid;

import java.util.List;

public interface CategorieService {
    Categorie addCategorie(Categorie categorie);
    Categorie updateCategorie(int id, Categorie categorie);
    void deleteCategorie(int idCat);
    Categorie getCategorie(int idCat);
    List<Categorie> getAllCategories();

    boolean existsById(int idCat);
}
