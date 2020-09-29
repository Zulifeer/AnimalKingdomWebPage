package com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "role_user")
public class RoleUser {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "date_asigned")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private Users user_id;
    @ManyToOne
    @JoinColumn(name = "authority_id",nullable = false)
    private Roles role_id;

    public RoleUser() {
    }

    public RoleUser(Long id, Date date, Users user_id, Roles role_id) {
        Id = id;
        this.date = date;
        this.user_id = user_id;
        this.role_id = role_id;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Users getUser_id() {
        return user_id;
    }

    public void setUser_id(Users user_id) {
        this.user_id = user_id;
    }

    public Roles getRole_id() {
        return role_id;
    }

    public void setRole_id(Roles role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "RoleUser [Id=" + Id + ", date=" + date + ", role_id=" + role_id + ", user_id=" + user_id + "]";
    }

    

}
