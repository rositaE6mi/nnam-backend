package com.logonedigital.Nnam.specification;

import com.logonedigital.Nnam.dto.LivreurReqDTO;
import com.logonedigital.Nnam.entities.Livreur;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class LivreurSpecification implements Specification<Livreur> {

    private final LivreurReqDTO searchDTO;

    public LivreurSpecification(LivreurReqDTO searchDTO) {
        this.searchDTO = searchDTO;
    }

    @Override
    public Predicate toPredicate(Root<Livreur> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (searchDTO.getNom() != null) {
            predicates.add(criteriaBuilder.like(root.get("nom"), "%" + searchDTO.getNom() + "%"));
        }

        if (searchDTO.getPrenom() != null) {
            predicates.add(criteriaBuilder.like(root.get("prenom"), "%" + searchDTO.getPrenom() + "%"));
        }

        if (searchDTO.getTelephone() != null) {
            predicates.add(criteriaBuilder.equal(root.get("telephone"), searchDTO.getTelephone()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
