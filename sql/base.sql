create database toky with encoding 'utf8';
\c toky;
CREATE TABLE admin  (
                        id SERIAL PRIMARY KEY,
                        nom varchar ,
                        prenom varchar,
                        email varchar unique ,
                        password varchar
);

create table equipe(
                       id serial primary key ,
                       nom varchar,
                       login varchar,
                       mdp varchar
);

create table etape (
                       id serial primary key ,
                       nom varchar,
                       debut timestamp,
                       longueur double precision,
                       nbcoureur int,
                       rang int
);

create table genre(
                      id serial primary key ,
                      nom varchar
);

create  table coureur(
                         id serial primary key ,
                         nom varchar,
                         numero int unique ,
                         idgenre int references genre,
                         dtn date,
                         idequipe int references equipe
);

create table categorie(
                          id serial primary key ,
                          nom varchar
);

create table coureurcategorie(
                                 id serial primary key ,
                                 idcategorie int references categorie,
                                 idcoureur int references coureur
);

create table compositionetape(
                                 id serial primary key ,
                                 idetape int references etape,
                                 idcoureur int references coureur
);

create table point(
                      id serial primary key ,
                      place int,
                      point double precision
);

create table resultatetape(
                              id serial primary key ,
                              idcompositionetape int references compositionetape,
                              debut timestamp,
                              fin timestamp

);



INSERT INTO admin (nom,prenom, email, password) VALUES
                                                    ('John ','Doe', 'john@example.com',  'password123'),
                                                    ('Jane','Smith', 'jane@example.com','abc456'),
                                                    ('Alice ','Johnson', 'alice@example.com','pass1234'),
                                                    ('Bob ','Brown', 'bob@example.com', 'securepwd');



CREATE TABLE penalite(
                         id serial primary key,
                         etape_id int references etape,
                         equipe_id int references equipe,
                         penalite time
);


-----------------------------------------------------x---------------------------------------
create table ipoint(
                       id serial primary key ,
                       classement int,
                       points int
);

CREATE TABLE ietape(
                       id serial primary key ,
                       etape varchar,
                       longueur varchar,
                       nbcoureur varchar,
                       rang varchar,
                       datedepart varchar,
                       heuredepart varchar
);

CREATE TABLE iresultat(
                          id SERIAL PRIMARY KEY ,
                          etaperang varchar,
                          numdossard varchar,
                          nom varchar,
                          genre varchar,
                          dateNaissance varchar,
                          equipe varchar,
                          arriver varchar
);





CREATE OR REPLACE VIEW temps_resultats AS
SELECT
    re.id AS id_resultat,
    re.idcompositionetape,
    cp.idetape,
    cp.idcoureur,
    cr.idequipe,
    re.debut,
    re.fin,
    EXTRACT(EPOCH FROM (re.fin - re.debut)) AS temps
FROM
    resultatetape re
        JOIN compositionetape cp ON re.idcompositionetape = cp.id
        JOIN coureur cr on cp.idcoureur = cr.id;

CREATE OR REPLACE VIEW resultats_par_etape AS
SELECT
    *,
    DENSE_RANK() OVER (PARTITION BY idetape ORDER BY temps) AS classement_par_etape
FROM
    temps_resultats;

SELECT
    *,
    RANK() OVER (PARTITION BY idetape ORDER BY temps) AS classement_par_etape
FROM
    temps_resultats;

------classement par etape
CREATE OR REPLACE VIEW classement_par_etape AS
SELECT row_number() OVER () AS id, rpe.*, COALESCE(p.point,0) as point FROM resultats_par_etape rpe
                                                                                Left JOIN point p ON rpe.classement_par_etape = p.place;

------classement par equipe
CREATE OR REPLACE VIEW classement_generale_equipe AS
SELECT  DENSE_RANK() OVER (order by sum(point)desc ) AS id,cpe.idequipe, SUM(cpe.point) as pointequipe FROM classement_par_etape cpe GROUP BY cpe.idequipe ORDER BY pointequipe desc;

------classement par coureur
CREATE OR REPLACE VIEW v_resultat_coureur AS
SELECT row_number() OVER () AS id,c.idetape,c.idcoureur,coalesce(t.temps,0) as temps
FROM compositionetape c
         Left JOIN temps_resultats t on c.id=t.idcompositionetape;
------classement par categorie
CREATE OR REPLACE VIEW v_temps_categorie AS
SELECT row_number() OVER (PARTITION BY idcategorie ORDER BY t.temps) AS rang,c.idcoureur,t.temps,c.idcategorie
FROM temps_resultats t
         INNER JOIN coureurcategorie c on t.idcoureur = c.idcoureur;

------point par rang
CREATE OR REPLACE VIEW v_tout_categorie AS
SELECT row_number() OVER () AS id,co.id as idcoureur,v.temps,eq.id as idequipe, v.idcategorie,p.point
FROM v_temps_categorie v
         INNER JOIN point p on p.id = v.rang
         INNER JOIN coureur co on co.id = v.idcoureur
         INNER JOIN equipe eq on eq.id = co.idequipe;

------point par categorie
CREATE OR REPLACE VIEW v_equipe_categorie AS
SELECT row_number() OVER () AS id, idequipe, idcategorie, SUM(point) AS point_total
FROM (
         SELECT co.idequipe, v.idcategorie, p.point
         FROM v_temps_categorie v
                  INNER JOIN point p ON p.id = v.rang
                  INNER JOIN coureur co ON co.id = v.idcoureur
                  INNER JOIN equipe eq ON eq.id = co.idequipe
     ) AS subquery
GROUP BY idequipe, idcategorie;

SELECT
    row_number() OVER () AS id,
        idequipe,
    null,
    SUM(point_total) as point_total
FROM v_equipe_categorie v
GROUP BY idequipe;



-------------------aaa

----Vue classement coureur par categorie

CREATE OR REPLACE VIEW classement_par_categorie AS

SELECT
    comp.idetape,
    c.idequipe,
    cc.idcategorie AS categorie_id,
    cat.nom AS categorie_nom,
    c.id AS coureur_id,
    c.nom AS coureur_nom,
    r.debut,
    r.fin,
    r.fin - r.debut AS duree,
    DENSE_RANK() OVER (PARTITION BY cc.idcategorie ORDER BY r.fin - r.debut) AS classement

FROM

    coureurcategorie cc
        JOIN coureur c ON cc.idcoureur = c.id
        JOIN categorie cat ON cc.idcategorie = cat.id
        JOIN compositionetape comp ON comp.idcoureur = c.id
        JOIN resultatetape r ON r.idcompositionetape = comp.id
ORDER BY
    cc.idcategorie, classement;





CREATE OR REPLACE VIEW classement_par_categorie AS

SELECT

    comp.idetape,

    c.idequipe,

    cc.idcategorie AS categorie_id,

    cat.nom AS categorie_nom,

    c.id AS coureur_id,

    c.nom AS coureur_nom,

    r.debut,

    r.fin,

    r.fin - r.debut AS duree,

    DENSE_RANK() OVER (PARTITION BY comp.idetape, cc.idcategorie ORDER BY r.fin - r.debut) AS classement

FROM

    coureurcategorie cc

        JOIN

    coureur c ON cc.idcoureur = c.id

        JOIN

    categorie cat ON cc.idcategorie = cat.id

        JOIN

    compositionetape comp ON comp.idcoureur = c.id

        JOIN

    resultatetape r ON r.idcompositionetape = comp.id

ORDER BY

    comp.idetape,cc.idcategorie, classement;









--- Vue resultat generale equipe par categorie

CREATE OR REPLACE VIEW resultat_equipe_categorie AS

SELECT

    row_number() OVER () AS id,
        cpc.idequipe,

    cpc.categorie_id,

    cpc.categorie_nom,

    SUM(COALESCE(p.point, 0)) as totalpoints

FROM

    classement_par_categorie cpc

        LEFT JOIN

    point p ON cpc.classement = p.place

GROUP BY

    cpc.idequipe, cpc.categorie_id, cpc.categorie_nom

ORDER BY

    cpc.categorie_id, totalpoints DESC;



--- Vue classement generale equipe par categorie



CREATE OR REPLACE VIEW classement_generale_equipe_categorie AS
SELECT
    row_number() OVER () AS id,
        rec.idequipe,
    rec.categorie_id,
    rec.totalpoints,
    DENSE_RANK() OVER (PARTITION BY rec.categorie_id ORDER BY rec.totalpoints DESC) AS classement
FROM
    resultat_equipe_categorie rec
ORDER BY
    rec.categorie_id, classement;
insert into categorie(nom) values
                               ('H'),
                               ('F'),
                               ('Junior');



create view alea as
SELECT
    row_number() OVER () AS id,
    sum(clas.point) as total,
    clas.idcoureur,
    clas.idequipe
FROM classement_par_etape clas
group by idcoureur,idequipe;











