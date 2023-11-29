package restservice.controllers;

import com.games.ChessBoard;
import org.springframework.web.bind.annotation.*;
import restservice.resources.NewGameRequest;
import restservice.services.DatabaseManager;
import restservice.resources.GameMove;
import restservice.resources.GameState;
import restservice.resources.NewGameResponse;
import websocket.resources.JoinGameResponse;

@RestController
public class MoveController {
    private DatabaseManager databaseManager = new DatabaseManager();
    public void setDatabaseManager(DatabaseManager newDatabaseManager){
        databaseManager = newDatabaseManager;
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/makeMove")
    public GameState move(@RequestBody GameMove move) {
        ChessBoard chessBoard = databaseManager.getGame(move.getGameId());
        if (chessBoard == null){
            return new GameState(null,'X');}
        chessBoard.takeTurn(move.getPlayerColour(), move.getStartXPos(), move.getStartYPos(), move.getEndXPos(), move.getEndYPos());
        databaseManager.updateGame(chessBoard,move.getGameId());

        return new GameState(chessBoard.getBoard(),chessBoard.getCurrentPlayer());
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/createGame")
    public NewGameResponse makeGame(@RequestBody NewGameRequest newGameRequest){

        return databaseManager.createGame(newGameRequest.getUserId());
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/getGame")
    public GameState getGame(@RequestBody int gameId){
        ChessBoard chessBoard = databaseManager.getGame(gameId);
        if (chessBoard==null){
            return new GameState(null,'X');
        }
        return new GameState(chessBoard.getBoard(), chessBoard.getCurrentPlayer());
    }

}