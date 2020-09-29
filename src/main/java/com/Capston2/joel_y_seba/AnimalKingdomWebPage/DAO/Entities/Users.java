package com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;
    @Column(name = "username", length = 25, nullable = false)
    private String username;
    @Column(name = "password",length = 255 ,nullable = false)
    private String pass;
    @Column(name = "enabled", nullable = false)
    private Byte enabled;
    @Column(name="email", nullable = false)
    private String email;

    public Users() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Byte isEnabled() {
        return enabled;
    }

    public void setEnabled(Byte enabled) {
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users(Long id, String username, String pass, Byte enabled, String email) {
        this.id = id;
        this.username = username;
        this.pass = pass;
        this.enabled = enabled;
        this.email = email;
    }

    public Byte getEnabled() {
        return enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Users [email=" + email + ", enabled=" + enabled + ", id=" + id + ", pass=" + pass + ", username="
                + username + "]";
    }

    
}
