package com.games;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class KnightMovementTests {



    ChessBoard testBoard;
    ChessPiece testKnightWhite;
    ChessPiece testKnightBlack;
    ChessPiece testRookWhite;
    ChessPiece testRookBlack;
    ChessPiece emptyPiece;
    @BeforeEach
    public void setUp(){
        testBoard = new ChessBoard();
        testKnightWhite = testBoard.getPiece('N','W');
        testKnightBlack = testBoard.getPiece('N','B');
        testRookWhite = testBoard.getPiece('R','W');
        testRookBlack = testBoard.getPiece('R','B');
        emptyPiece = testBoard.getPiece('X','X');
    }

    @Test
    public void WhiteKnightCanMoveNNE(){
        testBoard.placePiece(testKnightWhite,3,3);
        testBoard.movePiece(3,3,4,5);
        assertTrue(testKnightWhite.isEqual(testBoard.pieceAt(4,5)));
    }
    @Test
    public void WhiteKnightCanMoveENE(){
        testBoard.placePiece(testKnightWhite,3,3);
        testBoard.movePiece(3,3,5,4);
        assertTrue(testKnightWhite.isEqual(testBoard.pieceAt(5,4)));
    }
    @Test
    public void WhiteKnightCanMoveESE(){
        testBoard.placePiece(testKnightWhite,3,3);
        testBoard.movePiece(3,3,5,2);
        assertTrue(testKnightWhite.isEqual(testBoard.pieceAt(5,2)));
    }
    @Test
    public void WhiteKnightCanMoveSSE(){
        testBoard.placePiece(testKnightWhite,3,3);
        testBoard.movePiece(3,3,4,1);
        assertTrue(testKnightWhite.isEqual(testBoard.pieceAt(4,1)));
    }
    @Test
    public void WhiteKnightCanMoveSSW(){
        testBoard.placePiece(testKnightWhite,3,3);
        testBoard.movePiece(3,3,2,1);
        assertTrue(testKnightWhite.isEqual(testBoard.pieceAt(2,1)));
    }
    @Test
    public void WhiteKnightCanMoveWSW(){
        testBoard.placePiece(testKnightWhite,3,3);
        testBoard.movePiece(3,3,1,2);
        assertTrue(testKnightWhite.isEqual(testBoard.pieceAt(1,2)));
    }
    @Test
    public void WhiteKnightCanMoveWNW(){
        testBoard.placePiece(testKnightWhite,3,3);
        testBoard.movePiece(3,3,1,4);
        assertTrue(testKnightWhite.isEqual(testBoard.pieceAt(1,4)));
    }
    @Test
    public void WhiteKnightCanMoveNNW(){
        testBoard.placePiece(testKnightWhite,3,3);
        testBoard.movePiece(3,3,2,5);
        assertTrue(testKnightWhite.isEqual(testBoard.pieceAt(2,5)));
    }
    @Test
    public void BlackKnightCanMoveNNE(){
        testBoard.placePiece(testKnightBlack,3,3);
        testBoard.movePiece(3,3,4,5);
        assertTrue(testKnightBlack.isEqual(testBoard.pieceAt(4,5)));
    }
    @Test
    public void BlackKnightCanMoveENE(){
        testBoard.placePiece(testKnightBlack,3,3);
        testBoard.movePiece(3,3,5,4);
        assertTrue(testKnightBlack.isEqual(testBoard.pieceAt(5,4)));
    }
    @Test
    public void BlackKnightCanMoveESE(){
        testBoard.placePiece(testKnightBlack,3,3);
        testBoard.movePiece(3,3,5,2);
        assertTrue(testKnightBlack.isEqual(testBoard.pieceAt(5,2)));
    }
    @Test
    public void BlackKnightCanMoveSSE(){
        testBoard.placePiece(testKnightBlack,3,3);
        testBoard.movePiece(3,3,4,1);
        assertTrue(testKnightBlack.isEqual(testBoard.pieceAt(4,1)));
    }
    @Test
    public void BlackKnightCanMoveSSW(){
        testBoard.placePiece(testKnightBlack,3,3);
        testBoard.movePiece(3,3,2,1);
        assertTrue(testKnightBlack.isEqual(testBoard.pieceAt(2,1)));
    }
    @Test
    public void BlackKnightCanMoveWSW(){
        testBoard.placePiece(testKnightBlack,3,3);
        testBoard.movePiece(3,3,1,2);
        assertTrue(testKnightBlack.isEqual(testBoard.pieceAt(1,2)));
    }
    @Test
    public void BlackKnightCanMoveWNW(){
        testBoard.placePiece(testKnightBlack,3,3);
        testBoard.movePiece(3,3,1,4);
        assertTrue(testKnightBlack.isEqual(testBoard.pieceAt(1,4)));
    }
    @Test
    public void BlackKnightCanMoveNNW(){
        testBoard.placePiece(testKnightBlack,3,3);
        testBoard.movePiece(3,3,2,5);
        assertTrue(testKnightBlack.isEqual(testBoard.pieceAt(2,5)));
    }
    @Test
    public void WhiteKnightCannotTakeAllies(){
        testBoard.placePiece(testKnightWhite,5,4);
        testBoard.placePiece(testRookWhite,3,3);
        testBoard.movePiece(5,4,3,3);
        assertTrue(testRookWhite.isEqual(testBoard.pieceAt(3,3)));
    }
    @Test
    public void BlackKnightCannotTakeAllies(){
        testBoard.placePiece(testKnightBlack,5,4);
        testBoard.placePiece(testRookBlack,3,3);
        testBoard.movePiece(5,4,3,3);
        assertTrue(testRookBlack.isEqual(testBoard.pieceAt(3,3)));
    }
    @Test
    public void WhiteKnightCanTakeEnemies(){
        testBoard.placePiece(testKnightWhite,5,4);
        testBoard.placePiece(testRookBlack,3,3);
        testBoard.movePiece(5,4,3,3);
        assertTrue(testKnightWhite.isEqual(testBoard.pieceAt(3,3)));
    }
    @Test
    public void BlackKnightCanTakeEnemies(){
        testBoard.placePiece(testKnightBlack,5,4);
        testBoard.placePiece(testRookWhite,3,3);
        testBoard.movePiece(5,4,3,3);
        assertTrue(testKnightBlack.isEqual(testBoard.pieceAt(3,3)));
    }
    @Test
    public void WhiteKnightInvalidMoves(){
        testBoard.placePiece(testKnightWhite,3,3);
        testBoard.movePiece(3,3,4,3);
        testBoard.movePiece(3,3,5,7);
        testBoard.movePiece(3,3,3,1);
        testBoard.movePiece(3,3,0,1);
        testBoard.movePiece(3,3,0,7);
        testBoard.movePiece(3,3,7,0);
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(4,3)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(5,7)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(3,1)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(0,1)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(0,7)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(7,0)));
    }
    @Test
    public void BlackKnightInvalidMoves(){
        testBoard.placePiece(testKnightBlack,3,3);
        testBoard.movePiece(3,3,4,3);
        testBoard.movePiece(3,3,5,7);
        testBoard.movePiece(3,3,3,1);
        testBoard.movePiece(3,3,0,1);
        testBoard.movePiece(3,3,0,7);
        testBoard.movePiece(3,3,7,0);
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(4,3)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(5,7)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(3,1)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(0,1)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(0,7)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(7,0)));
    }
    @Test
    public void KnightCannotMoveOffTheBoard(){
        testBoard.placePiece(testKnightWhite,0,0);
        testBoard.movePiece(0,0,-2,-1);
        testBoard.movePiece(0,0,-2,1);
        testBoard.movePiece(0,0,-1,2);
        testBoard.movePiece(0,0,-1,-2);
        assertTrue(testKnightWhite.isEqual(testBoard.pieceAt(0,0)));
    }
}
