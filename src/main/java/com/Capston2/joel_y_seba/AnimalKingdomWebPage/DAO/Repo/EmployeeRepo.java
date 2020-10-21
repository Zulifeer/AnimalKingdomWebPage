package com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo;

import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepo extends JpaRepository<Employee, Long>{

    @Query(nativeQuery = true, value = "select * from employee where user_id = ?1")
    Employee findByUserId(Long id);

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM Employee WHERE id = :id")
    boolean itExists(@Param("id") Long id);

}
