package com.example.testeeval.repository;

import com.example.testeeval.model.Admin;
import com.example.testeeval.model.ClassementParEtape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClassementParEtapeRepository extends JpaRepository<ClassementParEtape, Integer> {
    @Query(value = """
      SELECT
        clas
      FROM ClassementParEtape clas
       WHERE clas.etape.id = :idetape
      """)
    List<ClassementParEtape> allResultatByEtape(@Param("idetape")int idetape);



}