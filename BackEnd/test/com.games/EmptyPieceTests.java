package com.games;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class EmptyPieceTests {
    ChessBoard testBoard;
    ChessPiece testRookWhite;
    ChessPiece testRookBlack;
    ChessPiece emptyPiece;
    @BeforeEach
    public void setUp(){
        testBoard = new ChessBoard();
        testRookWhite = new ChessPiece('R','W');
        testRookBlack = new ChessPiece('R','B');
        emptyPiece = testBoard.getEmptyPiece();
    }
    @Test
    public void PlaceEmptyPieceWorks() {
        testBoard.placePiece(testRookWhite, 0, 0);
        assertEquals(testRookWhite, testBoard.pieceAt(0, 0));
        testBoard.placePiece(emptyPiece,0,0);
        assertEquals(emptyPiece, testBoard.pieceAt(0, 0));
    }
    @Test
    public void EmptyPieceCannotCaptureWhite(){
        testBoard.placePiece(testRookWhite,5,5);
        testBoard.movePiece(4,4,5,5);
        testBoard.movePiece(4,5,5,5);
        testBoard.movePiece(4,6,5,5);
        testBoard.movePiece(5,4,5,5);
        testBoard.movePiece(5,6,5,5);
        testBoard.movePiece(6,4,5,5);
        testBoard.movePiece(6,5,5,5);
        testBoard.movePiece(6,6,5,5);
        assertEquals(testRookWhite,testBoard.pieceAt(5,5));
    }
    @Test
    public void EmptyPieceCannotCaptureBlack(){
        testBoard.placePiece(testRookBlack,5,5);
        testBoard.movePiece(4,4,5,5);
        testBoard.movePiece(4,5,5,5);
        testBoard.movePiece(4,6,5,5);
        testBoard.movePiece(5,4,5,5);
        testBoard.movePiece(5,6,5,5);
        testBoard.movePiece(6,4,5,5);
        testBoard.movePiece(6,5,5,5);
        testBoard.movePiece(6,6,5,5);
        assertEquals(testRookBlack,testBoard.pieceAt(5,5));
    }
}
