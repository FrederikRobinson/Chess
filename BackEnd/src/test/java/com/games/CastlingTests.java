package com.games;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class CastlingTests {
    ChessBoard testBoard;
    ChessPiece testKingWhite;
    ChessPiece testKingBlack;
    ChessPiece testRookWhite;
    ChessPiece testRookBlack;
    ChessPiece testPawnWhite;
    ChessPiece testPawnBlack;
    ChessPiece testBishopWhite;
    ChessPiece testBishopBlack;
    ChessPiece testKnightWhite;
    ChessPiece testKnightBlack;
    ChessPiece testQueenWhite;
    ChessPiece testQueenBlack;

    ChessPiece emptyPiece;
    @BeforeEach
    public void setUp(){
        testBoard = new ChessBoard();
        testKingWhite = testBoard.getPiece('K','W');
        testKingBlack = testBoard.getPiece('K','B');
        testRookWhite = testBoard.getPiece('R','W');
        testRookBlack = testBoard.getPiece('R','B');
        testPawnBlack = testBoard.getPiece('P','B');
        testPawnWhite = testBoard.getPiece('P','W');
        testKnightBlack = testBoard.getPiece('N','B');
        testKnightWhite = testBoard.getPiece('N','W');
        testBishopWhite = testBoard.getPiece('B','W');
        testBishopBlack = testBoard.getPiece('B','B');
        testQueenBlack = testBoard.getPiece('Q','B');
        testQueenWhite = testBoard.getPiece('Q','W');
        emptyPiece = testBoard.getPiece('X','X');
    }
    @Test
    public void CastlingIsPossibleWhiteKingSide(){
        testBoard.placePiece(testKingWhite,4,0);
        testBoard.placePiece(testRookWhite,7,0);
        testBoard.movePiece(4,0,6,0);
        assertTrue(testKingWhite.isEqual(testBoard.pieceAt(6,0)));
        assertTrue(testRookWhite.isEqual(testBoard.pieceAt(5,0)));
    }
}