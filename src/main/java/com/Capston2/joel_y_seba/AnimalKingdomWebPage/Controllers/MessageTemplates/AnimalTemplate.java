package com.Capston2.joel_y_seba.AnimalKingdomWebPage.Controllers.MessageTemplates;


public class AnimalTemplate {
    
    private String id;
    private String name;
    private String description;
    private String enviroment;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEnviroment() {
        return enviroment;
    }

    public void setEnviroment(String enviroment) {
        this.enviroment = enviroment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AnimalTemplate(String id, String name, String description, 
            String enviroment, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.enviroment = enviroment;
        this.type = type;
    }

    public AnimalTemplate() {
    }

    @Override
    public String toString() {
        return "AnimalTemplate [description=" + description + ", enviroment=" + enviroment + ", id=" + id
                + ", name=" + name + ", type=" + type + "]";
    }

    
}
