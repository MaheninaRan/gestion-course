package com.example.testeeval.repository;

import com.example.testeeval.model.ClassementGeneraleEquipeCategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassementGeneraleEquipeCategorieRepository extends JpaRepository<ClassementGeneraleEquipeCategorie, Integer> {

    @Query(value = """
   select
       v.id,
       v.idequipe,
       v.categorie_id,
       v.classement,
       sum(v.totalpoints) as totalpoints
   from classement_generale_equipe_categorie v
   where v.categorie_id = ?1
   GROUP BY v.id, v.idequipe, v.categorie_id,v.classement
   order by totalpoints desc
""", nativeQuery = true)
    List<ClassementGeneraleEquipeCategorie> findcategorieSum(int categorie);
}