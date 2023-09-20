package com.games;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MovementTests {

//@Test
//public void ValidRookMoveAccepted() {
// ChessBoard testBoard = new ChessBoard();
// ChessPiece testRook = new ChessPiece('R');
// testBoard.placePiece(testRook,3,6);
//}
    @Test
    public void PlacePawnWorks(){
        ChessBoard testBoard = new ChessBoard();
        ChessPiece testPawn = new ChessPiece('P','W');
        testBoard.placePiece(testPawn,0,0);
        assertEquals(testPawn,testBoard.pieceAt(0,0));
    }
}