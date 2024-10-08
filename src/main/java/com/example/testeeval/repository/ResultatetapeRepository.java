package com.example.testeeval.repository;

import com.example.testeeval.model.Resultatetape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ResultatetapeRepository extends JpaRepository<Resultatetape, Integer> {


}