package com.example.testeeval.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.sql.Timestamp;


@Entity
public class Resultatetape {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "idcompositionetape",referencedColumnName = "id")
    private Compositionetape compositionetape;

    @Column(name = "debut")
    private Timestamp debut;

    @Column(name = "fin")
    private Timestamp fin;





    public Resultatetape(int id) {
        this.id = id;
    }
    public Resultatetape() {

    }

    public Compositionetape getCompositionetape() {
        return compositionetape;
    }

    public void setCompositionetape(Compositionetape compositionetape) {
        this.compositionetape = compositionetape;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDebut() {
        return debut;
    }

    public void setDebut(Timestamp debut) {
        this.debut = debut;
    }

    public Timestamp getFin() {
        return fin;
    }

    public void setFin(Timestamp fin) {
        this.fin = fin;
    }

}
