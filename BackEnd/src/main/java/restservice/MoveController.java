package restservice;

import com.games.ChessBoard;
import org.springframework.web.bind.annotation.*;

@RestController
public class MoveController {
    private ChessBoard tempCurrentGame = new ChessBoard(1);
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/makeMove")
    public GameState move(@RequestBody GameMove move) {//@RequestParam(value = "name", defaultValue = "World") String name
        //Get the board from the database
        //Attempt the move from the request
        //If it is successful update the database and return the new board
        //If it fails return an error message
        if (!tempCurrentGame.takeTurn(move.getPlayerColour(),move.getStartXPos(),move.getStartYPos(),move.getEndXPos(),move.getEndYPos())){
            System.out.println("Bad");
        }

        return new GameState(tempCurrentGame.getBoard(),tempCurrentGame.getCurrentPlayer());
    }
}