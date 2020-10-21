package com.Capston2.joel_y_seba.AnimalKingdomWebPage.Controllers;

import java.security.Principal;
import java.sql.Date;

import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Employee;
import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Enviroment;
import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.RoleUser;
import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Type;
import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Users;
import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo.AnimalRepo;
import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Repo.EmployeeRepo;
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

import jdk.jfr.Description;

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

    @Autowired
    EmployeeRepo employeeRepo;

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

    @GetMapping(value = "/sec/admin")
    public String AdminApp(Model model, Principal user, Authentication auth){
        
        model.addAttribute("user", user.getName());
        boolean isAdmin = auth.getAuthorities().stream().anyMatch((authority -> authority.getAuthority().equals("ADMIN")));
        model.addAttribute("isAdmin", isAdmin);
        
        return "Admin";
    }

    @GetMapping(value = "/sec/Admin/AddEmployee")
    public String AddEmployee(Model model, Principal user, Authentication auth){
        model.addAttribute("user", user.getName());
        boolean isAdmin = auth.getAuthorities().stream().anyMatch((authority -> authority.getAuthority().equals("ADMIN")));
        model.addAttribute("isAdmin", isAdmin);
        return "AddEmployee";
    }

    @PostMapping(value = "/sec/Admin/AddEmployee") 
    public String AddEmployee(Model model, Principal user, Authentication auth,
    @RequestParam("username") String username, 
    @RequestParam("email") String email, 
    @RequestParam("password") String password,
    @RequestParam("name") String name){
        model.addAttribute("user", user.getName());
        boolean isAdmin = auth.getAuthorities().stream().anyMatch((authority -> authority.getAuthority().equals("ADMIN")));
        model.addAttribute("isAdmin", isAdmin);

        RoleUser roleUser = new RoleUser();
        Users newUser = new Users();
        Employee employee = new Employee();

        // verify that the employee dosent exist.
        if(!(userRepo.itExists(username)  && employeeRepo.itExists(userRepo.findByUsername(username).getId()))){
            //check that the user dosent exist.
            if(!userRepo.itExists(username)){

                newUser.setEmail(email);
                newUser.setEnabled(Byte.valueOf("1"));
                newUser.setName(name);
                newUser.setPass(new BCryptPasswordEncoder().encode(password));
                newUser.setUsername(username);

                userRepo.save(newUser);

                employee.setEnabled(Byte.valueOf("1"));
                employee.setUser_id(userRepo.findByUsername(username));

                employeeRepo.save(employee);

                long role = 3;
                long millis = System.currentTimeMillis();

                roleUser.setDate(new Date(millis));
                roleUser.setRole_id(roleRepo.findById(role).get());
                roleUser.setUser_id(userRepo.findByUsername(username));
                model.addAttribute("epmloyeeAdded", true);

            }else{
                model.addAttribute("userExist", true);
            }

        } else {
            model.addAttribute("employeeExists", true);
        }

        return "AddEmployee";
    }
    
    @GetMapping(value = "/sec/Admin/AddAnimal")
    public String AddAnimal(Model model, Principal user, Authentication auth){
        model.addAttribute("user", user.getName());
        boolean isAdmin = auth.getAuthorities().stream().anyMatch((authority -> authority.getAuthority().equals("ADMIN")));
        model.addAttribute("isAdmin", isAdmin);
        return "AddAnimal";
    }

    @PostMapping(value = "/sec/Admin/AddAnimal")
    public String AddAnimal(Model model, Principal user, Authentication auth,
    @RequestParam("username") String username){
        model.addAttribute("user", user.getName());
        boolean isAdmin = auth.getAuthorities().stream().anyMatch((authority -> authority.getAuthority().equals("ADMIN")));
        model.addAttribute("isAdmin", isAdmin);
        
        
       
        return "AddAnimal";
    }

    @GetMapping(value = "/sec/Admin/AddEnviroment")
    public String AddEnviroment(Model model, Principal user, Authentication auth){
        model.addAttribute("user", user.getName());
        boolean isAdmin = auth.getAuthorities().stream().anyMatch((authority -> authority.getAuthority().equals("ADMIN")));
        model.addAttribute("isAdmin", isAdmin);
        return "AddEnviroment";
    }
    
    @PostMapping(value = "/sec/Admin/AddEnviroment")
    public String AddEnviroment(Model model, Principal user, Authentication auth,
    @RequestParam("name") String name,
    @RequestParam("type") String type,
    @RequestParam("description") String description){
        model.addAttribute("user", user.getName());
        boolean isAdmin = auth.getAuthorities().stream().anyMatch((authority -> authority.getAuthority().equals("ADMIN")));
        model.addAttribute("isAdmin", isAdmin);

        Enviroment newEnviroment  = new  Enviroment();

        if(!enviromentRepo.itExists(name)){
            newEnviroment.setName(name);
            newEnviroment.setType(type);
            newEnviroment.setDescription(description);

            enviromentRepo.save(newEnviroment);
            model.addAttribute("enviromentAdded", true);
        }else{
            
            model.addAttribute("enviromentExists", true);
        }
        return "AddEnviroment";
    }

    @GetMapping(value = "/sec/Admin/AddType")
    public String AddType(Model model, Principal user, Authentication auth){
        model.addAttribute("user", user.getName());
        boolean isAdmin = auth.getAuthorities().stream().anyMatch((authority -> authority.getAuthority().equals("ADMIN")));
        model.addAttribute("isAdmin", isAdmin);
        return "AddType";
    }

    @PostMapping(value = "/sec/Admin/AddType")
    public String AddType(Model model, Principal user, Authentication auth,
    @RequestParam("name") String name,
    @RequestParam("description") String description){
        model.addAttribute("user", user.getName());
        boolean isAdmin = auth.getAuthorities().stream().anyMatch((authority -> authority.getAuthority().equals("ADMIN")));
        model.addAttribute("isAdmin", isAdmin);

        Type newType = new Type();

        Type check = typeRepo.findByName(name);

        if(check.getName() == null){
            //add info
            newType.setName(name);
            newType.setDescription(description);

            typeRepo.save(newType);
            model.addAttribute("typeAdded", true);
        } else {
            model.addAttribute("typeExist", true);
        }

        return "AddType";
    }
    
}