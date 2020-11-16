package com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo;

import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TypeRepo extends JpaRepository<Type,Long> {
    @Query(nativeQuery = true, value = "select * from type where name = ?1")
    Type findByName(String name);

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM Type WHERE name = :name")
    boolean itExists(@Param("name") String name);

    @Query(nativeQuery = true, value = "select * from type where typeid = ?1")
    Type findByID(Long id);

    @Modifying()
    @Transactional()
    @Query(nativeQuery = true, value = "update type set description = ?1 where typeid = ?2")
    int updateDescriptionByTypeId(String description,Long id);
}
