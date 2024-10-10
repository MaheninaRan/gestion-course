package com.example.testeeval.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Etape {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "longueur")
    private Double longueur;

    @Column(name = "nbcoureur")
    private Integer nbcoureur;

    public Timestamp getDebut() {
        return debut;
    }

    public void setDebut(Timestamp debut) {
        this.debut = debut;
    }

    @Column(name = "debut")
    private Timestamp debut;

    @Column(name = "rang")
    private Integer rang;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getLongueur() {
        return longueur;
    }

    public void setLongueur(Double longueur) {
        this.longueur = longueur;
    }

    public Integer getNbcoureur() {
        return nbcoureur;
    }

    public void setNbcoureur(Integer nbcoureur) {
        this.nbcoureur = nbcoureur;
    }

    public Integer getRang() {
        return rang;
    }

    public void setRang(Integer rang) {
        this.rang = rang;
    }

    public Etape(int id) {
        this.id = id;
    }

    public Etape() {
    }
}
