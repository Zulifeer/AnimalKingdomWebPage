package com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo;


import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,Long>{
    //Long findByUserId(String userid);
    
    Users findByUsername(String username);
}
