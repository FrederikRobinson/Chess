package websocket.resources;

import com.games.ChessPiece;

public record JoinGameResponse(ChessPiece[][] board, int playerNumber, char playerTurn) {
}
