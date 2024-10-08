package com.example.testeeval.repository.mappingRepository;

import com.example.testeeval.model.mappiingEntity.Iresultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface IresultatRepository extends JpaRepository<Iresultat, Integer> {

    @Modifying
    @Query(value = """
        INSERT INTO genre(nom)
        SELECT DISTINCT genre FROM iresultat
    """, nativeQuery = true)
    void toGenre();

    @Modifying
    @Query(value = """
        INSERT INTO equipe(nom, login, mdp)
            SELECT DISTINCT equipe, equipe, equipe
        FROM iresultat
    """, nativeQuery = true)
    void toEquipe();

    @Modifying
    @Query(value = """
        INSERT INTO coureur(nom, numero, idgenre, dtn, idequipe)
        SELECT DISTINCT ON (i.nom)
            i.nom,
            CAST(i.numdossard AS INT),
            g.id,
            CAST(i.datenaissance AS DATE),
            e.id
        FROM iresultat i
            INNER JOIN genre g on g.nom = i.genre
            INNER JOIN equipe e on i.equipe = e.nom
            """, nativeQuery = true)
    void toCoureur();

    @Modifying
    @Query(value = """
    INSERT INTO compositionetape(idetape, idcoureur)
    SELECT
        e.id as idetape,
        c.id as idcoureur
    FROM iresultat i
    INNER JOIN etape e ON e.rang = CAST(i.etaperang AS INT)
    INNER JOIN coureur c ON c.nom = i.nom
    """, nativeQuery = true)
    void toCompositionEtape();


    @Modifying
    @Query(value = """
        INSERT INTO resultatetape(idcompositionetape, debut, fin)
        SELECT
            ce.id,
            e.debut,
            CAST(i.arriver AS TIMESTAMP)
        FROM iresultat i
        INNER JOIN etape e ON e.rang = CAST(i.etaperang AS INT)
        INNER JOIN coureur c ON c.nom = i.nom
        INNER JOIN compositionetape ce ON ce.idetape = e.id AND ce.idcoureur = c.id;
        
    """, nativeQuery = true)
    void toResultat();
}