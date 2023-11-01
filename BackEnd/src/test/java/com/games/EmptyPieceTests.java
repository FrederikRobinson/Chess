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
        testRookWhite = testBoard.getPiece('R','W');
        testRookBlack = testBoard.getPiece('R','B');
        emptyPiece = testBoard.getPiece('X','X');
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
        assertTrue(testRookWhite.isEqual(testBoard.pieceAt(5,5)));
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
        assertTrue(testRookBlack.isEqual(testBoard.pieceAt(5,5)));
    }
}
