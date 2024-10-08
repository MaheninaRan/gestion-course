package com.example.testeeval.repository;

import com.example.testeeval.model.VEquipeCategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VEquipeCategorieRepository extends JpaRepository<VEquipeCategorie, Integer> {

    @Query("select  v from VEquipeCategorie v where v.categorie.id =:id order by v.pointTotal desc")
    List<VEquipeCategorie> findcategorie(@Param("id") int categorie);

    @Query(value = """
    select
        v.id,
        v.idequipe,
        v.idcategorie,
        sum(v.point_total) as point_total
    from v_equipe_categorie v
    where v.idcategorie = ?1
    GROUP BY v.id, v.idequipe, v.idcategorie
    order by point_total desc
""", nativeQuery = true)
    List<VEquipeCategorie> findcategorieSum(int categorie);

    @Query("select  v from VEquipeCategorie v order by v.pointTotal desc ")
    List<VEquipeCategorie> findAllcategorie();

   @Query(value = """
    SELECT
    row_number() OVER () AS id,
    idequipe,
    null as idcategorie,
    SUM(point_total) as point_total
FROM v_equipe_categorie v
GROUP BY idequipe order by point_total desc 
""",nativeQuery = true)
  List<VEquipeCategorie> findAllcategoriesum();

}