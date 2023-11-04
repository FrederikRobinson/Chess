package com.games;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnPassantTests {
    ChessBoard testBoard;
    ChessPiece testPawnWhite;
    ChessPiece testPawnBlack;
    ChessPiece testKingWhite;
    ChessPiece testKingBlack;
    ChessPiece testRookBlack;
    ChessPiece testRookWhite;
    ChessPiece emptyPiece;
    @BeforeEach
    public void setUp(){
        testBoard = new ChessBoard();
        testPawnWhite = testBoard.getPiece('P','W');
        testPawnBlack = testBoard.getPiece('P','B');
        testKingWhite = testBoard.getPiece('K','W');
        testRookBlack = testBoard.getPiece('R','B');
        testKingBlack = testBoard.getPiece('K','B');
        testRookWhite = testBoard.getPiece('R','W');
        emptyPiece = testBoard.getPiece('X','X');
    }


    @Test
    public void EnPassantIsPossibleWhite(){
        testBoard.placePiece(testPawnWhite,0,4);
        testBoard.placePiece(testPawnBlack,1,6);
        testBoard.movePiece(1,6,1,4);
        testBoard.movePiece(0,4,1,5);
        assertTrue(testPawnWhite.isEqual(testBoard.pieceAt(1,5)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(1,6)));
    }
    @Test
    public void EnPassantIsPossibleBlack(){
        testBoard.placePiece(testPawnWhite,0,1);
        testBoard.placePiece(testPawnBlack,1,3);
        testBoard.movePiece(0,1,0,3);
        testBoard.movePiece(1,3,0,2);
        assertTrue(testPawnBlack.isEqual(testBoard.pieceAt(0,2)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(0,3)));
    }
    @Test
    public void EnPassantIsNotPossibleIfWouldCauseCheckCheckWhite(){
        testBoard.placePiece(testPawnWhite,1,4);
        testBoard.placePiece(testPawnBlack,2,6);
        testBoard.placePiece(testRookBlack,0,4);
        testBoard.placePiece(testKingWhite,5,4);
        testBoard.movePiece(2,6,2,4);
        testBoard.movePiece(1,4,2,5);
        assertTrue(testPawnWhite.isEqual(testBoard.pieceAt(1,4)));
        assertTrue(testPawnBlack.isEqual(testBoard.pieceAt(2,4)));
    }
    @Test
    public void EnPassantIsNotPossibleIfWouldCauseCheckCheckBlack(){
        testBoard.placePiece(testPawnWhite,1,1);
        testBoard.placePiece(testPawnBlack,2,3);
        testBoard.placePiece(testRookWhite,0,3);
        testBoard.placePiece(testKingBlack,5,3);
        testBoard.movePiece(1,1,1,3);
        testBoard.movePiece(2,3,1,2);
        assertTrue(testPawnWhite.isEqual(testBoard.pieceAt(1,3)));
        assertTrue(testPawnBlack.isEqual(testBoard.pieceAt(2,3)));
    }
}