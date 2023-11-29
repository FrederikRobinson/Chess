package restservice.controllers;


import org.springframework.web.bind.annotation.*;
import restservice.services.DatabaseManager;
import restservice.resources.UserDetails;

@RestController
public class UserController {
    private DatabaseManager databaseManager = new DatabaseManager();
    public void setDatabaseManager(DatabaseManager newDatabaseManager){
        databaseManager=newDatabaseManager;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/signUp")
    public int signUp(@RequestBody UserDetails user) {
        return databaseManager.signUp(user);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/login")
    public int login(@RequestBody UserDetails user) {
        return databaseManager.login(user);
    }
}
