package com.logonedigital.Nnam.services.Categorie;

import com.logonedigital.Nnam.dto.categorie.CategorieReqDTO;
import com.logonedigital.Nnam.dto.categorie.CategorieResDTO;
import com.logonedigital.Nnam.entities.Categorie;
import com.logonedigital.Nnam.exception.ResourceExistException;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.mapper.CategorieMapper;
import com.logonedigital.Nnam.repository.CategorieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    private CategorieRepo categorieRepository;
    @Autowired
    private CategorieMapper categorieMapper;

    @Override
    public Categorie addCategorie(CategorieReqDTO categorieReqDTO) {
        if (categorieRepository.existsByNomCat(categorieReqDTO.getNomCat())) {
            throw new ResourceExistException("Une catégorie avec le nom '" + categorieReqDTO.getNomCat() + "' existe déjà.");
        }
        Categorie categorie = this.categorieMapper.getCategorieFromCategorieReqDTO(categorieReqDTO);

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
    public CategorieResDTO getCategorie(int idCat) {
        Categorie categorie = this.categorieRepository.findById(idCat)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Not found!")
                );
        return this.categorieMapper.getCategorieResDTOFromCategorie(categorie);
    }



    @Override
    public List<CategorieResDTO> getAllCategories() {
        List<Categorie> categories = categorieRepository.findAll();
       //Debug: Affichons les donnees de l'entite
        categories.forEach(cat -> System.out.println("Entity -> ID: " + cat.getIdCat()
                + ", Nom: " + cat.getNomCat()
                + ", Desc: " + cat.getDescription()));

        return categories.stream()
                .map(cat -> {
                    CategorieResDTO dto = categorieMapper.getCategorieResDTOFromCategorie(cat);
                    System.out.println("DTO -> ID: " + dto.getIdCat()
                            + ", Nom: " + dto.getNomCat()
                            + ", Desc: " + dto.getDescription()); // Debug
                    return dto;
/*
        return categories.stream()
                .map(cat -> {
                    CategorieResDTO dto = categorieMapper.getCategorieResDTOFromCategorie(cat);
                    return dto;
                */})
                .toList(); //Collections.singletonList(this.categorieMapper.getCategorieResDTOFromCategorie((Categorie) categorie));
    }

    @Override
    public boolean existsById(int idCat) {
        return categorieRepository.existsById(idCat);
    }

    @Override
    public boolean existsByNomCat(String nomCat) {
        return categorieRepository.existsByNomCat(nomCat);
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