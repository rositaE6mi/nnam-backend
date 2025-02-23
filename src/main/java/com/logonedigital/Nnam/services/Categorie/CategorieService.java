package com.logonedigital.Nnam.services.Categorie;

import com.logonedigital.Nnam.entities.Categorie;
import jakarta.validation.Valid;

import java.util.List;

public interface CategorieService {
    void addCategorie(@Valid Categorie categorie);
    Categorie getCategorieById(Integer IdCat);
    List<Categorie> getAllCategories();
    //Optional<Produit> getProduitById(int id);
    void updateCategorie(Integer IdCat, Categorie categorie);
    void deleteCategorie(Integer IdCat);
}
