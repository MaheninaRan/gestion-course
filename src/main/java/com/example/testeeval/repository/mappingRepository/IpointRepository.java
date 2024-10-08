package com.example.testeeval.repository.mappingRepository;

import com.example.testeeval.model.mappiingEntity.Ipoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface IpointRepository extends JpaRepository<Ipoint, Integer> {

    @Modifying
    @Query(value = """
    insert into point (place,point)
    select  classement,points  from ipoint
    """, nativeQuery = true)
    void topoint();
}