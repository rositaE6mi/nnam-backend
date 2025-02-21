package com.logonedigital.Nnam.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Administration implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;


    @NotEmpty(message = "Please fill this")
    @NotNull( message = "This field can't be null")

    private String nom;


    @OneToMany
    private List<Administration> administrations = new ArrayList<>();

}

