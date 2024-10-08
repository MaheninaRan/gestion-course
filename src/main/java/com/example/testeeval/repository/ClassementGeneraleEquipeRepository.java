package com.example.testeeval.repository;

import com.example.testeeval.model.ClassementGeneraleEquipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClassementGeneraleEquipeRepository extends JpaRepository<ClassementGeneraleEquipe, Long> {

    @Query("select c from ClassementGeneraleEquipe c order by c.pointequipe desc")
    List<ClassementGeneraleEquipe> findTout();




}