package com.example.testeeval.model;

import jakarta.persistence.*;



@Entity
@Table(name = "classement_generale_equipe")
public class ClassementGeneraleEquipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idequipe",referencedColumnName = "id")
    private Equipe equipe;

    @Basic
    @Column(name = "pointequipe")
    private Double pointequipe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
    public Double getPointequipe() {
        return pointequipe;
    }

    public void setPointequipe(Double pointequipe) {
        this.pointequipe = pointequipe;
    }


}
