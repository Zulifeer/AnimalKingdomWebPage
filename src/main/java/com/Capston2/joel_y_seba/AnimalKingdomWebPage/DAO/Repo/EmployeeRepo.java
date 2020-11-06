package com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo;

import java.util.List;

import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeeRepo extends JpaRepository<Employee, Long>{

    @Query(nativeQuery = true, value = "select * from employee")
    List<Employee> findAllEmployees();

    @Query(nativeQuery = true, value = "select * from employee where user_id = ?1")
    Employee findByUserId(Long id);

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM Employee WHERE id = :id")
    boolean itExists(@Param("id") Long id);

    @Modifying()
    @Transactional()
    @Query(nativeQuery = true, value = "update employee set enabled = 0 where user_id = ?")
    int disableEmployeeById(Long id);

    @Modifying()
    @Transactional()
    @Query(nativeQuery = true, value = "update employee set enabled = 1 where user_id = ?")
    int enableEmployeeById(Long id);

}
