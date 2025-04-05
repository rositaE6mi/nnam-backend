package com.logonedigital.Nnam.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Livreur implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idLivreur;


    private String nom;

    private String prenom;

    private String telephone;

    private Date createdAt;
    private Date updatedAt;


@OneToMany(mappedBy = "livreur", cascade = CascadeType.ALL)
private List<Livraison> livraisons;

}
