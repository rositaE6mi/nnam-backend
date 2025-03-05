package com.logonedigital.Nnam.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRole ;
    @NotEmpty (message = "Le nom du role est obligatoire")
    @NotNull (message = "This field can't be null")
    private String nomRole ;

    @OneToMany(mappedBy = "role")
    @JsonIgnoreProperties ("role")// Empeche les boucles infinies
    private List<Utilisateur> utilisateurs;

    @OneToMany(mappedBy = "role")
    @JsonIgnoreProperties ("role")// Empeche les boucles infinies
    private List<Profil> profils;
}
