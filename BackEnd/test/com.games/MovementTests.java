package com.games;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    @Test
    public void PlaceOffBoardReturnsFalse(){
        ChessBoard testBoard = new ChessBoard();
        ChessPiece testPawn = new ChessPiece('P','W');
        assertEquals(false,testBoard.placePiece(testPawn,-1,0));
        assertEquals(false,testBoard.placePiece(testPawn,0,-1));
        assertEquals(false,testBoard.placePiece(testPawn,8,0));
        assertEquals(false,testBoard.placePiece(testPawn,0,8));
    }
    @Test
    public void PlaceOnBoardReturnsTrue(){
        ChessBoard testBoard = new ChessBoard();
        ChessPiece testPawn = new ChessPiece('P','W');
        assertEquals(true,testBoard.placePiece(testPawn,0,0));
        assertEquals(true,testBoard.placePiece(testPawn,7,7));
        assertEquals(true,testBoard.placePiece(testPawn,0,7));
        assertEquals(true,testBoard.placePiece(testPawn,7,0));
        assertEquals(true,testBoard.placePiece(testPawn,2,4));
        assertEquals(true,testBoard.placePiece(testPawn,6,3));
    }

    @Test
    public void MovingWhitePawnOneSpaceUpIsValid(){
        ChessBoard testBoard = new ChessBoard();
        ChessPiece testPawn = new ChessPiece('P','W');
        testBoard.placePiece(testPawn,0,1);
        testBoard.movePiece(0,1,0,2);
        assertEquals(testPawn,testBoard.pieceAt(0,2));
    }
    @Test
    public void MovingWhitePawnTwoSpaceUpFromStartIsValid(){
        ChessBoard testBoard = new ChessBoard();
        ChessPiece testPawn = new ChessPiece('P','W');
        testBoard.placePiece(testPawn,0,1);
        testBoard.movePiece(0,1,0,3);
        assertEquals(testPawn,testBoard.pieceAt(0,3));
    }
    @Test
    public void MovingWhitePawnOneSpaceDownIsInvalid(){
        ChessBoard testBoard = new ChessBoard();
        ChessPiece testPawn = new ChessPiece('P','W');
        testBoard.placePiece(testPawn,0,1);
        testBoard.movePiece(0,1,0,0);
        assertNotEquals(testPawn,testBoard.pieceAt(0,0));
    }
    @Test
    public void MovingWhitePawnTwoSpaceUpElsewhereIsInvalid(){
        ChessBoard testBoard = new ChessBoard();
        ChessPiece testPawn = new ChessPiece('P','W');
        testBoard.placePiece(testPawn,0,2);
        testBoard.movePiece(0,2,0,4);
        assertNotEquals(testPawn,testBoard.pieceAt(0,4));
    }
    @Test
    public void PieceIsNoLongerAtStartingPositionAfterMove(){
        ChessBoard testBoard = new ChessBoard();
        ChessPiece testPawn = new ChessPiece('P','W');
        testBoard.placePiece(testPawn,0,1);
        testBoard.movePiece(0,1,0,2);
        assertNotEquals(testPawn,testBoard.pieceAt(0,1));
    }
}