package com.logonedigital.Nnam.specification;

import com.logonedigital.Nnam.dto.LivraisonReqDTO;
import com.logonedigital.Nnam.entities.Livraison;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class LivraisonSpecification implements Specification<Livraison> {

    private final LivraisonReqDTO searchDTO;

    public LivraisonSpecification(LivraisonReqDTO searchDTO) {
        this.searchDTO = searchDTO;
    }

    @Override
    public Predicate toPredicate(Root<Livraison> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (searchDTO.getAdresseDestination() != null) {
            predicates.add(criteriaBuilder.like(root.get("adresse"), "%" + searchDTO.getAdresseDestination() + "%"));
        }

        if (searchDTO.getDateDeLivraison() != null ) {
            predicates.add(criteriaBuilder.like(root.get("dateLivraison"), searchDTO.getDateDeLivraison()));
        }

        if (searchDTO.getLivreurId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("livreur").get("id"), searchDTO.getLivreurId()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

