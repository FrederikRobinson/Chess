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
        assertFalse(testBoard.placePiece(testPawn,-1,0));
        assertFalse(testBoard.placePiece(testPawn,0,-1));
        assertFalse(testBoard.placePiece(testPawn,8,0));
        assertFalse(testBoard.placePiece(testPawn,0,8));
    }
    @Test
    public void PlaceOnBoardReturnsTrue(){
        ChessBoard testBoard = new ChessBoard();
        ChessPiece testPawn = new ChessPiece('P','W');
        assertTrue(testBoard.placePiece(testPawn,0,0));
        assertTrue(testBoard.placePiece(testPawn,7,7));
        assertTrue(testBoard.placePiece(testPawn,0,7));
        assertTrue(testBoard.placePiece(testPawn,7,0));
        assertTrue(testBoard.placePiece(testPawn,2,4));
        assertTrue(testBoard.placePiece(testPawn,6,3));
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
    public void MovingBlackPawnOneSpaceDownIsValid(){
        ChessBoard testBoard = new ChessBoard();
        ChessPiece testPawn = new ChessPiece('P','B');
        testBoard.placePiece(testPawn,0,6);
        testBoard.movePiece(0,6,0,5);
        assertEquals(testPawn,testBoard.pieceAt(0,5));
    }
    @Test
    public void MovingBlackPawnTwoSpaceDownFromStartIsValid(){
        ChessBoard testBoard = new ChessBoard();
        ChessPiece testPawn = new ChessPiece('P','B');
        testBoard.placePiece(testPawn,0,6);
        testBoard.movePiece(0,6,0,4);
        assertEquals(testPawn,testBoard.pieceAt(0,4));
    }
    @Test
    public void MovingBlackPawnOneSpaceUpIsInvalid(){
        ChessBoard testBoard = new ChessBoard();
        ChessPiece testPawn = new ChessPiece('P','B');
        testBoard.placePiece(testPawn,0,6);
        testBoard.movePiece(0,6,0,7);
        assertNotEquals(testPawn,testBoard.pieceAt(0,7));
    }
    @Test
    public void MovingBlackPawnTwoSpaceDownElsewhereIsInvalid(){
        ChessBoard testBoard = new ChessBoard();
        ChessPiece testPawn = new ChessPiece('P','B');
        testBoard.placePiece(testPawn,0,5);
        testBoard.movePiece(0,5,0,3);
        assertNotEquals(testPawn,testBoard.pieceAt(0,3));
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