package com.Capston2.joel_y_seba.AnimalKingdomWebPage.Controllers;

import java.io.*;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import javax.servlet.http.HttpServletRequest;

import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Animal;
import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Employee;
import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Enviroment;
import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.RoleUser;
import com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Roles;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import org.apache.commons.io.IOUtils;
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

    @GetMapping(value = "/Home_1/{selection}")
    public String Home_1(Model model, Principal user, Authentication auth, @PathVariable("selection") String selection){
        if (user != null && auth.isAuthenticated()) {
            if (userRepo.findByUsername(user.getName()).getEnabled() != 1) {
                model.addAttribute("user", user.getName());
                return "AccountDesaible";
            }
            model.addAttribute("user", user.getName());
            boolean isAdmin = auth.getAuthorities().stream()
                    .anyMatch((authority -> authority.getAuthority().equals("ADMIN")));
            model.addAttribute("isAdmin", isAdmin);

            boolean isEmployee = auth.getAuthorities().stream()
                    .anyMatch((authority -> authority.getAuthority().equals("EMPLOYEE")));
            model.addAttribute("isEmployee", isEmployee);

        }
        List<Type> allTypes = typeRepo.findAll();
        
        model.addAttribute("allTypes", allTypes);
        if(selection.equals("allanimals")){
            List<Animal> allAnimals = animalRepo.findAll();
            //List<Enviroment> allEnviroments = enviromentRepo.findAll();
            model.addAttribute("allAnimals", allAnimals);
            //model.addAttribute("allEnviroments", allEnviroments);
        }else if(selection != null){
            List<Animal> selected_animals = animalRepo.findByTypeId(typeRepo.findByName(selection).getTypeID());
            model.addAttribute("selected_animals", selected_animals);
            model.addAttribute("animal_selected", true);
        }
        

        return"Home_1";
    }

    @GetMapping(value = { "/", "/index" })
    public String Home(Model model, Principal user, Authentication auth) {
        if (user != null && auth.isAuthenticated()) {
            if (userRepo.findByUsername(user.getName()).getEnabled() != 1) {
                model.addAttribute("user", user.getName());
                return "AccountDesaible";
            }
            model.addAttribute("user", user.getName());
            boolean isAdmin = auth.getAuthorities().stream()
                    .anyMatch((authority -> authority.getAuthority().equals("ADMIN")));
            model.addAttribute("isAdmin", isAdmin);

            boolean isEmployee = auth.getAuthorities().stream()
                    .anyMatch((authority -> authority.getAuthority().equals("EMPLOYEE")));
            model.addAttribute("isEmployee", isEmployee);

        }
        List<Animal> allAnimals = animalRepo.findAll();
        model.addAttribute("allAnimals", allAnimals);
            

        return "Home";
    }

    @GetMapping(value = "/login")
    public String Login(Principal user) {

        if (user != null && ((Authentication) user).isAuthenticated()) {
            // logger.info("USER NAME: " + user.getName());
            return "redirect:/Home_1";
        }

        return "Login";
    }

    @GetMapping(value = "/animals/{animal}")
    public String Animals(Model model, Principal user, Authentication auth,
    @PathVariable("animal") String selected_animal) {
        if(user != null){
            model.addAttribute("user", user.getName());
            boolean isAdmin = auth.getAuthorities().stream()
                    .anyMatch((authority -> authority.getAuthority().equals("ADMIN")));
            model.addAttribute("isAdmin", isAdmin);
        }
        
        Animal animal = animalRepo.findByName(selected_animal);
        model.addAttribute("animal", animal);

        return "Animals";
    }

    @GetMapping(value = "/signup")
    public String SingUp() {

        return "SignUp";
    }

    @PostMapping(value = "/logout")
    public String logout() {
        return "redirect:/login";
    }

    @PostMapping(value = "/signup")
    public String SingUp(@RequestParam("username") String username, @RequestParam("email") String email,
            @RequestParam("password") String password, @RequestParam("name") String name) {

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

    @GetMapping(value = "/sec/admin/{id}")
    public String AdminApp(Model model, Principal user, Authentication auth, @PathVariable("id") String view
    ) {

        model.addAttribute("user", user.getName());
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch((authority -> authority.getAuthority().equals("ADMIN")));
        model.addAttribute("isAdmin", isAdmin);

        switch (view) {
            case "users":
                List<Users> users = userRepo.findAll();
                List<String> roles = new ArrayList<String>();
                List<String> dateAdded = new ArrayList<String>();
                List<Employee> empList = employeeRepo.findAllEmployees();
                
                for(int i =0; i<users.size(); i++){
                    roles.add(roleRepo.findByUserId(users.get(i).getId()));
                    dateAdded.add(roleUserRepo.findAll().get(i).getDate().toString());
                }
                
                model.addAttribute("employees", empList);
                model.addAttribute("dateAdded", dateAdded);
                model.addAttribute("roles", roles);
                model.addAttribute("users", users);
                model.addAttribute("viewUsers", true);
                break;
            case "animals":
                List<Animal> animals = animalRepo.findAll();
                model.addAttribute("animals", animals);
                model.addAttribute("viewAnimals", true);
                break;
            case "enviroments":
                List<Enviroment> enviroments = enviromentRepo.findAll();
                model.addAttribute("enviroments", enviroments);
                model.addAttribute("viewEnviroments", true);
                break;
            case "types":
                List<Type> types = typeRepo.findAll();
                model.addAttribute("types", types);
                model.addAttribute("viewTypes", true);
                break;
        }
        return "Admin";
    }

    @PostMapping(value = "sec/Admin/Modify/User/{selected_id}")
    public String modifyUser(Model model, Principal user, Authentication auth,@PathVariable("selected_id") String selected_id, 
    @RequestParam("email") String email,
    @RequestParam("password") String pass,
    @RequestParam("selected_enabled")Long enabled){
        model.addAttribute("user", user.getName());
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch((authority -> authority.getAuthority().equals("ADMIN")));
        model.addAttribute("isAdmin", isAdmin);

        logger.info("SELECTED ID: " + selected_id);
        logger.info("SELECTED ENABLED: " + enabled);
        logger.info(" SELECTED USER ROLE: " + roleRepo.findByUserId(Long.valueOf(selected_id)));
        if(!email.equals("")){
            userRepo.updateUserEmailById(Long.valueOf(selected_id), email);
        }
        if(!pass.equals("")){
            userRepo.updateUserPassById(Long.valueOf(selected_id), new BCryptPasswordEncoder().encode(pass));
        }
        if(roleRepo.findByUserId(Long.valueOf(selected_id)).equals("EMPLOYEE")){
            logger.info("ENTERING EMPLOYEE IF");
            if(enabled.equals(Long.valueOf("1"))){
                logger.info("ENABALING EMPLOYEE");
                employeeRepo.enableEmployeeById(Long.valueOf(selected_id));
            }else{
                logger.info("DISABALING EMPLOYEE");
                employeeRepo.disableEmployeeById(Long.valueOf(selected_id));
            }
        }
        if(roleRepo.findByUserId(Long.valueOf(selected_id)).equals("USER")){
            logger.info("ENTERING USER IF");
            if(enabled.equals(Long.valueOf("1"))){
                logger.info("ENABALING USER");
                userRepo.enableUserById(Long.valueOf(selected_id));
            }else{
                logger.info("DISABALING USER");
                userRepo.disableUserById(Long.valueOf(selected_id));
            }
        }
        return"Admin";
    }

    @PostMapping(value = "sec/Admin/Modify/Animal/{selected_id}")
    public String modifyAnimal(Model model, Principal user, Authentication auth,@PathVariable("selected_id") String selected_id,HttpServletRequest request,
    @RequestParam("description") String description,
    @RequestParam("img") MultipartFile img) throws IllegalStateException, IOException {
        model.addAttribute("user", user.getName());
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch((authority -> authority.getAuthority().equals("ADMIN")));
        model.addAttribute("isAdmin", isAdmin);

        UUID uuid = UUID.randomUUID();

        if(!img.isEmpty()){
            String actualPath = "./AnimalIMG/"+ user.getName() + "/" + uuid.toString() + img.getOriginalFilename();
            String path = request.getSession().getServletContext().getRealPath(actualPath);
            File dirPath = new File(path);
            
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }

            animalRepo.updateIMGByAnimalId(actualPath ,Long.valueOf(selected_id));
            img.transferTo(dirPath);
        }

        if(!description.equals("")){
            animalRepo.updateDescriptionByAnimalId(description, Long.valueOf(selected_id));
        }

        
        return"Admin";
    }

    @PostMapping(value = "sec/Admin/Modify/Enviroment/{selected_id}")
    public String modifyEnviroment(Model model, Principal user, Authentication auth,@PathVariable("selected_id") String selected_id,
    @RequestParam("description") String description) {
        model.addAttribute("user", user.getName());
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch((authority -> authority.getAuthority().equals("ADMIN")));
        model.addAttribute("isAdmin", isAdmin);

        if(!description.equals("")){
            enviromentRepo.updateDescriptionByEnviromentId(description, Long.valueOf(selected_id));
        }
        return"Admin";
    }

    @PostMapping(value = "sec/Admin/Modify/Type/{selected_id}")
    public String modifyType(Model model, Principal user, Authentication auth,@PathVariable("selected_id") String selected_id,
    @RequestParam("description") String description) {
        model.addAttribute("user", user.getName());
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch((authority -> authority.getAuthority().equals("ADMIN")));
        model.addAttribute("isAdmin", isAdmin);
        if(!description.equals("")){
            typeRepo.updateDescriptionByTypeId(description, Long.valueOf(selected_id));
        }
        
        return"Admin";
    }

    @GetMapping(value = "sec/Admin/Modify/{id}/{selected_id}")
    public String modifyAnithing(Model model, Principal user, Authentication auth, @PathVariable("id") String view,
    @PathVariable("selected_id") String selected_id){
        model.addAttribute("user", user.getName());
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch((authority -> authority.getAuthority().equals("ADMIN")));
        model.addAttribute("isAdmin", isAdmin);
        
        switch (view) {
            case "user":
                List<Users> users = userRepo.findAll(); 
                List<Employee> empList = employeeRepo.findAllEmployees();
                List<String> roles = new ArrayList<String>();
                List<String> dateAdded = new ArrayList<String>();
               


                for(int i =0; i<users.size(); i++){
                    roles.add(roleRepo.findByUserId(users.get(i).getId()));
                    dateAdded.add(roleUserRepo.findAll().get(i).getDate().toString());
                }
                model.addAttribute("employees", empList);
                model.addAttribute("dateAdded", dateAdded);
                model.addAttribute("roles", roles);
                model.addAttribute("users", users);
                
                model.addAttribute("viewUsers", true);
                
                Users user_selected = userRepo.findByUserId(Long.valueOf(selected_id));
                Employee employee_selected = employeeRepo.findByUserId(Long.valueOf(selected_id));
                String user_role = roleRepo.findByUserId(Long.valueOf(selected_id));
                String date_added = roleUserRepo.findBYUserId(Long.valueOf(selected_id)).getDate().toString();

                

                model.addAttribute("employee_selected", employee_selected);
                model.addAttribute("selected_user", user_selected);
                model.addAttribute("selected_user_role", user_role);
                model.addAttribute("selected_user_date_added", date_added);
                model.addAttribute("user_selected", true);
                break;
            case "animals":
                List<Animal> animals = animalRepo.findAll();
                model.addAttribute("animals", animals);
                model.addAttribute("viewAnimals", true);

                Animal animal_selected = animalRepo.findByAnimalId(Long.valueOf(selected_id));
                model.addAttribute("selected_animal", animal_selected);
                model.addAttribute("animal_selected", true);
                break;
            case "enviroments":
                List<Enviroment> enviroments = enviromentRepo.findAll();
                model.addAttribute("enviroments", enviroments);
                model.addAttribute("viewEnviroments", true);

                Enviroment enviroment_selected = enviromentRepo.findByID(Long.valueOf(selected_id));
                model.addAttribute("selected_enviroment", enviroment_selected);
                model.addAttribute("enviroments_selected", true);
                break;
            case "types":
                List<Type> types = typeRepo.findAll();
                model.addAttribute("types", types);
                model.addAttribute("viewTypes", true);

                Type type_selected = typeRepo.findByID(Long.valueOf(selected_id));
                model.addAttribute("selected_type", type_selected);
                model.addAttribute("type_selected", true);
                break;
        }

        return"Admin";
    }

    @GetMapping(value = "/sec/Admin/AddEmployee")
    public String AddEmployee(Model model, Principal user, Authentication auth) {
        model.addAttribute("user", user.getName());
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch((authority -> authority.getAuthority().equals("ADMIN")));
        model.addAttribute("isAdmin", isAdmin);
        return "AddEmployee";
    }

    @PostMapping(value = "/sec/Admin/AddEmployee")
    public String AddEmployee(Model model, Principal user, Authentication auth,
            @RequestParam("username") String username, @RequestParam("email") String email,
            @RequestParam("password") String password, @RequestParam("name") String name) {
        model.addAttribute("user", user.getName());
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch((authority -> authority.getAuthority().equals("ADMIN")));
        model.addAttribute("isAdmin", isAdmin);

        RoleUser roleUser = new RoleUser();
        Users newUser = new Users();
        Employee employee = new Employee();

        // verify that the employee dosent exist.
        if (!(userRepo.itExists(username) && employeeRepo.itExists(userRepo.findByUsername(username).getId()))) {
            // check that the user dosent exist.
            if (!userRepo.itExists(username)) {

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

                roleUserRepo.save(roleUser);
                model.addAttribute("epmloyeeAdded", true);

            } else {
                model.addAttribute("userExist", true);
            }

        } else {
            model.addAttribute("employeeExists", true);
        }

        return "AddEmployee";
    }

    @GetMapping(value = "/sec/Admin/AddAnimal")
    public String AddAnimal(Model model, Principal user, Authentication auth) {
        model.addAttribute("user", user.getName());
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch((authority -> authority.getAuthority().equals("ADMIN")));
        model.addAttribute("isAdmin", isAdmin);

        List<Enviroment> env = enviromentRepo.findAll();
        List<Type> type = typeRepo.findAll();
        model.addAttribute("env", env);
        model.addAttribute("type", type);
        return "AddAnimal";
    }

    @PostMapping(value = "/sec/Admin/AddAnimal")
    public String AddAnimal(Model model, Principal user, Authentication auth, HttpServletRequest request,
            @RequestParam("name") String name, @RequestParam("enviroment_search") Long enviroment_id, 
            @RequestParam("description") String description,
            @RequestParam("type_search") Long type_id,
            @RequestParam("img") MultipartFile img,
            @RequestParam("obj3d") MultipartFile obj3d) throws IllegalStateException, IOException {
        
        model.addAttribute("user", user.getName());
        boolean isAdmin = auth.getAuthorities().stream().anyMatch((authority -> authority.getAuthority().equals("ADMIN")));
        model.addAttribute("isAdmin", isAdmin);
        
        Animal newAnimal = new Animal();
        UUID uuid = UUID.randomUUID();

        if(!animalRepo.itExistsByName(name)){
            newAnimal.setName(name);
            newAnimal.setDescription(description);
            newAnimal.setEnviromentId(enviromentRepo.findByID(enviroment_id));
            newAnimal.setTypeID(typeRepo.findByID(type_id));
            
            if(!img.isEmpty()){
                String actualPath = "/AnimalIMG/"+ user.getName() + "/" + uuid.toString() + img.getOriginalFilename();
                String path = request.getSession().getServletContext().getRealPath(actualPath);
                File dirPath = new File(path);
                
                if (!dirPath.exists()) {
                    dirPath.mkdirs();
                }
    
                newAnimal.setImagePath(actualPath);
                img.transferTo(dirPath);
                
            }else{
                newAnimal.setImagePath("/AnimalIMG/img-not-available.png");
            }

            if(!obj3d.isEmpty()){
                logger.info("obj3d has something");
                logger.info("obj3d FILE NAME: " + obj3d.getOriginalFilename());
                File zip = File.createTempFile(UUID.randomUUID().toString(), "temp");
                FileOutputStream o = new FileOutputStream(zip);
                if(IOUtils.copy(obj3d.getInputStream(), o) > 0){
                    logger.info("IOUTILS COPIED FILE");
                }
                o.close();
                String obj3d_path = "/Animal_3D_Scenes/" + name.replace(" ", "");
                String location = request.getSession().getServletContext().getRealPath(obj3d_path);
                File dir3dpath = new File(location);
                if(!dir3dpath.exists()){
                    logger.info("MAKING DIR");
                    dir3dpath.mkdirs();
                }

                try {
                    
                    logger.info("ENTERING TRY CATCH");
                    ZipFile zipFile = new ZipFile(zip);
                    zipFile.extractAll(location);
                    logger.info("EXTRACTING TRY CATCH");
                } catch (ZipException e) {
                    e.printStackTrace();
                }finally{
                    zip.delete();
                }

                File [] temp = dir3dpath.listFiles();
                for(File obj: temp){
                    if(obj.isFile()){
                        logger.info("File: " + obj.getName());
                        if(obj.getName().equals("scene.gltf")){
                            logger.info("/Animal_3D_Scenes/"+ name.replace(" ", "") + "/"+obj.getName());
                            newAnimal.setModel_Path("/Animal_3D_Scenes/"+ name.replace(" ", "") + "/"+obj.getName());
                            break;
                        }
                    }else if (obj.isDirectory()){
                        logger.info("Directory: " );
                    }
                }
            }else{
                logger.info("obj3d has nothing");
            }

            animalRepo.save(newAnimal);
            model.addAttribute("animalAdded", true);
        }else{
            model.addAttribute("animalExists", true);
        }

        List<Enviroment> env = enviromentRepo.findAll();
        List<Type> type = typeRepo.findAll();
        model.addAttribute("env", env);
        model.addAttribute("type", type);
        
       
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

        if(!enviromentRepo.itExistsByName(name)){
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

        if(!typeRepo.itExists(name)){
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