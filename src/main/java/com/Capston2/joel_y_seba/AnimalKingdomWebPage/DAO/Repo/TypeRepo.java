package com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo;

import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TypeRepo extends JpaRepository<Type,Long> {
    @Query(nativeQuery = true, value = "select * from type where user_id = ?1")
    Type findByName(String name);
}
