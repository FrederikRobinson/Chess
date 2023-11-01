package restservice;

import com.games.ChessBoard;
import com.games.ChessPiece;

public record NewGameResponse(ChessPiece[][] board, int gameId) {

}
