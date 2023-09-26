package com.games;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RookMovementTests {
    ChessBoard testBoard;
    ChessPiece testRookWhite;
    ChessPiece testRookBlack;

    @BeforeEach
    public void setUp() {
        testBoard = new ChessBoard();
        testRookWhite = new ChessPiece('R', 'W');
        testRookBlack = new ChessPiece('R', 'B');
    }

    @Test
    public void PlaceRookWorks() {
        testBoard.placePiece(testRookWhite, 0, 0);
        assertEquals(testRookWhite, testBoard.pieceAt(0, 0));
    }

    @Test
    public void WhiteRookMoveUpValid(){
        testBoard.placePiece(testRookWhite,3,3);
        testBoard.movePiece(3,3,3,4);
        assertEquals(testRookWhite,testBoard.pieceAt(3,4));
    }
}



