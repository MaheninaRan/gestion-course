package com.example.testeeval.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Compositionetape {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "idetape",referencedColumnName = "id")
    private Etape etape;

    @ManyToOne
    @JoinColumn(name = "idcoureur",referencedColumnName = "id")
    private Coureur coureur;

    public Coureur getCoureur() {
        return coureur;
    }

    public void setCoureur(Coureur coureur) {
        this.coureur = coureur;
    }

    public Etape getEtape() {
        return etape;
    }

    public void setEtape(Etape etape) {
        this.etape = etape;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Compositionetape(int id) {
        this.id = id;
    }
    public Compositionetape() {

    }
}
