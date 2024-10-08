package com.example.testeeval.repository;

import com.example.testeeval.model.Etape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EtapeRepository extends JpaRepository<Etape, Integer> {

    @Query("select u from Etape u where u.rang=:id")
    Optional<Etape> findByetape(@Param("id")int id);
}