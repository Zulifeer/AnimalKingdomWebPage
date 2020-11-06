package com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo;

import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RolesRepo extends JpaRepository<Roles,Long> {

    @Query(value = "select a.role from Roles a, RoleUser ru, Users u where u.id = ru.user_id and ru.role_id = a.id and u.id = :id")
    String findByUserId(@Param("id") Long id);
    
}
