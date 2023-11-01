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
        testBishopWhite = testBoard.getPiece('B','W');
        testBishopBlack = testBoard.getPiece('B','B');
        emptyPiece = testBoard.getPiece('X','X');
    }


    @Test
    public void MoveWhiteBishopNEOneSpaceValid(){
        testBoard.placePiece(testBishopWhite,2,2);
        testBoard.movePiece(2,2,3,3);
        assertTrue(testBishopWhite.isEqual(testBoard.pieceAt(3, 3)));

    }
    @Test
    public void MoveWhiteBishopNWOneSpaceValid(){
        testBoard.placePiece(testBishopWhite,2,2);
        testBoard.movePiece(2,2,1,3);
        assertTrue(testBishopWhite.isEqual(testBoard.pieceAt(1, 3)));

    }
    @Test
    public void MoveWhiteBishopSEOneSpaceValid(){
        testBoard.placePiece(testBishopWhite,2,2);
        testBoard.movePiece(2,2,3,1);
        assertTrue(testBishopWhite.isEqual(testBoard.pieceAt(3, 1)));

    }
    @Test
    public void MoveWhiteBishopSWOneSpaceValid(){
        testBoard.placePiece(testBishopWhite,2,2);
        testBoard.movePiece(2,2,1,1);
        assertTrue(testBishopWhite.isEqual(testBoard.pieceAt(1, 1)));

    }
    @Test
    public void MoveBlackBishopSWOneSpaceValid(){
        testBoard.placePiece(testBishopBlack,2,2);
        testBoard.movePiece(2,2,1,1);
        assertTrue(testBishopBlack.isEqual(testBoard.pieceAt(1, 1)));

    }
    @Test
    public void MoveBlackBishopSEOneSpaceValid(){
        testBoard.placePiece(testBishopBlack,2,2);
        testBoard.movePiece(2,2,3,1);
        assertTrue(testBishopBlack.isEqual(testBoard.pieceAt(3, 1)));

    }
    @Test
    public void MoveBlackBishopNWOneSpaceValid(){
        testBoard.placePiece(testBishopBlack,2,2);
        testBoard.movePiece(2,2,1,3);
        assertTrue(testBishopBlack.isEqual(testBoard.pieceAt(1, 3)));

    }
    @Test
    public void MoveBlackBishopNEOneSpaceValid(){
        testBoard.placePiece(testBishopBlack,2,2);
        testBoard.movePiece(2,2,3,3);
        assertTrue(testBishopBlack.isEqual(testBoard.pieceAt(3, 3)));

    }
    @Test
    public void MoveBlackBishopNEMultipleSpacesValid(){
        testBoard.placePiece(testBishopBlack,4,2);
        testBoard.movePiece(4,2,7,5);
        assertTrue(testBishopBlack.isEqual(testBoard.pieceAt(7, 5)));

    }
    @Test
    public void MoveBlackBishopNWMultipleSpacesValid(){
        testBoard.placePiece(testBishopBlack,4,2);
        testBoard.movePiece(4,2,1,5);
        assertTrue(testBishopBlack.isEqual(testBoard.pieceAt(1, 5)));

    }
    @Test
    public void MoveBlackBishopSEMultipleSpacesValid(){
        testBoard.placePiece(testBishopBlack,4,2);
        testBoard.movePiece(4,2,6,0);
        assertTrue(testBishopBlack.isEqual(testBoard.pieceAt(6, 0)));

    }
    @Test
    public void MoveBlackBishopSWMultipleSpacesValid(){
        testBoard.placePiece(testBishopBlack,4,2);
        testBoard.movePiece(4,2,2,0);
        assertTrue(testBishopBlack.isEqual(testBoard.pieceAt(2, 0)));

    }
    @Test
    public void BlackBishopCannotJump(){
        testBoard.placePiece(testBishopBlack,4,2);
        testBoard.placePiece(testBishopBlack,3,1);
        testBoard.movePiece(4,2,2,0);
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(2, 0)));
    }
    @Test
    public void WhiteBishopCannotJump(){
        testBoard.placePiece(testBishopWhite,4,1);
        testBoard.placePiece(testBishopWhite,6,3);
        testBoard.movePiece(4,1,7,4);
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(7, 4)));
    }
    @Test
    public void WhiteBishopCannotTakeAllies(){
        testBoard.placePiece(testBishopWhite,4,1);
        testBoard.placePiece(testBishopWhite,6,3);
        testBoard.movePiece(4,1,6,3);
        assertTrue(testBishopWhite.isEqual(testBoard.pieceAt(4, 1)));
    }
    @Test
    public void BlackBishopCannotTakeAllies(){
        testBoard.placePiece(testBishopBlack,4,1);
        testBoard.placePiece(testBishopBlack,6,3);
        testBoard.movePiece(4,1,6,3);
        assertTrue(testBishopBlack.isEqual(testBoard.pieceAt(4, 1)));
    }
    @Test
    public void WhiteBishopCanTakeEnemies(){
        testBoard.placePiece(testBishopWhite,4,1);
        testBoard.placePiece(testBishopBlack,6,3);
        testBoard.movePiece(4,1,6,3);
        assertTrue(testBishopWhite.isEqual(testBoard.pieceAt(6, 3)));
    }
    @Test
    public void BlackBishopCanTakeEnemies(){
        testBoard.placePiece(testBishopBlack,4,1);
        testBoard.placePiece(testBishopWhite,6,3);
        testBoard.movePiece(4,1,6,3);
        assertTrue(testBishopBlack.isEqual(testBoard.pieceAt(6, 3)));
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
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(4,5)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(5,3)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(2,1)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(3,1)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(0,7)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(7,0)));
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
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(4,5)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(5,3)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(2,1)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(3,1)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(0,7)));
        assertTrue(emptyPiece.isEqual(testBoard.pieceAt(7,0)));
    }
    @Test
    public void BishopCannotMoveOffTheBoard(){
        testBoard.placePiece(testBishopWhite,0,0);
        testBoard.movePiece(0,0,-1,-1);
        testBoard.movePiece(0,0,-1,1);
        testBoard.movePiece(0,0,1,-1);
        testBoard.movePiece(0,0,8,8);
        testBoard.movePiece(0,0,100,100);
        assertTrue(testBishopWhite.isEqual(testBoard.pieceAt(0,0)));
    }
}
