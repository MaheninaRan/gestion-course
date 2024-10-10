package com.example.testeeval.repository;

import com.example.testeeval.model.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EquipeRepository extends JpaRepository<Equipe, Integer> {
    @Query("select u from Equipe u where u.login= :email AND u.mdp= :password")
    Optional<Equipe> findLogin(@Param("email") String email, @Param("password") String password);

    @Query("select u from Equipe u where u.login= :email")
    Optional<Equipe> findEmail(@Param("email") String email);


    @Modifying
    @Query(value = """
    delete from equipe
    """, nativeQuery = true)
    void remAll();

    @Query("""
        SELECT
        e
        FROM
            Compositionetape  c
            INNER JOIN Equipe e ON e.id = c.coureur.equipe.id
        WHERE c.etape.id = :etape
    """)
    List<Equipe> findByComposition(@Param("etape") int idetape);
}