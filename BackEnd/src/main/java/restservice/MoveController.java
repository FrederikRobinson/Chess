package restservice;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoveController {

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/makeMove")
    public GameState move() {//@RequestParam(value = "name", defaultValue = "World") String name
        //Get the board from the database
        //Attempt the move from the request
        //If it is successful update the database and return the new board
        //If it fails return an error message
        return new GameState(Piece.board);
    }
}