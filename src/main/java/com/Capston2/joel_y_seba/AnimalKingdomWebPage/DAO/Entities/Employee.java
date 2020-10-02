package com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
    
    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="enabled")
    private Byte enabled;
    @OneToOne
    @JoinColumn(name = "authority_id",nullable = false)
    private Users user_id;

    public Employee() {
    }

    public Employee(Long id, Byte enabled, Users user_id) {
        this.id = id;
        this.enabled = enabled;
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getEnabled() {
        return enabled;
    }

    public void setEnabled(Byte enabled) {
        this.enabled = enabled;
    }

    public Users getUser_id() {
        return user_id;
    }

    public void setUser_id(Users user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Employee [enabled=" + enabled + ", id=" + id + ", user_id=" + user_id + "]";
    }

}
