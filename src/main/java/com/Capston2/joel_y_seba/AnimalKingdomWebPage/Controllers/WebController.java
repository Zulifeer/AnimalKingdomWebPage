package com.Capston2.joel_y_seba.AnimalKingdomWebPage.Controllers;

import java.security.Principal;
import java.sql.Date;

import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.RoleUser;
import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Users;
import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo.AnimalRepo;
import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo.EnviromentRepo;
import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo.RoleUserRepo;
import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo.RolesRepo;
import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo.TypeRepo;
import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class WebController {
    Logger logger = LoggerFactory.getLogger(WebController.class);

    @Autowired
    AnimalRepo animalRepo;

    @Autowired
    EnviromentRepo enviromentRepo;

    @Autowired
    RolesRepo roleRepo;

    @Autowired
    RoleUserRepo roleUserRepo;

    @Autowired
    TypeRepo typeRepo;

    @Autowired
    UserRepo userRepo;

    @GetMapping(value = {"/","/index"})
    public String Home(Model model, Principal user, Authentication auth){
        if(user != null && auth.isAuthenticated()){
            if(userRepo.findByUsername(user.getName()).getEnabled() != 1){
                model.addAttribute("user", user.getName());
                return "AccountDesaible";
            }
            model.addAttribute("user", user.getName());
            boolean isAdmin = auth.getAuthorities().stream().anyMatch((authority -> authority.getAuthority().equals("ADMIN")));
            model.addAttribute("isAdmin", isAdmin);

            boolean isUser = auth.getAuthorities().stream().anyMatch((authority -> authority.getAuthority().equals("USER")));
            model.addAttribute("isUser", isUser);
            

        }

        return "Home";
    }

    @GetMapping(value = "/login")
    public String Login(Principal user){
        
        if(user != null && ((Authentication) user ).isAuthenticated()){
            //logger.info("USER NAME: " + user.getName());
            return "redirect:/";
        }

        return "Login";
    }

    @GetMapping(value = "/animals")
    public String Animals(){
 
        return "Animals";
    }

    @GetMapping(value = "/signup")
    public String SingUp(){

        return "SignUp";
    }

    @PostMapping(value="/logout")
    public String logout(){
        return "redirect:/login";
    }

    @PostMapping(value = "/signup")
    public String SingUp(@RequestParam("username") String username, 
    @RequestParam("email") String email, 
    @RequestParam("password") String password,
    @RequestParam("name") String name){
        
        RoleUser roleUser = new RoleUser();
        Users newUser = new Users();
        
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setEnabled(Byte.valueOf("1"));
        newUser.setPass(new BCryptPasswordEncoder().encode(password));
        newUser.setUsername(username);
        userRepo.save(newUser);

        long role = 2;
        long millis = System.currentTimeMillis();

        roleUser.setDate(new Date(millis));
        roleUser.setRole_id(roleRepo.findById(role).get());
        roleUser.setUser_id(userRepo.findByUsername(username));

        roleUserRepo.save(roleUser);
        
        return "redirect:/Login";
    }

    @GetMapping(value = "/sec/Admin")
    public String AdminApp(){

        
        return "Admin";
    }

    
}