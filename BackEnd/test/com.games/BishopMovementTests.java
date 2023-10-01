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
        emptyPiece = testBoard.getEmptyPiece();
    }
    @Test
    public void PlaceBishopWorks() {
        testBoard.placePiece(testBishopWhite, 0, 0);
        assertEquals(testBishopWhite, testBoard.pieceAt(0, 0));
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
    public void WhiteCannotTakeAllies(){
        testBoard.placePiece(testBishopWhite,4,1);
        testBoard.placePiece(testBishopWhite,6,3);
        testBoard.movePiece(4,1,6,3);
        assertEquals(testBishopWhite, testBoard.pieceAt(4, 1));
    }
    @Test
    public void BlackCannotTakeAllies(){
        testBoard.placePiece(testBishopBlack,4,1);
        testBoard.placePiece(testBishopBlack,6,3);
        testBoard.movePiece(4,1,6,3);
        assertEquals(testBishopBlack, testBoard.pieceAt(4, 1));
    }
    @Test
    public void WhiteCanTakeEnemies(){
        testBoard.placePiece(testBishopWhite,4,1);
        testBoard.placePiece(testBishopBlack,6,3);
        testBoard.movePiece(4,1,6,3);
        assertEquals(testBishopWhite, testBoard.pieceAt(6, 3));
    }
    @Test
    public void BlackCanTakeEnemies(){
        testBoard.placePiece(testBishopBlack,4,1);
        testBoard.placePiece(testBishopWhite,6,3);
        testBoard.movePiece(4,1,6,3);
        assertEquals(testBishopBlack, testBoard.pieceAt(6, 3));
    }
}