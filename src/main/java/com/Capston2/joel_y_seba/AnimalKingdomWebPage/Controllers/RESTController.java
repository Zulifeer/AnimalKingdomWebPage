package com.Capston2.joel_y_seba.AnimalKingdomWebPage.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Animal;
import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Enviroment;
import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Type;
import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo.AnimalRepo;
import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo.EnviromentRepo;
import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo.TypeRepo;
import com.Capston2.joel_y_seba.AnimalKingdomWebPage.Controllers.MessageTemplates.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController{
    
    @Autowired
    private AnimalRepo animalRepo;
    @Autowired
    private TypeRepo typeRepo;
    @Autowired
    private EnviromentRepo enviromentRepo;

    @GetMapping(value = "/unity/api/allAnimals", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AnimalTemplate> allAnimals(){
        List<Animal> animals = animalRepo.findAll();
        List<AnimalTemplate> msg = new ArrayList<>();
        for(int i =0; i<animals.size(); i++){
            msg.add(new AnimalTemplate());
            msg.get(i).setName(animals.get(i).getName());
            msg.get(i).setDescription(animals.get(i).getDescription());
            msg.get(i).setEnviroment(animals.get(i).getEnviromentId().getName());
            msg.get(i).setType(animals.get(i).getTypeID().getName());
            msg.get(i).setId(animals.get(i).getAnimlalID().toString());
        }

        return msg;
    }

    @GetMapping(value = "/unity/api/allTypes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TypeTemplate> allTypes(){
        List<TypeTemplate> msg  = new ArrayList<>();
        List<Type> types = typeRepo.findAll();
        for (int i = 0; i < types.size(); i++) {
            msg.add(new TypeTemplate());
            msg.get(i).setId(types.get(i).getTypeID().toString());
            msg.get(i).setDescription(types.get(i).getDescription());
            msg.get(i).setName(types.get(i).getDescription());
        }

        return msg;
    }
    
    @GetMapping(value = "/unity/api/allEnviroments", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EnviromentTemplate> allEnviroments(){
        List<EnviromentTemplate> msg  = new ArrayList<>();
        List<Enviroment> enviroments = enviromentRepo.findAll();
        for (int i = 0; i < enviroments.size(); i++) {
            msg.add(new EnviromentTemplate());
            msg.get(i).setDescription(enviroments.get(i).getDescription());
            msg.get(i).setId(enviroments.get(i).getEnvirmonetID().toString());
            msg.get(i).setName(enviroments.get(i).getName());
            msg.get(i).setType(enviroments.get(i).getType());
        }   

        return msg;
    }
    
}