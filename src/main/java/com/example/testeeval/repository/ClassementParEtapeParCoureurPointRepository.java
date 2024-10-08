package com.example.testeeval.repository;

import com.example.testeeval.model.ClassementParEtapeParCoureurPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClassementParEtapeParCoureurPointRepository extends JpaRepository<ClassementParEtapeParCoureurPoint, Integer> {

    @Query("select clas from ClassementParEtapeParCoureurPoint clas where clas.etape.id =:idetape")
    List<ClassementParEtapeParCoureurPoint> findbyIdEtape(@Param("idetape")int idetape);
}