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


public class Livraison implements Serializable {

    @Serial

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idLivraison;

    @NotEmpty(message = "Please fill this")
    @NotNull( message = "This field can't be null")

    private String adresseDestination;

    private String dateDeLivraison;

    private String etatDeLivraison;

    private Date createdAt;
    private Date updatedAt;



    @ManyToOne
    private List<Livraison> livraisons = new ArrayList<>();
}
