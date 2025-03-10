package com.logonedigital.Nnam.services.Categorie;

import com.logonedigital.Nnam.entities.Categorie;
import com.logonedigital.Nnam.exception.ResourceExistException;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.repository.CategorieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    private CategorieRepo categorieRepository;

    @Override
    public Categorie addCategorie(Categorie categorie) {
        if (categorieRepository.existsByNomCat(categorie.getNomCat())) {
            throw new ResourceExistException("Une catégorie avec le nom '" + categorie.getNomCat() + "' existe déjà.");
        }
        return categorieRepository.save(categorie);
    }

    @Override
    public Categorie updateCategorie(int id, Categorie categorie) {
        Categorie existingCategorie = categorieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catégorie non trouvée avec l'ID : " + id));
        existingCategorie.setNomCat(categorie.getNomCat());
        existingCategorie.setDescription(categorie.getDescription());
        return categorieRepository.save(existingCategorie);
    }

    @Override
    public void deleteCategorie(int idCat) {
        if (!categorieRepository.existsById(idCat)) {
            throw new ResourceNotFoundException("Catégorie non trouvée avec l'ID : " + idCat);
        }
        categorieRepository.deleteById(idCat);
    }

    @Override
    public Categorie getCategorie(int idCat) {
        return categorieRepository.findById(idCat)
                .orElseThrow(() -> new ResourceNotFoundException("Catégorie non trouvée avec l'ID : " + idCat));
    }

    @Override
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    @Override
    public boolean existsById(int idCat) {
        return categorieRepository.existsById(idCat);
    }

    @Override
    public boolean existsByNomCat(String nomCat) {
        return false;
    }

    @Override
    public List<Categorie> searchCategories(String nomCat, String description, Integer minProduits) {
       /* if (nomCat != null && description != null){
            return categorieRepository.findByNomCatContainingAndDescriptionContainingAndProduitsSizeGreaterThanEqual(nomCat, description, minProduits);
        }*/
        return categorieRepository.findByNomCatContainingAndDescriptionContainingAndProduitsSizeGreaterThanEqual(
                nomCat,
                description,
                minProduits
        );
    }
}