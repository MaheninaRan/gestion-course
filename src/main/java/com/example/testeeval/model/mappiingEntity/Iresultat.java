package com.example.testeeval.model.mappiingEntity;

import com.example.testeeval.Util.Util;
import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.text.ParseException;

@Entity
public class Iresultat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CsvBindByName(column = "etape_rang")
    private String etaperang;
    @CsvBindByName(column = "numero dossard")
    private String numdossard;
    @CsvBindByName(column = "nom")
    private String nom;
    @CsvBindByName(column = "genre")
    private String genre;
    @CsvBindByName(column = "date naissance")
    private String datenaissance;
    @CsvBindByName(column = "equipe")
    private String equipe;
    @CsvBindByName(column = "arrivée")
    private String arriver;

    public Iresultat(String etaperang, String numdossard, String nom, String genre, String datenaissance, String equipe, String arriver) {
        this.etaperang = etaperang;
        this.numdossard = numdossard;
        this.nom = nom;
        this.genre = genre;
        this.datenaissance = datenaissance;
        this.equipe = equipe;
        this.arriver = arriver;
    }

    public Iresultat() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtaperang() {
        return etaperang;
    }

    public void setEtaperang(String etaperang) {
        this.etaperang = etaperang;
    }

    public String getNumdossard() {
        return numdossard;
    }

    public void setNumdossard(String numdossard) {
        this.numdossard = numdossard;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDatenaissance() throws ParseException {
        return Util.formatDate(datenaissance);
    }
    public void setDatenaissance(String datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public String getArriver() throws ParseException {
        return Util.formatDateTime(arriver);
    }

    public void setArriver(String arriver) {
        this.arriver = arriver;
    }
}
