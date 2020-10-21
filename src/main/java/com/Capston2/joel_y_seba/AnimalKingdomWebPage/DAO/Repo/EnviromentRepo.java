package com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo;

import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Enviroment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnviromentRepo extends JpaRepository<Enviroment, Long>{

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM Enviroment WHERE name = :name")
    boolean itExists(@Param("name") String name);
}
