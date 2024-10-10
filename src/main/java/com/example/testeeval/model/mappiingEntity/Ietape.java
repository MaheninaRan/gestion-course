package com.example.testeeval.model.mappiingEntity;

import com.example.testeeval.Util.Util;
import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.text.ParseException;

@Entity
public class Ietape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CsvBindByName(column = "etape")
    private String etape;
    @CsvBindByName(column = "longueur")
    private String longueur;
    @CsvBindByName(column = "nb coureur")
    private String nbcoureur;
    @CsvBindByName(column = "rang")
    private String rang;
    @CsvBindByName(column = "date départ")
    private String datedepart;
    @CsvBindByName(column = "heure départ")
    private String heuredepart;

    public Ietape(String etape, String longueur, String nbcoureur, String rang, String datedepart, String heuredepart) {
        this.etape = etape;
        this.longueur = longueur;
        this.nbcoureur = nbcoureur;
        this.rang = rang;
        this.datedepart = datedepart;
        this.heuredepart = heuredepart;
    }

    public Ietape() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtape() {
        return etape;
    }

    public void setEtape(String etape) {
        this.etape = etape;
    }

    public String getLongueur() {
        return longueur.replace(",", ".");
    }

    public void setLongueur(String longueur) {
        this.longueur = longueur;
    }

    public String getNbcoureur() {
        return nbcoureur;
    }

    public void setNbcoureur(String nbcoureur) {
        this.nbcoureur = nbcoureur;
    }

    public String getRang() {
        return rang;
    }

    public void setRang(String rang) {
        this.rang = rang;
    }

    public String getDatedepart() throws ParseException {
        return Util.formatDate(this.datedepart);
    }

    public void setDatedepart(String datedepart) {
        this.datedepart = datedepart;
    }

    public String getHeuredepart() {
        return heuredepart;
    }

    public void setHeuredepart(String heuredepart) {
        this.heuredepart = heuredepart;
    }
}
