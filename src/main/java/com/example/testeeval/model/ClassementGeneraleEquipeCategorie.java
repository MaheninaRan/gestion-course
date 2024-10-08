package com.example.testeeval.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "classement_generale_equipe_categorie")
public class ClassementGeneraleEquipeCategorie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idequipe",referencedColumnName = "id")
    private Equipe equipe;

    @ManyToOne
    @JoinColumn(name = "categorie_id",referencedColumnName = "id")
    private Categorie categorie;


    @Column(name = "totalpoints")
    private Double totalpoints;

    @Column(name = "classement")
    private Long classement;

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

    public Double getTotalpoints() {
        return totalpoints;
    }

    public void setTotalpoints(Double totalpoints) {
        this.totalpoints = totalpoints;
    }

    public Long getClassement() {
        return classement;
    }

    public void setClassement(Long classement) {
        this.classement = classement;
    }


}
