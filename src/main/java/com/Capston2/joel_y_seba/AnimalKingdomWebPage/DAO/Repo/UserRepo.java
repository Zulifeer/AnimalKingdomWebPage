package com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo;



import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepo extends JpaRepository<Users,Long>{
    //Long findByUserId(String userid);
    
    Users findByUsername(String username);

    @Query(nativeQuery = true, value = "select user_id, email, username, name, enabled from users where user_id = ?")
    Users findByUserId(Long id);

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM Users WHERE username = :username")
    boolean itExists(@Param("username") String username);

    @Modifying()
    @Transactional()
    @Query(nativeQuery = true, value = "update users set enabled = 0 where user_id = ?")
    int disableUserById(Long id);

    @Modifying()
    @Transactional()
    @Query(nativeQuery = true, value = "update users set enabled = 1 where user_id = ?")
    int enableUserById(Long id);

    @Modifying()
    @Transactional()
    @Query(nativeQuery = true, value = "update users set email = ?2 where user_id = ?1")
    int updateUserEmailById(Long id, String email);

    @Modifying()
    @Transactional()
    @Query(nativeQuery = true, value = "update users set password = ?2 where user_id = ?1")
    int updateUserPassById(Long id, String pass);
}
