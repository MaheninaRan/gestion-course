package com.example.testeeval.model.mappiingEntity;

import jakarta.persistence.*;

@Entity
public class Ipoint {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "classement")
    private Integer classement;

    @Column(name = "points")
    private Integer points;

    public Ipoint( Integer classement, Integer points) {
        this.setClassement(classement);
        this.setPoints(points);
        System.out.println("Added Point");
    }

    public Ipoint() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getClassement() {
        return classement;
    }

    public void setClassement(Integer classement) {
        this.classement = classement;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }


}
