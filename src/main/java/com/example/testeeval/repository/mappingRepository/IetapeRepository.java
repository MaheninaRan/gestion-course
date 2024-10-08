package com.example.testeeval.repository.mappingRepository;

import com.example.testeeval.model.mappiingEntity.Ietape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface IetapeRepository extends JpaRepository<Ietape, Integer> {

    @Modifying
    @Query(value = """
        INSERT INTO etape(nom, debut, longueur, nbcoureur, rang)
        SELECT
            etape,
            CAST((datedepart || ' ' || heuredepart) AS TIMESTAMP) as debut,
            CAST(longueur AS DOUBLE PRECISION),
            CAST(nbcoureur AS INT),
            CAST(rang AS INT)
        FROM ietape
    """, nativeQuery = true)
    void toEtape();
}