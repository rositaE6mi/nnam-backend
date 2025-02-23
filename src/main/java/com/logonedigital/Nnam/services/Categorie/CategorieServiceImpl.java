package com.logonedigital.Nnam.services.Categorie;

import com.logonedigital.Nnam.entities.Categorie;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.repository.CategorieRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieServiceImpl implements CategorieService {
    private  final CategorieRepo categorieRepo;


    public CategorieServiceImpl(CategorieRepo categorieRepo) {
        this.categorieRepo = categorieRepo;
    }

    @Override
    public void addCategorie(Categorie categorie) {

        this.categorieRepo.save(categorie);
    }

    @Override
    public List<Categorie> getAllCategories() {

        return this.categorieRepo.findAll();
    }
    @Override
    public Categorie getCategorieById(Integer id) {
        Optional<Categorie> categorie = this.categorieRepo.findById(id);
        if (categorie.isEmpty())
            throw new ResourceNotFoundException("Resource not found ");
        return categorie.get();
    }

    public void updateCategorie(Integer id, Categorie categorie) {
        Categorie categorieToUpdate = this.categorieRepo
                .findById(id)
                .get();
        categorieToUpdate.setNomCat(categorie.getNomCat());
        categorieToUpdate.setDescription(categorie.getDescription());
        categorieToUpdate.setProduits(categorie.getProduits());
        this.categorieRepo.saveAndFlush(categorieToUpdate);
    }
    @Override
    public void deleteCategorie(Integer id) {
        Categorie categorie = this.categorieRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found exception"));
        this.categorieRepo.delete(categorie);
    }
}
