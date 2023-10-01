package com.games;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RookMovementTests {
    ChessBoard testBoard;
    ChessPiece testRookWhite;
    ChessPiece testRookBlack;
    ChessPiece emptyPiece;

    @BeforeEach
    public void setUp() {
        testBoard = new ChessBoard();
        testRookWhite = new ChessPiece('R', 'W');
        testRookBlack = new ChessPiece('R', 'B');
        emptyPiece = testBoard.getEmptyPiece();
    }

    @Test
    public void PlaceRookWorks() {
        testBoard.placePiece(testRookWhite, 0, 0);
        assertEquals(testRookWhite, testBoard.pieceAt(0, 0));
    }

    @Test
    public void WhiteRookMoveUpOneValid(){
        testBoard.placePiece(testRookWhite,3,3);
        testBoard.movePiece(3,3,3,4);
        assertEquals(testRookWhite,testBoard.pieceAt(3,4));
    }

    @Test
    public void WhiteRookMoveUpMultipleValid(){
        testBoard.placePiece(testRookWhite,3,3);
        testBoard.movePiece(3,3,3,7);
        assertEquals(testRookWhite,testBoard.pieceAt(3,7));
    }
    @Test
    public void WhiteRookMoveLeftOneValid(){
        testBoard.placePiece(testRookWhite,3,3);
        testBoard.movePiece(3,3,2,3);
        assertEquals(testRookWhite,testBoard.pieceAt(2,3));
    }

    @Test
    public void WhiteRookMoveLeftMultipleValid(){
        testBoard.placePiece(testRookWhite,3,3);
        testBoard.movePiece(3,3,0,3);
        assertEquals(testRookWhite,testBoard.pieceAt(0,3));
    }
    @Test
    public void WhiteRookMoveDownOneValid(){
        testBoard.placePiece(testRookWhite,3,3);
        testBoard.movePiece(3,3,3,2);
        assertEquals(testRookWhite,testBoard.pieceAt(3,2));
    }

    @Test
    public void WhiteRookMoveDownMultipleValid(){
        testBoard.placePiece(testRookWhite,3,3);
        testBoard.movePiece(3,3,3,0);
        assertEquals(testRookWhite,testBoard.pieceAt(3,0));
    }
    @Test
    public void WhiteRookMoveRightOneValid(){
        testBoard.placePiece(testRookWhite,3,3);
        testBoard.movePiece(3,3,4,3);
        assertEquals(testRookWhite,testBoard.pieceAt(4,3));
    }

    @Test
    public void WhiteRookMoveRightMultipleValid(){
        testBoard.placePiece(testRookWhite,3,3);
        testBoard.movePiece(3,3,7,3);
        assertEquals(testRookWhite,testBoard.pieceAt(7,3));
    }
    @Test
    public void BlackRookValidMoves(){
        testBoard.placePiece(testRookBlack,3,3);
        testBoard.movePiece(3,3,5,3);
        assertEquals(testRookBlack,testBoard.pieceAt(5,3));
        testBoard.movePiece(5,3,5,6);
        assertEquals(testRookBlack,testBoard.pieceAt(5,6));
        testBoard.movePiece(5,6,2,6);
        assertEquals(testRookBlack,testBoard.pieceAt(2,6));
        testBoard.movePiece(2,6,2,0);
        assertEquals(testRookBlack,testBoard.pieceAt(2,0));

    }
    @Test
    public void WhiteRookInvalidMoves(){
        testBoard.placePiece(testRookWhite,3,3);
        testBoard.movePiece(3,3,4,4);
        testBoard.movePiece(3,3,5,7);
        testBoard.movePiece(3,3,2,2);
        testBoard.movePiece(3,3,0,0);
        testBoard.movePiece(3,3,0,7);
        testBoard.movePiece(3,3,7,0);
        assertEquals(emptyPiece,testBoard.pieceAt(4,4));
        assertEquals(emptyPiece,testBoard.pieceAt(5,7));
        assertEquals(emptyPiece,testBoard.pieceAt(2,2));
        assertEquals(emptyPiece,testBoard.pieceAt(0,0));
        assertEquals(emptyPiece,testBoard.pieceAt(0,7));
        assertEquals(emptyPiece,testBoard.pieceAt(7,0));
    }
    @Test
    public void BlackRookInvalidMoves(){
        testBoard.placePiece(testRookBlack,3,3);
        testBoard.movePiece(3,3,4,4);
        testBoard.movePiece(3,3,5,7);
        testBoard.movePiece(3,3,2,2);
        testBoard.movePiece(3,3,0,0);
        testBoard.movePiece(3,3,0,7);
        testBoard.movePiece(3,3,7,0);
        assertEquals(emptyPiece,testBoard.pieceAt(4,4));
        assertEquals(emptyPiece,testBoard.pieceAt(5,7));
        assertEquals(emptyPiece,testBoard.pieceAt(2,2));
        assertEquals(emptyPiece,testBoard.pieceAt(0,0));
        assertEquals(emptyPiece,testBoard.pieceAt(0,7));
        assertEquals(emptyPiece,testBoard.pieceAt(7,0));
    }

    @Test
    public void WhiteRookCannotJump(){
        testBoard.placePiece(testRookWhite,6,6);
        testBoard.placePiece(testRookWhite,6,5);
        testBoard.movePiece(6,6,6,4);
        assertEquals(emptyPiece,testBoard.pieceAt(6,4));

    }
    @Test
    public void BlackRookCannotJump(){
        testBoard.placePiece(testRookWhite,0,0);
        testBoard.placePiece(testRookWhite,0,5);
        testBoard.movePiece(0,0,0,7);
        assertEquals(emptyPiece,testBoard.pieceAt(0,7));

    }
    @Test
    public void WhiteRookCanCaptureEnemyPieces(){
        testBoard.placePiece(testRookWhite,3,3);
        testBoard.placePiece(testRookBlack,5,3);
        testBoard.movePiece(3,3,5,3);
        assertEquals(testRookWhite,testBoard.pieceAt(5,3));
    }
    @Test
    public void WhiteRookCannotCaptureAllyPieces(){
        testBoard.placePiece(testRookWhite,3,3);
        testBoard.placePiece(testRookWhite,5,3);
        testBoard.movePiece(3,3,5,3);
        assertEquals(testRookWhite,testBoard.pieceAt(3,3));
    }
    @Test
    public void BlackRookCanCaptureEnemyPieces(){
        testBoard.placePiece(testRookWhite,3,3);
        testBoard.placePiece(testRookBlack,5,3);
        testBoard.movePiece(5,3,3,3);
        assertEquals(testRookBlack,testBoard.pieceAt(3,3));
    }
    @Test
    public void BlackRookCannotCaptureAllyPieces(){
        testBoard.placePiece(testRookBlack,3,3);
        testBoard.placePiece(testRookBlack,5,3);
        testBoard.movePiece(5,3,3,3);
        assertEquals(testRookBlack,testBoard.pieceAt(5,3));
    }
}



