package com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo;

import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long>{
    
}
