package com.example.testeeval.model;

import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "classement_par_etape")
public class ClassementParEtape {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_resultat")
    private Resultatetape resultatetape;

    @ManyToOne
    @JoinColumn(name = "idcompositionetape",referencedColumnName = "id")
    private Compositionetape compositionetape;

    @ManyToOne
    @JoinColumn(name = "idetape",referencedColumnName = "id")
    private Etape etape;

    @ManyToOne
    @JoinColumn(name = "idcoureur",referencedColumnName = "id")
    private Coureur coureur;

    @ManyToOne
    @JoinColumn(name = "idequipe",referencedColumnName = "id")
    private Equipe equipe;


    @Column(name = "debut")
    private Timestamp debut;

    @Column(name = "fin")
    private Timestamp fin;

    @Column(name = "temps")
    private Time temps;

    @Column(name = "classement_par_etape")
    private Long classementParEtape;

    @Column(name = "point")
    private Double point;

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

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

    public Compositionetape getCompositionetape() {
        return compositionetape;
    }

    public void setCompositionetape(Compositionetape compositionetape) {
        this.compositionetape = compositionetape;
    }

    public Resultatetape getResultatetape() {
        return resultatetape;
    }

    public void setResultatetape(Resultatetape resultatetape) {
        this.resultatetape = resultatetape;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    public Time getTemps(){
        return temps;
    }

    public void setTemps(Time temps) {
        this.temps = temps;
    }

    public Long getClassementParEtape() {
        return classementParEtape;
    }

    public void setClassementParEtape(Long classementParEtape) {
        this.classementParEtape = classementParEtape;
    }

    public Double getPoint() {
        return point;
    }
    public void setPoint(Double point) {
        this.point = point;
    }
}
