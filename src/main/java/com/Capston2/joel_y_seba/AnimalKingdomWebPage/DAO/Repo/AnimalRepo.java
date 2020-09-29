package com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo;

import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Animal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepo extends JpaRepository<Animal, Long>{
    
}
