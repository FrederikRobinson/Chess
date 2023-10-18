package com.games;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
public class StalemateTests {
    ChessBoard testBoard;
    ChessPiece testKingWhite;
    ChessPiece testKingBlack;
    ChessPiece testRookWhite;
    ChessPiece testRookBlack;
    ChessPiece emptyPiece;
    @BeforeEach
    public void setUp(){
        testBoard = new ChessBoard();
        testKingWhite = new ChessPiece('K','W');
        testKingBlack = new ChessPiece('K','B');
        testRookWhite = new ChessPiece('R','W');
        testRookBlack = new ChessPiece('R','B');
        emptyPiece = testBoard.getPiece('X','X');
    }
    @Test
    public void EmptyBoardIsStalemate(){
        assertTrue(testBoard.isStalemate('W'));
    }
    @Test
    public void BoardWithAWhitePieceIsNotStalemateForWhite(){
        testBoard.placePiece(testRookWhite,2,2);
        assertFalse(testBoard.isStalemate('W'));
    }
    @Test
    public void BoardWithABlackPieceIsNotStalemateForBlack(){
        testBoard.placePiece(testRookBlack,2,2);
        assertFalse(testBoard.isStalemate('B'));
    }
    @Test
    public void BoardWithOnlyAWhitePieceIsStalemateForBlack(){
        testBoard.placePiece(testRookWhite,2,2);
        assertTrue(testBoard.isStalemate('B'));
    }
    @Test
    public void BoardWithOnlyBlackPieceIsStalemateForWhite(){
        testBoard.placePiece(testRookBlack,2,2);
        assertTrue(testBoard.isStalemate('W'));
    }
    @Test
    public void CheckMateIsNotStalemate(){
        testBoard.placePiece(testKingWhite,0,0);
        testBoard.placePiece(testRookBlack,5,0);
        testBoard.placePiece(testRookBlack,5,1);
        assertFalse(testBoard.isStalemate('W'));
    }
    @Test
    public void KingTrappedButNotCheckedIsStalemate(){
        testBoard.placePiece(testKingWhite,0,0);
        testBoard.placePiece(testRookBlack,5,1);
        testBoard.placePiece(testRookBlack,1,5);
        assertTrue(testBoard.isStalemate('W'));
    }
    @Test
    public void KingTrappedButWithOtherPieceIsNotStalemate(){
        testBoard.placePiece(testKingWhite,0,0);
        testBoard.placePiece(testRookWhite,4,4);
        testBoard.placePiece(testRookBlack,5,1);
        testBoard.placePiece(testRookBlack,1,5);
        assertFalse(testBoard.isStalemate('W'));
    }
}
