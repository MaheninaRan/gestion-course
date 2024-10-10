package com.example.testeeval.repository;

import com.example.testeeval.model.Admin;
import com.example.testeeval.model.Compositionetape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompositionetapeRepository extends JpaRepository<Compositionetape, Integer> {

}