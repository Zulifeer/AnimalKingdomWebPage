package com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo;


import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<Users,Long>{
    //Long findByUserId(String userid);
    
    Users findByUsername(String username);

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM Users WHERE username = :username")
    boolean itExists(@Param("username") String username);
}
