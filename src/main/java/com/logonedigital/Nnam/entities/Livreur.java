package com.logonedigital.Nnam.entities;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Livreur implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer idLivreur;

    private String nom;

    private String prenom;

    private String telephone;


    private List<Livreur> livreurs = new ArrayList<>();

}
