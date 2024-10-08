package com.example.testeeval.model;
import jakarta.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "classement_par_etape_par_coureur_point")
public class ClassementParEtapeParCoureurPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idetape",referencedColumnName = "id")
    private Etape etape;

    @ManyToOne
    @JoinColumn(name = "idcoureur",referencedColumnName = "id")
    private Coureur coureur;

    @ManyToOne
    @JoinColumn(name = "idequipe",referencedColumnName = "id")
    private Equipe equipe;

    @Basic
    @Column(name = "debut")
    private Timestamp debut;
    @Basic
    @Column(name = "fin")
    private Timestamp fin;

    @Column(name = "chrono")
    private Time chrono;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setChrono(Time chrono) {
        this.chrono = chrono;
    }

    public Time getPenalite() {
        return penalite;
    }

    public void setPenalite(Time penalite) {
        this.penalite = penalite;
    }

    public Time getTempsFinal() {
        return tempsFinal;
    }

    public void setTempsFinal(Time tempsFinal) {
        this.tempsFinal = tempsFinal;
    }

    @Column(name = "penalite")
    private Time penalite;

    @Column(name = "temps_final")
    private Time tempsFinal;

    @Column(name = "rang")
    private Long rang;

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

    public Object getChrono() {
        return chrono;
    }



    public Long getRang() {
        return rang;
    }

    public void setRang(Long rang) {
        this.rang = rang;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

}


