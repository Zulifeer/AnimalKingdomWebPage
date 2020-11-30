package com.Capston2.joel_y_seba.AnimalKingdomWebPage.Controllers.MessageTemplates;

public class EnviromentTemplate {
    
    private String id;
    private String name;
    private String description;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public EnviromentTemplate(String id, String name, String description, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public EnviromentTemplate() {
    }

    @Override
    public String toString() {
        return "EnviromentTemplate [description=" + description + ", id=" + id + ", name=" + name + ", type=" + type
                + "]";
    }


}
