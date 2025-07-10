package com.logonedigital.Nnam.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class LivraisonReqDTO {
    @NotEmpty(message = "Please fill this")
    @NotNull( message = "This field can't be null")

    private String adresseDestination;

    private String dateDeLivraison;

    private String etatDeLivraison;

    private Integer livreurId;

    public LivraisonReqDTO() {
    }

    public LivraisonReqDTO(String adresseDestination, String dateDeLivraison, String etatDeLivraison, Integer livreurId) {
        this.adresseDestination = adresseDestination;
        this.dateDeLivraison = dateDeLivraison;
        this.etatDeLivraison = etatDeLivraison;
        this.livreurId = livreurId;
    }

    public @NotEmpty(message = "Please fill this") @NotNull(message = "This field can't be null") String getAdresseDestination() {
        return adresseDestination;
    }

    public void setAdresseDestination(@NotEmpty(message = "Please fill this") @NotNull(message = "This field can't be null") String adresseDestination) {
        this.adresseDestination = adresseDestination;
    }

    public String getDateDeLivraison() {
        return dateDeLivraison;
    }

    public void setDateDeLivraison(String dateDeLivraison) {
        this.dateDeLivraison = dateDeLivraison;
    }

    public String getEtatDeLivraison() {
        return etatDeLivraison;
    }

    public void setEtatDeLivraison(String etatDeLivraison) {
        this.etatDeLivraison = etatDeLivraison;
    }

    public Integer getLivreurId() {
        return livreurId;
    }

    public void setLivreurId(Integer livreurId) {
        this.livreurId = livreurId;
    }
}
