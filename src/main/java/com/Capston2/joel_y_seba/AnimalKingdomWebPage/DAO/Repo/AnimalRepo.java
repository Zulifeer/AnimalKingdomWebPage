package com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo;

import javax.transaction.Transactional;

import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnimalRepo extends JpaRepository<Animal, Long>{
    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM Animal WHERE name = :name")
    boolean itExistsByName(@Param("name") String name);

    @Query(nativeQuery = true, value = "select * from animal where animalid = ?1")
    Animal findByAnimalId(Long id);

    @Modifying()
    @Transactional()
    @Query(nativeQuery = true, value = "update animal set image_path = ?1 where animalid = ?2")
    int updateIMGByAnimalId(String img_path,Long id);

    @Modifying()
    @Transactional()
    @Query(nativeQuery = true, value = "update animal set description = ?1 where animalid = ?2")
    int updateDescriptionByAnimalId(String description,Long id);
}
