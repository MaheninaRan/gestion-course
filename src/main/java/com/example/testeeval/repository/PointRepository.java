package com.example.testeeval.repository;

import com.example.testeeval.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Integer> {

}