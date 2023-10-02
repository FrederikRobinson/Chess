package com.games;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class CheckTests {
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
        emptyPiece = testBoard.getEmptyPiece();
    }
    @Test
    public void AKingOnAnEmptyBoardIsNotCheckInCheck(){
        testBoard.placePiece(testKingWhite,2,2);
        assertFalse(testBoard.isCheck('W'));
    }
    @Test
    public void BlackRookCanCauseWhiteCheck(){
        testBoard.placePiece(testRookBlack,3,3);
        testBoard.placePiece(testKingWhite,3,4);
        assertTrue(testBoard.isCheck('W'));
    }
}
