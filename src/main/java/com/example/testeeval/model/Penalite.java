package com.example.testeeval.model;

import jakarta.persistence.*;

import java.sql.Time;

@Entity
@Table(name = "penalite")
public class Penalite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    @Column(name = "penalite")
    private Time penalite;
    @ManyToOne
    @JoinColumn(name = "etape_id")
    private Etape etape;

    public Time getPenalite() {
        return penalite;
    }

    public void setPenalite(Time penalite) {
        this.penalite = penalite;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public void setEquipe(int equipe) {
        Equipe e = new Equipe();
        e.setId(equipe);
        this.equipe = e;
    }

    public Etape getEtape() {
        return etape;
    }

    public void setEtape(Etape etape) {
        this.etape = etape;
    }

    public void setEtape(int etape) {
        Etape e = new Etape(etape);
        this.etape = e;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}