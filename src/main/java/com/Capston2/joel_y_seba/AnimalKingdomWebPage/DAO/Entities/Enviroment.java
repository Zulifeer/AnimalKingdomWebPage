package com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Envioroment")
public class Enviroment {
    
    @Id
    @Column(name = "enviromentID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long envirmonetID;

    @Column(name = "name",  length = 25)
    private String name;

    @Column(name = "description", length = 750)
    private String description;

    @Column(name = "type",  length = 25)
    private String type;

    public Long getEnvirmonetID() {
        return envirmonetID;
    }

    public void setEnvirmonetID(Long envirmonetID) {
        this.envirmonetID = envirmonetID;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Enviroment() {
    }

	public Enviroment(Long envirmonetID, String name, String description, String type) {
		this.envirmonetID = envirmonetID;
		this.name = name;
		this.description = description;
		this.type = type;
    }

    @Override
    public String toString() {
        return "Enviroment [description=" + description + ", envirmonetID=" + envirmonetID + ", name=" + name
                + ", type=" + type + "]";
    }
    
    

    
}
