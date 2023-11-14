package restservice.controllers;

import com.games.ChessBoard;
import org.springframework.web.bind.annotation.*;
import restservice.services.DatabaseManager;
import restservice.resources.GameMove;
import restservice.resources.GameState;
import restservice.resources.NewGameResponse;

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
    @PostMapping("/createGame")
    public NewGameResponse makeGame(@RequestBody int userId){
        return databaseManager.createGame(userId);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/getGame")
    public GameState getGame(@RequestBody int gameId){
        ChessBoard chessBoard = databaseManager.getGame(gameId);
        return new GameState(chessBoard.getBoard(), chessBoard.getCurrentPlayer());
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/joinGame")
    public GameState joinGame(@RequestBody int userId,int gameId){
        ChessBoard chessBoard = databaseManager.joinGame(userId,gameId);
        return new GameState(chessBoard.getBoard(), chessBoard.getCurrentPlayer());
    }
}