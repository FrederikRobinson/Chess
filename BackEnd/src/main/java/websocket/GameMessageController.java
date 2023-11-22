package websocket;
import com.games.ChessBoard;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import restservice.resources.GameState;
import restservice.resources.NewGameResponse;
import restservice.services.DatabaseManager;
import websocket.resources.JoinGameResponse;
import websocket.resources.NewGameRequest;

public class GameMessageController {
    private DatabaseManager databaseManager = new DatabaseManager();
    @MessageMapping("/app/joinGame")
    @SendTo("/topic/{gameId}")
    public JoinGameResponse joinGame(@DestinationVariable int gameId,int userId) throws Exception {
        JoinGameResponse joinGameResponse = databaseManager.joinGame(userId,gameId);
    }
}
