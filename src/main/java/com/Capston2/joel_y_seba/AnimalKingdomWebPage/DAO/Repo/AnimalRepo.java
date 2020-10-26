package com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo;

import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnimalRepo extends JpaRepository<Animal, Long>{
    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM Animal WHERE name = :name")
    boolean itExistsByName(@Param("name") String name);
}
