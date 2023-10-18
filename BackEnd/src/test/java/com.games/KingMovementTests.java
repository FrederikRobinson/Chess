package com.games;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class KingMovementTests {
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
        emptyPiece = testBoard.getPiece('X','X');
    }


    @Test
    public void WhiteKingCanMoveNE(){
        testBoard.placePiece(testKingWhite,4,4);
        testBoard.movePiece(4,4,5,5);
        assertEquals(testKingWhite,testBoard.pieceAt(5,5));
    }
    @Test
    public void WhiteKingCanMoveE(){
        testBoard.placePiece(testKingWhite,4,4);
        testBoard.movePiece(4,4,5,4);
        assertEquals(testKingWhite,testBoard.pieceAt(5,4));
    }
    @Test
    public void WhiteKingCanMoveSE(){
        testBoard.placePiece(testKingWhite,4,4);
        testBoard.movePiece(4,4,5,3);
        assertEquals(testKingWhite,testBoard.pieceAt(5,3));
    }
    @Test
    public void WhiteKingCanMoveS(){
        testBoard.placePiece(testKingWhite,4,4);
        testBoard.movePiece(4,4,4,3);
        assertEquals(testKingWhite,testBoard.pieceAt(4,3));
    }
    @Test
    public void WhiteKingCanMoveSW(){
        testBoard.placePiece(testKingWhite,4,4);
        testBoard.movePiece(4,4,3,3);
        assertEquals(testKingWhite,testBoard.pieceAt(3,3));
    }
    @Test
    public void WhiteKingCanMoveW(){
        testBoard.placePiece(testKingWhite,4,4);
        testBoard.movePiece(4,4,3,4);
        assertEquals(testKingWhite,testBoard.pieceAt(3,4));
    }
    @Test
    public void WhiteKingCanMoveNW(){
        testBoard.placePiece(testKingWhite,4,4);
        testBoard.movePiece(4,4,3,5);
        assertEquals(testKingWhite,testBoard.pieceAt(3,5));
    }
    @Test
    public void WhiteKingCanMoveN(){
        testBoard.placePiece(testKingWhite,4,4);
        testBoard.movePiece(4,4,4,5);
        assertEquals(testKingWhite,testBoard.pieceAt(4,5));
    }
    @Test
    public void BlackKingCanMoveNE(){
        testBoard.placePiece(testKingBlack,4,4);
        testBoard.movePiece(4,4,5,5);
        assertEquals(testKingBlack,testBoard.pieceAt(5,5));
    }
    @Test
    public void BlackKingCanMoveE(){
        testBoard.placePiece(testKingBlack,4,4);
        testBoard.movePiece(4,4,5,4);
        assertEquals(testKingBlack,testBoard.pieceAt(5,4));
    }
    @Test
    public void BlackKingCanMoveSE(){
        testBoard.placePiece(testKingBlack,4,4);
        testBoard.movePiece(4,4,5,3);
        assertEquals(testKingBlack,testBoard.pieceAt(5,3));
    }
    @Test
    public void BlackKingCanMoveS(){
        testBoard.placePiece(testKingBlack,4,4);
        testBoard.movePiece(4,4,4,3);
        assertEquals(testKingBlack,testBoard.pieceAt(4,3));
    }
    @Test
    public void BlackKingCanMoveSW(){
        testBoard.placePiece(testKingBlack,4,4);
        testBoard.movePiece(4,4,3,3);
        assertEquals(testKingBlack,testBoard.pieceAt(3,3));
    }
    @Test
    public void BlackKingCanMoveW(){
        testBoard.placePiece(testKingBlack,4,4);
        testBoard.movePiece(4,4,3,4);
        assertEquals(testKingBlack,testBoard.pieceAt(3,4));
    }
    @Test
    public void BlackKingCanMoveNW(){
        testBoard.placePiece(testKingBlack,4,4);
        testBoard.movePiece(4,4,3,5);
        assertEquals(testKingBlack,testBoard.pieceAt(3,5));
    }
    @Test
    public void BlackKingCanMoveN(){
        testBoard.placePiece(testKingBlack,4,4);
        testBoard.movePiece(4,4,4,5);
        assertEquals(testKingBlack,testBoard.pieceAt(4,5));
    }
    @Test
    public void WhiteCanCaptureEnemies(){
        testBoard.placePiece(testKingWhite,2,5);
        testBoard.placePiece(testRookBlack,2,6);
        testBoard.movePiece(2,5,2,6);
        assertEquals(testKingWhite,testBoard.pieceAt(2,6));
    }
    @Test
    public void BlackCanCaptureEnemies(){
        testBoard.placePiece(testKingBlack,2,5);
        testBoard.placePiece(testRookWhite,2,6);
        testBoard.movePiece(2,5,2,6);
        assertEquals(testKingBlack,testBoard.pieceAt(2,6));
    }
    @Test
    public void WhiteCannotCaptureAllies(){
        testBoard.placePiece(testKingWhite,2,5);
        testBoard.placePiece(testRookWhite,2,6);
        testBoard.movePiece(2,5,2,6);
        assertEquals(testRookWhite,testBoard.pieceAt(2,6));
    }
    @Test
    public void BlackCannotCaptureAllies(){
        testBoard.placePiece(testKingBlack,2,5);
        testBoard.placePiece(testRookBlack,2,6);
        testBoard.movePiece(2,5,2,6);
        assertEquals(testRookBlack,testBoard.pieceAt(2,6));
    }
    @Test
    public void WhiteKingInvalidMoves(){
        testBoard.placePiece(testKingWhite,3,3);
        testBoard.movePiece(3,3,4,5);
        testBoard.movePiece(3,3,5,7);
        testBoard.movePiece(3,3,2,1);
        testBoard.movePiece(3,3,0,1);
        testBoard.movePiece(3,3,0,7);
        testBoard.movePiece(3,3,7,0);
        assertEquals(emptyPiece,testBoard.pieceAt(4,5));
        assertEquals(emptyPiece,testBoard.pieceAt(5,7));
        assertEquals(emptyPiece,testBoard.pieceAt(2,1));
        assertEquals(emptyPiece,testBoard.pieceAt(0,1));
        assertEquals(emptyPiece,testBoard.pieceAt(0,7));
        assertEquals(emptyPiece,testBoard.pieceAt(7,0));
    }
    @Test
    public void BlackKingInvalidMoves(){
        testBoard.placePiece(testKingBlack,3,3);
        testBoard.movePiece(3,3,4,5);
        testBoard.movePiece(3,3,5,7);
        testBoard.movePiece(3,3,2,1);
        testBoard.movePiece(3,3,0,1);
        testBoard.movePiece(3,3,0,7);
        testBoard.movePiece(3,3,7,0);
        assertEquals(emptyPiece,testBoard.pieceAt(4,5));
        assertEquals(emptyPiece,testBoard.pieceAt(5,7));
        assertEquals(emptyPiece,testBoard.pieceAt(2,1));
        assertEquals(emptyPiece,testBoard.pieceAt(0,1));
        assertEquals(emptyPiece,testBoard.pieceAt(0,7));
        assertEquals(emptyPiece,testBoard.pieceAt(7,0));
    }
    @Test
    public void KingCannotMoveOffTheBoard(){
        testBoard.placePiece(testKingWhite,0,0);
        testBoard.movePiece(0,0,0,-1);
        testBoard.movePiece(0,0,-1,-1);
        testBoard.movePiece(0,0,-1,0);
        testBoard.movePiece(0,0,1,-1);
        testBoard.movePiece(0,0,-1,1);
        assertEquals(testKingWhite,testBoard.pieceAt(0,0));
    }
}
