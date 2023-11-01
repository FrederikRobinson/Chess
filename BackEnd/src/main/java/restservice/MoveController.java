package restservice;

import com.games.ChessBoard;
import org.springframework.web.bind.annotation.*;

@RestController
public class MoveController {
    private ChessBoard tempCurrentGame = new ChessBoard(1);
    private DatabaseManager databaseManager = new DatabaseManager();
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/makeMove")
    public GameState move(@RequestBody GameMove move) {//@RequestParam(value = "name", defaultValue = "World") String name
        //Get the board from the database
        ChessBoard chessBoard = databaseManager.getGame(move.getGameId());
        if (chessBoard == null){
            return new GameState(tempCurrentGame.getBoard(),tempCurrentGame.getCurrentPlayer());}
        //Attempt the move from the request
        chessBoard.takeTurn(move.getPlayerColour(), move.getStartXPos(), move.getStartYPos(), move.getEndXPos(), move.getEndYPos());
        //If it is successful update the database and return the new board
        boolean updateDb = databaseManager.updateGame(chessBoard,move.getGameId());
        //If it fails return an error message
        if (!tempCurrentGame.takeTurn(move.getPlayerColour(),move.getStartXPos(),move.getStartYPos(),move.getEndXPos(),move.getEndYPos())){
            System.out.println("Bad");
        }

        return new GameState(chessBoard.getBoard(),chessBoard.getCurrentPlayer());
    }
//    @CrossOrigin(origins = "http://localhost:5173")
//    @GetMapping("/makeMove")
//    public NewGameResponse makeGame(){
//        return databaseManager.createGame();
//    }
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/makeMove")
    public NewGameResponse makeGame(){
        return databaseManager.createGame();
    }
}
