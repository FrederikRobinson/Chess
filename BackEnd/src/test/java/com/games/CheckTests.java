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
    ChessPiece testPawnWhite;
    ChessPiece testPawnBlack;
    ChessPiece emptyPiece;
    @BeforeEach
    public void setUp(){
        testBoard = new ChessBoard();
        testKingWhite = testBoard.getPiece('K','W');
        testKingBlack = testBoard.getPiece('K','B');
        testRookWhite = testBoard.getPiece('R','W');
        testRookBlack = testBoard.getPiece('R','B');
        emptyPiece = testBoard.getPiece('X','X');
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
    @Test
    public void NotInCheckIfNoKingOnBoard(){
        assertFalse(testBoard.isCheck('W'));
        assertFalse(testBoard.isCheck('B'));
    }
    @Test
    public void AlliesCannotCauseCheck(){
        testBoard.placePiece(testKingWhite,0,0);
        testBoard.placePiece(testRookWhite,0,1);
        assertFalse(testBoard.isCheck('W'));
    }
    @Test
    public void CannotMovePieceIfItPutsPlayerIntoCheckWhite(){
        testBoard.placePiece(testKingWhite,0,0);
        testBoard.placePiece(testRookWhite,0,1);
        testBoard.placePiece(testRookBlack,0,2);
        testBoard.movePiece(0,1,1,1);
        assertTrue(testRookWhite.isEqual(testBoard.pieceAt(0,1)));
    }
    @Test
    public void CannotMovePieceIfItPutsPlayerIntoCheckBlack(){
        testBoard.placePiece(testKingBlack,0,0);
        testBoard.placePiece(testRookBlack,0,1);
        testBoard.placePiece(testRookWhite,0,2);
        testBoard.movePiece(0,1,1,1);
        assertTrue(testRookBlack.isEqual(testBoard.pieceAt(0,1)));
    }
    @Test
    public void CannotMovePieceIfItPutsPlayerIntoCheckEnPassantWhite(){
        testBoard.placePiece(testKingWhite,0,0);
        testBoard.placePiece(testRookWhite,0,1);
        testBoard.placePiece(testRookBlack,0,2);
        testBoard.movePiece(0,1,1,1);
        assertTrue(testRookWhite.isEqual(testBoard.pieceAt(0,1)));
    }
}
