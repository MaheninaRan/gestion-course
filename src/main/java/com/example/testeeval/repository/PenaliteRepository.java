package com.example.testeeval.repository;

import com.example.testeeval.model.Penalite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PenaliteRepository extends JpaRepository<Penalite, Integer> {

    @Query(value = """
    SELECT
        row_number() over () as id,
        etape_id,
        equipe_id,
        SUM(penalite) AS penalite
    FROM
        penalite
    GROUP BY
        etape_id,
        equipe_id
    """, nativeQuery = true)
    List<Penalite> findAllVaovao();

    @Modifying
    @Query(value = "DELETE FROM penalite WHERE equipe_id=?1 AND etape_id=?2", nativeQuery = true)
    void deletePenaliteByidEquipeAndIdEtpae(int equipe_id, int etape_id);
}