package com.example.testeeval.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "v_equipe_categorie", schema = "public", catalog = "cours")
public class VEquipeCategorie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idequipe",referencedColumnName = "id")
    private Equipe equipe;

    @ManyToOne
    @JoinColumn(name = "idcategorie",referencedColumnName = "id")
    private Categorie categorie;

    @Basic
    @Column(name = "point_total")
    private Double pointTotal;

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }



    public Double getPointTotal() {
        return pointTotal;
    }

    public void setPointTotal(Double pointTotal) {
        this.pointTotal = pointTotal;
    }


}
