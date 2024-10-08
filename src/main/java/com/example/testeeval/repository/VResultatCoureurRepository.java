package com.example.testeeval.repository;

import com.example.testeeval.model.VResultatCoureur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface VResultatCoureurRepository extends JpaRepository<VResultatCoureur, Integer> {
    @Query("select u from VResultatCoureur u where u.coureur.equipe.id= :id order by u.etape.rang ,u.temps asc")
    List<VResultatCoureur> findParequipe(@Param("id") int id);
}