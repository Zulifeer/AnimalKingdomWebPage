package com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo;

import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Enviroment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface EnviromentRepo extends JpaRepository<Enviroment, Long>{

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM Enviroment WHERE name = :name")
    boolean itExistsByName(@Param("name") String name);

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM Enviroment WHERE enviromentID = :id")
    boolean itExistsById(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select * from envioroment where enviromentid = ?1")
    Enviroment findByID(Long id);

    @Modifying()
    @Transactional()
    @Query(nativeQuery = true, value = "update envioroment set description = ?1 where enviromentid = ?2")
    int updateDescriptionByEnviromentId(String description,Long id);
}
