package restservice.resources;

import com.games.ChessPiece;

public record NewGameResponse(ChessPiece[][] board, int gameId,int playerNumber,char playerTurn) {

}
