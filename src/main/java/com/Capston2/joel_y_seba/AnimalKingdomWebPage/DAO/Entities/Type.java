package com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Type")
public class Type {

    @Id
    @Column(name = "typeID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeID;

    @Column(name = "name",  length = 25)
    private String name;

    @Column(name="description",  length = 750)
    private String description;

    public Type(Long typeID, String name, String description) {
        this.typeID = typeID;
        this.name = name;
        this.description = description;
    }

    public Type() {
    }

    public Long getTypeID() {
        return typeID;
    }

    public void setTypeID(Long typeID) {
        this.typeID = typeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Type [description=" + description + ", name=" + name + ", typeID=" + typeID + "]";
    }

    
}
