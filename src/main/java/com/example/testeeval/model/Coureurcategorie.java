package com.example.testeeval.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Coureurcategorie {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "idcategorie",referencedColumnName = "id")
    private Categorie categorie;

    @ManyToOne
    @JoinColumn(name = "idcoureur",referencedColumnName = "id")
    private Coureur coureur;

    public Coureur getCoureur() {
        return coureur;
    }

    public void setCoureur(Coureur coureur) {
        this.coureur = coureur;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
