package com.example.testeeval.repository;

import com.example.testeeval.model.Coureurcategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoureurcategorieRepository extends JpaRepository<Coureurcategorie, Integer> {
}