package com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo;

import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.RoleUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleUserRepo extends JpaRepository<RoleUser,Long> {
    
    @Query(nativeQuery = true, value = "select * from role_user where user_id = ?")
    RoleUser findBYUserId(Long id);
}
