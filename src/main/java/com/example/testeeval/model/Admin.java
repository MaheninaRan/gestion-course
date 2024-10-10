package com.example.testeeval.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "nom")
    private String nom;


    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }



    @Column(name = "prenom")
    private String prenom;



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        if(password==null) {
            throw new Exception("Champs vide");
        }

        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception{
        if(email==null || email ==" " || email.toCharArray().length > 30) {
            throw new Exception("Champs vide");
        }
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws Exception {
        if(nom.equals("") || nom.trim().isEmpty() || nom.toCharArray().length > 30) {
            throw new Exception("Champs vide");
        }
        this.nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
