package com.example.testeeval.repository;

import com.example.testeeval.model.Alea;
import com.example.testeeval.model.ClassementParEtape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AleaRepository extends JpaRepository<Alea, Integer> {

    @Query(value = """
      SELECT
        clas
      FROM Alea clas
       WHERE clas.equipe.id =:idequipe
      """)
    List<Alea> yaya(@Param("idequipe")int idequipe);
}