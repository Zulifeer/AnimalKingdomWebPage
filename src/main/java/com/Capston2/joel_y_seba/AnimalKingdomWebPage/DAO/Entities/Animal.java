package com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Animal")
public class Animal {

    @Id
    @Column(name = "animalID")
    private Long animlalID;

    @Column(name = "name", length = 25, nullable = false)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    @ManyToOne
    @JoinColumn(name = "enviromentID", nullable = false)
    private Enviroment enviromentId;

    @ManyToOne
    @JoinColumn(name = "typeID",nullable = false)
    private Type typeID;

    public Long getAnimlalID() {
        return animlalID;
    }

    public void setAnimlalID(Long animlalID) {
        this.animlalID = animlalID;
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

    public Enviroment getEnviromentId() {
        return enviromentId;
    }

    public void setEnviromentId(Enviroment enviromentId) {
        this.enviromentId = enviromentId;
    }

    public Type getTypeID() {
        return typeID;
    }

    public void setTypeID(Type typeID) {
        this.typeID = typeID;
    }

    @Override
    public String toString() {
        return "Animal [animlalID=" + animlalID + ", description=" + description + ", enviromentId=" + enviromentId
                + ", name=" + name + ", typeID=" + typeID + "]";
    }

    public Animal(Long animlalID, String name, String description, Enviroment enviromentId, Type typeID) {
        this.animlalID = animlalID;
        this.name = name;
        this.description = description;
        this.enviromentId = enviromentId;
        this.typeID = typeID;
    }

    public Animal() {
    }

    
}