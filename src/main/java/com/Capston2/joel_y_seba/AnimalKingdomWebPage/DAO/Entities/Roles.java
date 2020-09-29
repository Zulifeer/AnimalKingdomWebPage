package com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="authorities")
public class Roles {
    
    @Id
    @Column(name = "authority_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    @Column(name = "authority", nullable = false)
    private String role;

    public Roles() {
    }

    public Roles(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Roles [id=" + id + ", role=" + role + "]";
    }

    
    
}
