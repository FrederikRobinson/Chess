package com.games;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class CheckmateTests {
    ChessBoard testBoard;
    ChessPiece testKingWhite;
    ChessPiece testKingBlack;
    ChessPiece testRookWhite;
    ChessPiece testRookBlack;
    ChessPiece testBishopWhite;
    ChessPiece emptyPiece;
    @BeforeEach
    public void setUp(){
        testBoard = new ChessBoard();
        testKingWhite = new ChessPiece('K','W');
        testKingBlack = new ChessPiece('K','B');
        testRookWhite = new ChessPiece('R','W');
        testRookBlack = new ChessPiece('R','B');
        testBishopWhite = new ChessPiece('B','W');
        emptyPiece = testBoard.getPiece('X','X');
    }
    @Test
    public void NotCheckmateIfBoardEmpty(){
        assertFalse(testBoard.isCheckmate('W'));
    }
    @Test
    public void IsCheckmateIfWhiteKingIsCorneredByRooks(){
        testBoard.placePiece(testKingWhite,0,0);
        testBoard.placePiece(testRookBlack,5,0);
        testBoard.placePiece(testRookBlack,5,1);
        assertTrue(testBoard.isCheckmate('W'));
    }
    @Test
    public void IsCheckmateIfBlackKingIsCorneredByRooks(){
        testBoard.placePiece(testKingBlack,0,0);
        testBoard.placePiece(testRookWhite,5,0);
        testBoard.placePiece(testRookWhite,5,1);
        assertTrue(testBoard.isCheckmate('B'));
    }
    @Test
    public void IsNotCheckmateIfWhiteKingIsCheckedByRook(){
        testBoard.placePiece(testKingWhite,0,0);
        testBoard.placePiece(testRookBlack,5,0);
        assertFalse(testBoard.isCheckmate('W'));
    }
    @Test
    public void IsNotCheckmateIfBlackKingIsCheckedByRook(){
        testBoard.placePiece(testKingBlack,0,0);
        testBoard.placePiece(testRookWhite,5,0);
        assertFalse(testBoard.isCheckmate('B'));
    }
    @Test
    public void IsNotCheckmateIfKingCanEscapeByCapture(){
        testBoard.placePiece(testKingWhite,0,0);
        testBoard.placePiece(testRookBlack,5,0);
        testBoard.placePiece(testRookBlack,6,1);
        testBoard.placePiece(testRookWhite,5,6);
        assertFalse(testBoard.isCheckmate('W'));
    }
    @Test
    public void IsNotCheckmateIfKingCanEscapeByObstruction(){
        testBoard.placePiece(testKingWhite,0,0);
        testBoard.placePiece(testRookBlack,5,0);
        testBoard.placePiece(testRookBlack,6,1);
        testBoard.placePiece(testRookWhite,4,6);
        assertFalse(testBoard.isCheckmate('W'));
    }
    @Test
    public void IsCheckmateIfKingCannotEscapeByCaptureBecauseOfPin(){
        testBoard.placePiece(testKingWhite,0,0);
        testBoard.placePiece(testRookBlack,5,0);
        testBoard.placePiece(testRookBlack,6,1);
        testBoard.placePiece(testBishopWhite,0,5);
        testBoard.placePiece(testRookBlack,0,7);
        assertTrue(testBoard.isCheckmate('W'));
    }
    @Test
    public void IsCheckmateIfKingCannotEscapeByObstructionBecauseOfPin(){
        testBoard.placePiece(testKingWhite,0,0);
        testBoard.placePiece(testRookBlack,6,0);
        testBoard.placePiece(testRookBlack,6,1);
        testBoard.placePiece(testBishopWhite,0,5);
        testBoard.placePiece(testRookBlack,0,7);
        assertTrue(testBoard.isCheckmate('W'));
    }

}
