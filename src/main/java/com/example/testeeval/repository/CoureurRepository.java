package com.example.testeeval.repository;

import com.example.testeeval.model.Coureur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoureurRepository extends JpaRepository<Coureur, Integer> {

    @Query("select u from Coureur u where  u.equipe.id =:equipe and u.id not in(select c.coureur.id from Compositionetape c where c.etape.id=:idetape)")
    List<Coureur> findcoureur(@Param("idetape") String etape,@Param("equipe") String equipe);

    @Query("select u from Coureur u where  u.equipe.id =:equipe and u.id in(select c.coureur.id from Compositionetape c where c.etape.id=:idetape)")
    List<Coureur> findcoureurEfaMikrosy(@Param("idetape") String etape,@Param("equipe") String equipe);


    @Query("""
        SELECT
            c
        FROM Coureur c
            WHERE c.id NOT IN(
                SELECT comp.coureur.id
                FROM Compositionetape comp where comp.etape.id = :idetape)
                AND c.equipe.id = :idequipe
    """)
    List<Coureur> allCoureurMbolatsymikrosy(@Param("idetape") int idetape,@Param("idequipe") int idequipe);


    @Query("select u from Coureur u where u.equipe.id = :id")
    List<Coureur> findequipe(@Param("id") int id);
}