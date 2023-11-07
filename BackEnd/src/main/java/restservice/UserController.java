package restservice;


import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private DatabaseManager databaseManager = new DatabaseManager();

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/signUp")
    public int move(@RequestBody UserDetails user) {
        return databaseManager.signUp(user);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/login")
    public int makeGame(@RequestBody UserDetails user) {
        return databaseManager.login(user);
    }
}
