package com.games;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
public class PlaceAndGetMethodTests {
    ChessBoard testBoard;
    ChessPiece testKingWhite;
    ChessPiece testKingBlack;
    ChessPiece testRookWhite;
    ChessPiece testRookBlack;
    ChessPiece testQueenWhite;
    ChessPiece testQueenBlack;
    ChessPiece testKnightWhite;
    ChessPiece testKnightBlack;
    ChessPiece testPawnWhite;
    ChessPiece testPawnBlack;
    ChessPiece testBishopWhite;
    ChessPiece testBishopBlack;
    ChessPiece emptyPiece;
    @BeforeEach
    public void setUp(){
        testBoard = new ChessBoard();
        testKingWhite = testBoard.getPiece('K','W');
        testKingBlack = testBoard.getPiece('K','B');
        testKnightWhite = testBoard.getPiece('N','W');
        testKnightBlack = testBoard.getPiece('N','B');
        testQueenWhite = testBoard.getPiece('Q','W');
        testQueenBlack = testBoard.getPiece('Q','B');
        testPawnWhite = testBoard.getPiece('P','W');
        testPawnBlack = testBoard.getPiece('P','B');
        testBishopWhite = testBoard.getPiece('B','W');
        testBishopBlack = testBoard.getPiece('B','B');
        testRookWhite = testBoard.getPiece('R','W');
        testRookBlack = testBoard.getPiece('R','B');
        emptyPiece = testBoard.getPiece('X','X');
    }

    @Test
    public void PlaceRookWorks() {
        testBoard.placePiece(testRookWhite, 0, 0);
        assertTrue(testRookWhite.isEqual(testBoard.pieceAt(0, 0)));
    }
    @Test
    public void PlaceQueenWorks() {
        testBoard.placePiece(testQueenWhite, 0, 0);
        assertTrue(testQueenWhite.isEqual(testBoard.pieceAt(0, 0)));
    }
    @Test
    public void PlacePawnWorks(){
        testBoard.placePiece(testPawnWhite,0,0);
        assertTrue(testPawnWhite.isEqual(testBoard.pieceAt(0,0)));
    }
    @Test
    public void PlaceKnightWorks() {
        testBoard.placePiece(testKnightWhite, 0, 0);
        assertTrue(testKnightWhite.isEqual(testBoard.pieceAt(0, 0)));
    }
    @Test
    public void PlaceKingWorks() {
        testBoard.placePiece(testKingWhite, 0, 0);
        assertTrue(testKingWhite.isEqual(testBoard.pieceAt(0, 0)));
    }
    @Test
    public void PlaceBishopWorks() {
        testBoard.placePiece(testBishopWhite, 0, 0);
        assertTrue(testBishopWhite.isEqual(testBoard.pieceAt(0, 0)));
    }
    @Test
    public void PlaceEmptyPieceWorks() {
        testBoard.placePiece(testRookWhite, 0, 0);
        assertTrue(testRookWhite.isEqual(testBoard.pieceAt(0, 0)));
        testBoard.placePiece(emptyPiece,0,0);
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(0, 0)));
    }
    @Test
    public void PlaceOffBoardReturnsFalse(){
        assertFalse(testBoard.placePiece(testPawnWhite,-1,0));
        assertFalse(testBoard.placePiece(testPawnWhite,0,-1));
        assertFalse(testBoard.placePiece(testPawnWhite,8,0));
        assertFalse(testBoard.placePiece(testPawnWhite,0,8));
    }
    @Test
    public void PlaceOnBoardReturnsTrue(){
        assertTrue(testBoard.placePiece(testPawnWhite,0,0));
        assertTrue(testBoard.placePiece(testPawnWhite,7,7));
        assertTrue(testBoard.placePiece(testPawnWhite,0,7));
        assertTrue(testBoard.placePiece(testPawnWhite,7,0));
        assertTrue(testBoard.placePiece(testPawnWhite,2,4));
        assertTrue(testBoard.placePiece(testPawnWhite,6,3));
    }
    @Test
    public void PlacingAPieceOverridesOtherPieces(){
        testBoard.placePiece(testPawnWhite,0,0);
        testBoard.placePiece(testKingWhite,0,0);
        assertTrue(testKingWhite.isEqual(testBoard.pieceAt(0,0)));
    }
    @Test
    public void GetPieceGetsTheCorrectPiece(){
        assertTrue(testPawnWhite.isEqual(testBoard.getPiece('P','W')));
        assertTrue(testPawnBlack.isEqual(testBoard.getPiece('P','B')));
        assertTrue(testKnightWhite.isEqual(testBoard.getPiece('N','W')));
        assertTrue(testKnightBlack.isEqual(testBoard.getPiece('N','B')));
        assertTrue(testKingWhite.isEqual(testBoard.getPiece('K','W')));
        assertTrue(testKingBlack.isEqual(testBoard.getPiece('K','B')));
        assertTrue(testQueenWhite.isEqual(testBoard.getPiece('Q','W')));
        assertTrue(testQueenBlack.isEqual(testBoard.getPiece('Q','B')));
        assertTrue(testBishopWhite.isEqual(testBoard.getPiece('B','W')));
        assertTrue(testBishopBlack.isEqual(testBoard.getPiece('B','B')));
        assertTrue(testRookWhite.isEqual(testBoard.getPiece('R','W')));
        assertTrue(testRookBlack.isEqual(testBoard.getPiece('R','B')));
        assertTrue(emptyPiece.isEqual(testBoard.getPiece('X','X')));

    }
}
