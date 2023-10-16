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
        testKingWhite = new ChessPiece('K','W');
        testKingBlack = new ChessPiece('K','B');
        testKnightWhite = new ChessPiece('N','W');
        testKnightBlack = new ChessPiece('N','B');
        testQueenWhite = new ChessPiece('Q','W');
        testQueenBlack = new ChessPiece('Q','B');
        testPawnWhite = new ChessPiece('P','W');
        testPawnBlack = new ChessPiece('P','B');
        testBishopWhite = new ChessPiece('B','W');
        testBishopBlack = new ChessPiece('B','B');
        testRookWhite = new ChessPiece('R','W');
        testRookBlack = new ChessPiece('R','B');
        emptyPiece = testBoard.getEmptyPiece();
    }

    @Test
    public void PlaceRookWorks() {
        testBoard.placePiece(testRookWhite, 0, 0);
        assertEquals(testRookWhite, testBoard.pieceAt(0, 0));
    }
    @Test
    public void PlaceQueenWorks() {
        testBoard.placePiece(testQueenWhite, 0, 0);
        assertEquals(testQueenWhite, testBoard.pieceAt(0, 0));
    }
    @Test
    public void PlacePawnWorks(){
        testBoard.placePiece(testPawnWhite,0,0);
        assertEquals(testPawnWhite,testBoard.pieceAt(0,0));
    }
    @Test
    public void PlaceKnightWorks() {
        testBoard.placePiece(testKnightWhite, 0, 0);
        assertEquals(testKnightWhite, testBoard.pieceAt(0, 0));
    }
    @Test
    public void PlaceKingWorks() {
        testBoard.placePiece(testKingWhite, 0, 0);
        assertEquals(testKingWhite, testBoard.pieceAt(0, 0));
    }
    @Test
    public void PlaceBishopWorks() {
        testBoard.placePiece(testBishopWhite, 0, 0);
        assertEquals(testBishopWhite, testBoard.pieceAt(0, 0));
    }
    @Test
    public void PlaceEmptyPieceWorks() {
        testBoard.placePiece(testRookWhite, 0, 0);
        assertEquals(testRookWhite, testBoard.pieceAt(0, 0));
        testBoard.placePiece(emptyPiece,0,0);
        assertEquals(emptyPiece, testBoard.pieceAt(0, 0));
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
        assertEquals(testKingWhite,testBoard.pieceAt(0,0));
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
