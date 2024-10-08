package com.example.testeeval.repository;

import com.example.testeeval.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
    @Query("select u from Admin u where u.email= :email AND u.password= :password")
    Optional<Admin> findMail(@Param("email") String email, @Param("password") String password);

    @Query(value = """
      select u from Admin u where 
      LOWER(u.nom) like LOWER(CONCAT('%',:recherche,'%')) or
      LOWER(u.prenom) like LOWER(CONCAT('%',:recherche,'%')) or
      LOWER(u.email) like LOWER(CONCAT('%',:recherche,'%')) 
      """)
    List<Admin> findrecherche(@Param("recherche") String recherche);
}
