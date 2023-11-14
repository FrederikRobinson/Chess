package restservice.resources;

import com.games.ChessPiece;

public record GameState(ChessPiece[][] updatedBoard, char playerTurn) { }