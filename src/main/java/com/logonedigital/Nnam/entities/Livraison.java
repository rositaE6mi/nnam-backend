package com.logonedigital.Nnam.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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


public class Livraison implements Serializable {

    @Serial

    private static final long serialVersionUID = 1L;

    private Integer idLivraison;

    private String adresseDestination;

    private String dateDeLivraison;

    private String etatDeLivraison;



    @ManyToOne
    private List<Livraison> livraisons = new ArrayList<>();
}
