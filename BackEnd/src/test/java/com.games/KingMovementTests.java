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
        testKingWhite = testBoard.getPiece('K','W');
        testKingBlack = testBoard.getPiece('K','B');
        testRookWhite = testBoard.getPiece('R','W');
        testRookBlack = testBoard.getPiece('R','B');
        emptyPiece = testBoard.getPiece('X','X');
    }


    @Test
    public void WhiteKingCanMoveNE(){
        testBoard.placePiece(testKingWhite,4,4);
        testBoard.movePiece(4,4,5,5);
        assertTrue(testKingWhite.isEqual(testBoard.pieceAt(5,5)));
    }
    @Test
    public void WhiteKingCanMoveE(){
        testBoard.placePiece(testKingWhite,4,4);
        testBoard.movePiece(4,4,5,4);
        assertTrue(testKingWhite.isEqual(testBoard.pieceAt(5,4)));
    }
    @Test
    public void WhiteKingCanMoveSE(){
        testBoard.placePiece(testKingWhite,4,4);
        testBoard.movePiece(4,4,5,3);
        assertTrue(testKingWhite.isEqual(testBoard.pieceAt(5,3)));
    }
    @Test
    public void WhiteKingCanMoveS(){
        testBoard.placePiece(testKingWhite,4,4);
        testBoard.movePiece(4,4,4,3);
        assertTrue(testKingWhite.isEqual(testBoard.pieceAt(4,3)));
    }
    @Test
    public void WhiteKingCanMoveSW(){
        testBoard.placePiece(testKingWhite,4,4);
        testBoard.movePiece(4,4,3,3);
        assertTrue(testKingWhite.isEqual(testBoard.pieceAt(3,3)));
    }
    @Test
    public void WhiteKingCanMoveW(){
        testBoard.placePiece(testKingWhite,4,4);
        testBoard.movePiece(4,4,3,4);
        assertTrue(testKingWhite.isEqual(testBoard.pieceAt(3,4)));
    }
    @Test
    public void WhiteKingCanMoveNW(){
        testBoard.placePiece(testKingWhite,4,4);
        testBoard.movePiece(4,4,3,5);
        assertTrue(testKingWhite.isEqual(testBoard.pieceAt(3,5)));
    }
    @Test
    public void WhiteKingCanMoveN(){
        testBoard.placePiece(testKingWhite,4,4);
        testBoard.movePiece(4,4,4,5);
        assertTrue(testKingWhite.isEqual(testBoard.pieceAt(4,5)));
    }
    @Test
    public void BlackKingCanMoveNE(){
        testBoard.placePiece(testKingBlack,4,4);
        testBoard.movePiece(4,4,5,5);
        assertTrue(testKingBlack.isEqual(testBoard.pieceAt(5,5)));
    }
    @Test
    public void BlackKingCanMoveE(){
        testBoard.placePiece(testKingBlack,4,4);
        testBoard.movePiece(4,4,5,4);
        assertTrue(testKingBlack.isEqual(testBoard.pieceAt(5,4)));
    }
    @Test
    public void BlackKingCanMoveSE(){
        testBoard.placePiece(testKingBlack,4,4);
        testBoard.movePiece(4,4,5,3);
        assertTrue(testKingBlack.isEqual(testBoard.pieceAt(5,3)));
    }
    @Test
    public void BlackKingCanMoveS(){
        testBoard.placePiece(testKingBlack,4,4);
        testBoard.movePiece(4,4,4,3);
        assertTrue(testKingBlack.isEqual(testBoard.pieceAt(4,3)));
    }
    @Test
    public void BlackKingCanMoveSW(){
        testBoard.placePiece(testKingBlack,4,4);
        testBoard.movePiece(4,4,3,3);
        assertTrue(testKingBlack.isEqual(testBoard.pieceAt(3,3)));
    }
    @Test
    public void BlackKingCanMoveW(){
        testBoard.placePiece(testKingBlack,4,4);
        testBoard.movePiece(4,4,3,4);
        assertTrue(testKingBlack.isEqual(testBoard.pieceAt(3,4)));
    }
    @Test
    public void BlackKingCanMoveNW(){
        testBoard.placePiece(testKingBlack,4,4);
        testBoard.movePiece(4,4,3,5);
        assertTrue(testKingBlack.isEqual(testBoard.pieceAt(3,5)));
    }
    @Test
    public void BlackKingCanMoveN(){
        testBoard.placePiece(testKingBlack,4,4);
        testBoard.movePiece(4,4,4,5);
        assertTrue(testKingBlack.isEqual(testBoard.pieceAt(4,5)));
    }
    @Test
    public void WhiteCanCaptureEnemies(){
        testBoard.placePiece(testKingWhite,2,5);
        testBoard.placePiece(testRookBlack,2,6);
        testBoard.movePiece(2,5,2,6);
        assertTrue(testKingWhite.isEqual(testBoard.pieceAt(2,6)));
    }
    @Test
    public void BlackCanCaptureEnemies(){
        testBoard.placePiece(testKingBlack,2,5);
        testBoard.placePiece(testRookWhite,2,6);
        testBoard.movePiece(2,5,2,6);
        assertTrue(testKingBlack.isEqual(testBoard.pieceAt(2,6)));
    }
    @Test
    public void WhiteCannotCaptureAllies(){
        testBoard.placePiece(testKingWhite,2,5);
        testBoard.placePiece(testRookWhite,2,6);
        testBoard.movePiece(2,5,2,6);
        assertTrue(testRookWhite.isEqual(testBoard.pieceAt(2,6)));
    }
    @Test
    public void BlackCannotCaptureAllies(){
        testBoard.placePiece(testKingBlack,2,5);
        testBoard.placePiece(testRookBlack,2,6);
        testBoard.movePiece(2,5,2,6);
        assertTrue(testRookBlack.isEqual(testBoard.pieceAt(2,6)));
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
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(4,5)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(5,7)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(2,1)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(0,1)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(0,7)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(7,0)));
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
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(4,5)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(5,7)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(2,1)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(0,1)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(0,7)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(7,0)));
    }
    @Test
    public void KingCannotMoveOffTheBoard(){
        testBoard.placePiece(testKingWhite,0,0);
        testBoard.movePiece(0,0,0,-1);
        testBoard.movePiece(0,0,-1,-1);
        testBoard.movePiece(0,0,-1,0);
        testBoard.movePiece(0,0,1,-1);
        testBoard.movePiece(0,0,-1,1);
        assertTrue(testKingWhite.isEqual(testBoard.pieceAt(0,0)));
    }
}
