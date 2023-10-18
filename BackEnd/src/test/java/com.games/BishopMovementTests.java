package com.games;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BishopMovementTests {

    ChessBoard testBoard;
    ChessPiece testBishopWhite;
    ChessPiece testBishopBlack;
    ChessPiece emptyPiece;
    @BeforeEach
    public void setUp(){
        testBoard = new ChessBoard();
        testBishopWhite = new ChessPiece('B','W');
        testBishopBlack = new ChessPiece('B','B');
        emptyPiece = testBoard.getPiece('X','X');
    }


    @Test
    public void MoveWhiteBishopNEOneSpaceValid(){
        testBoard.placePiece(testBishopWhite,2,2);
        testBoard.movePiece(2,2,3,3);
        assertEquals(testBishopWhite, testBoard.pieceAt(3, 3));

    }
    @Test
    public void MoveWhiteBishopNWOneSpaceValid(){
        testBoard.placePiece(testBishopWhite,2,2);
        testBoard.movePiece(2,2,1,3);
        assertEquals(testBishopWhite, testBoard.pieceAt(1, 3));

    }
    @Test
    public void MoveWhiteBishopSEOneSpaceValid(){
        testBoard.placePiece(testBishopWhite,2,2);
        testBoard.movePiece(2,2,3,1);
        assertEquals(testBishopWhite, testBoard.pieceAt(3, 1));

    }
    @Test
    public void MoveWhiteBishopSWOneSpaceValid(){
        testBoard.placePiece(testBishopWhite,2,2);
        testBoard.movePiece(2,2,1,1);
        assertEquals(testBishopWhite, testBoard.pieceAt(1, 1));

    }
    @Test
    public void MoveBlackBishopSWOneSpaceValid(){
        testBoard.placePiece(testBishopBlack,2,2);
        testBoard.movePiece(2,2,1,1);
        assertEquals(testBishopBlack, testBoard.pieceAt(1, 1));

    }
    @Test
    public void MoveBlackBishopSEOneSpaceValid(){
        testBoard.placePiece(testBishopBlack,2,2);
        testBoard.movePiece(2,2,3,1);
        assertEquals(testBishopBlack, testBoard.pieceAt(3, 1));

    }
    @Test
    public void MoveBlackBishopNWOneSpaceValid(){
        testBoard.placePiece(testBishopBlack,2,2);
        testBoard.movePiece(2,2,1,3);
        assertEquals(testBishopBlack, testBoard.pieceAt(1, 3));

    }
    @Test
    public void MoveBlackBishopNEOneSpaceValid(){
        testBoard.placePiece(testBishopBlack,2,2);
        testBoard.movePiece(2,2,3,3);
        assertEquals(testBishopBlack, testBoard.pieceAt(3, 3));

    }
    @Test
    public void MoveBlackBishopNEMultipleSpacesValid(){
        testBoard.placePiece(testBishopBlack,4,2);
        testBoard.movePiece(4,2,7,5);
        assertEquals(testBishopBlack, testBoard.pieceAt(7, 5));

    }
    @Test
    public void MoveBlackBishopNWMultipleSpacesValid(){
        testBoard.placePiece(testBishopBlack,4,2);
        testBoard.movePiece(4,2,1,5);
        assertEquals(testBishopBlack, testBoard.pieceAt(1, 5));

    }
    @Test
    public void MoveBlackBishopSEMultipleSpacesValid(){
        testBoard.placePiece(testBishopBlack,4,2);
        testBoard.movePiece(4,2,6,0);
        assertEquals(testBishopBlack, testBoard.pieceAt(6, 0));

    }
    @Test
    public void MoveBlackBishopSWMultipleSpacesValid(){
        testBoard.placePiece(testBishopBlack,4,2);
        testBoard.movePiece(4,2,2,0);
        assertEquals(testBishopBlack, testBoard.pieceAt(2, 0));

    }
    @Test
    public void BlackBishopCannotJump(){
        testBoard.placePiece(testBishopBlack,4,2);
        testBoard.placePiece(testBishopBlack,3,1);
        testBoard.movePiece(4,2,2,0);
        assertEquals(emptyPiece, testBoard.pieceAt(2, 0));
    }
    @Test
    public void WhiteBishopCannotJump(){
        testBoard.placePiece(testBishopWhite,4,1);
        testBoard.placePiece(testBishopWhite,6,3);
        testBoard.movePiece(4,1,7,4);
        assertEquals(emptyPiece, testBoard.pieceAt(7, 4));
    }
    @Test
    public void WhiteBishopCannotTakeAllies(){
        testBoard.placePiece(testBishopWhite,4,1);
        testBoard.placePiece(testBishopWhite,6,3);
        testBoard.movePiece(4,1,6,3);
        assertEquals(testBishopWhite, testBoard.pieceAt(4, 1));
    }
    @Test
    public void BlackBishopCannotTakeAllies(){
        testBoard.placePiece(testBishopBlack,4,1);
        testBoard.placePiece(testBishopBlack,6,3);
        testBoard.movePiece(4,1,6,3);
        assertEquals(testBishopBlack, testBoard.pieceAt(4, 1));
    }
    @Test
    public void WhiteBishopCanTakeEnemies(){
        testBoard.placePiece(testBishopWhite,4,1);
        testBoard.placePiece(testBishopBlack,6,3);
        testBoard.movePiece(4,1,6,3);
        assertEquals(testBishopWhite, testBoard.pieceAt(6, 3));
    }
    @Test
    public void BlackBishopCanTakeEnemies(){
        testBoard.placePiece(testBishopBlack,4,1);
        testBoard.placePiece(testBishopWhite,6,3);
        testBoard.movePiece(4,1,6,3);
        assertEquals(testBishopBlack, testBoard.pieceAt(6, 3));
    }

    @Test
    public void WhiteBishopInvalidMoves(){
        testBoard.placePiece(testBishopWhite,3,3);
        testBoard.movePiece(3,3,4,5);
        testBoard.movePiece(3,3,5,3);
        testBoard.movePiece(3,3,2,1);
        testBoard.movePiece(3,3,3,1);
        testBoard.movePiece(3,3,0,7);
        testBoard.movePiece(3,3,7,0);
        assertEquals(emptyPiece,testBoard.pieceAt(4,5));
        assertEquals(emptyPiece,testBoard.pieceAt(5,3));
        assertEquals(emptyPiece,testBoard.pieceAt(2,1));
        assertEquals(emptyPiece,testBoard.pieceAt(3,1));
        assertEquals(emptyPiece,testBoard.pieceAt(0,7));
        assertEquals(emptyPiece,testBoard.pieceAt(7,0));
    }
    @Test
    public void BlackBishopInvalidMoves(){
        testBoard.placePiece(testBishopBlack,3,3);
        testBoard.movePiece(3,3,4,5);
        testBoard.movePiece(3,3,5,3);
        testBoard.movePiece(3,3,2,1);
        testBoard.movePiece(3,3,3,1);
        testBoard.movePiece(3,3,0,7);
        testBoard.movePiece(3,3,7,0);
        assertEquals(emptyPiece,testBoard.pieceAt(4,5));
        assertEquals(emptyPiece,testBoard.pieceAt(5,3));
        assertEquals(emptyPiece,testBoard.pieceAt(2,1));
        assertEquals(emptyPiece,testBoard.pieceAt(3,1));
        assertEquals(emptyPiece,testBoard.pieceAt(0,7));
        assertEquals(emptyPiece,testBoard.pieceAt(7,0));
    }
    @Test
    public void BishopCannotMoveOffTheBoard(){
        testBoard.placePiece(testBishopWhite,0,0);
        testBoard.movePiece(0,0,-1,-1);
        testBoard.movePiece(0,0,-1,1);
        testBoard.movePiece(0,0,1,-1);
        testBoard.movePiece(0,0,8,8);
        testBoard.movePiece(0,0,100,100);
        assertEquals(testBishopWhite,testBoard.pieceAt(0,0));
    }
}
