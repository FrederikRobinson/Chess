package com.games;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class QueenMovementTests {


        

    ChessBoard testBoard;
    ChessPiece testQueenWhite;
    ChessPiece testQueenBlack;
    ChessPiece emptyPiece;
    @BeforeEach
    public void setUp(){
        testBoard = new ChessBoard();
        testQueenWhite = new ChessPiece('Q','W');
        testQueenBlack = new ChessPiece('Q','B');
        emptyPiece = testBoard.getEmptyPiece();
    }


    @Test
    public void MoveWhiteQueenNEOneSpaceValid(){
        testBoard.placePiece(testQueenWhite,2,2);
        testBoard.movePiece(2,2,3,3);
        assertEquals(testQueenWhite, testBoard.pieceAt(3, 3));

    }
    @Test
    public void MoveWhiteQueenNWOneSpaceValid(){
        testBoard.placePiece(testQueenWhite,2,2);
        testBoard.movePiece(2,2,1,3);
        assertEquals(testQueenWhite, testBoard.pieceAt(1, 3));

    }
    @Test
    public void MoveWhiteQueenSEOneSpaceValid(){
        testBoard.placePiece(testQueenWhite,2,2);
        testBoard.movePiece(2,2,3,1);
        assertEquals(testQueenWhite, testBoard.pieceAt(3, 1));

    }
    @Test
    public void MoveWhiteQueenSWOneSpaceValid(){
        testBoard.placePiece(testQueenWhite,2,2);
        testBoard.movePiece(2,2,1,1);
        assertEquals(testQueenWhite, testBoard.pieceAt(1, 1));

    }
    @Test
    public void MoveBlackQueenSWOneSpaceValid(){
        testBoard.placePiece(testQueenBlack,2,2);
        testBoard.movePiece(2,2,1,1);
        assertEquals(testQueenBlack, testBoard.pieceAt(1, 1));

    }
    @Test
    public void MoveBlackQueenSEOneSpaceValid(){
        testBoard.placePiece(testQueenBlack,2,2);
        testBoard.movePiece(2,2,3,1);
        assertEquals(testQueenBlack, testBoard.pieceAt(3, 1));

    }
    @Test
    public void MoveBlackQueenNWOneSpaceValid(){
        testBoard.placePiece(testQueenBlack,2,2);
        testBoard.movePiece(2,2,1,3);
        assertEquals(testQueenBlack, testBoard.pieceAt(1, 3));

    }
    @Test
    public void MoveBlackQueenNEOneSpaceValid(){
        testBoard.placePiece(testQueenBlack,2,2);
        testBoard.movePiece(2,2,3,3);
        assertEquals(testQueenBlack, testBoard.pieceAt(3, 3));

    }
    @Test
    public void MoveBlackQueenNEMultipleSpacesValid(){
        testBoard.placePiece(testQueenBlack,4,2);
        testBoard.movePiece(4,2,7,5);
        assertEquals(testQueenBlack, testBoard.pieceAt(7, 5));

    }
    @Test
    public void MoveBlackQueenNWMultipleSpacesValid(){
        testBoard.placePiece(testQueenBlack,4,2);
        testBoard.movePiece(4,2,1,5);
        assertEquals(testQueenBlack, testBoard.pieceAt(1, 5));

    }
    @Test
    public void MoveBlackQueenSEMultipleSpacesValid(){
        testBoard.placePiece(testQueenBlack,4,2);
        testBoard.movePiece(4,2,6,0);
        assertEquals(testQueenBlack, testBoard.pieceAt(6, 0));

    }
    @Test
    public void MoveBlackQueenSWMultipleSpacesValid(){
        testBoard.placePiece(testQueenBlack,4,2);
        testBoard.movePiece(4,2,2,0);
        assertEquals(testQueenBlack, testBoard.pieceAt(2, 0));

    }
    @Test
    public void BlackQueenCannotJump(){
        testBoard.placePiece(testQueenBlack,4,2);
        testBoard.placePiece(testQueenBlack,3,1);
        testBoard.movePiece(4,2,2,0);
        assertEquals(emptyPiece, testBoard.pieceAt(2, 0));
    }
    @Test
    public void WhiteQueenCannotJump(){
        testBoard.placePiece(testQueenWhite,4,1);
        testBoard.placePiece(testQueenWhite,6,3);
        testBoard.movePiece(4,1,7,4);
        assertEquals(emptyPiece, testBoard.pieceAt(7, 4));
    }
    @Test
    public void WhiteQueenCannotTakeAllies(){
        testBoard.placePiece(testQueenWhite,4,1);
        testBoard.placePiece(testQueenWhite,6,3);
        testBoard.movePiece(4,1,6,3);
        assertEquals(testQueenWhite, testBoard.pieceAt(4, 1));
    }
    @Test
    public void BlackQueenCannotTakeAllies(){
        testBoard.placePiece(testQueenBlack,4,1);
        testBoard.placePiece(testQueenBlack,6,3);
        testBoard.movePiece(4,1,6,3);
        assertEquals(testQueenBlack, testBoard.pieceAt(4, 1));
    }
    @Test
    public void WhiteQueenCanTakeEnemies(){
        testBoard.placePiece(testQueenWhite,4,1);
        testBoard.placePiece(testQueenBlack,6,3);
        testBoard.movePiece(4,1,6,3);
        assertEquals(testQueenWhite, testBoard.pieceAt(6, 3));
    }
    @Test
    public void BlackQueenCanTakeEnemies(){
        testBoard.placePiece(testQueenBlack,4,1);
        testBoard.placePiece(testQueenWhite,6,3);
        testBoard.movePiece(4,1,6,3);
        assertEquals(testQueenBlack, testBoard.pieceAt(6, 3));
    }

    @Test
    public void WhiteQueenMoveUpOneValid(){
        testBoard.placePiece(testQueenWhite,3,3);
        testBoard.movePiece(3,3,3,4);
        assertEquals(testQueenWhite,testBoard.pieceAt(3,4));
    }

    @Test
    public void WhiteQueenMoveUpMultipleValid(){
        testBoard.placePiece(testQueenWhite,3,3);
        testBoard.movePiece(3,3,3,7);
        assertEquals(testQueenWhite,testBoard.pieceAt(3,7));
    }
    @Test
    public void WhiteQueenMoveLeftOneValid(){
        testBoard.placePiece(testQueenWhite,3,3);
        testBoard.movePiece(3,3,2,3);
        assertEquals(testQueenWhite,testBoard.pieceAt(2,3));
    }

    @Test
    public void WhiteQueenMoveLeftMultipleValid(){
        testBoard.placePiece(testQueenWhite,3,3);
        testBoard.movePiece(3,3,0,3);
        assertEquals(testQueenWhite,testBoard.pieceAt(0,3));
    }
    @Test
    public void WhiteQueenMoveDownOneValid(){
        testBoard.placePiece(testQueenWhite,3,3);
        testBoard.movePiece(3,3,3,2);
        assertEquals(testQueenWhite,testBoard.pieceAt(3,2));
    }

    @Test
    public void WhiteQueenMoveDownMultipleValid(){
        testBoard.placePiece(testQueenWhite,3,3);
        testBoard.movePiece(3,3,3,0);
        assertEquals(testQueenWhite,testBoard.pieceAt(3,0));
    }
    @Test
    public void WhiteQueenMoveRightOneValid(){
        testBoard.placePiece(testQueenWhite,3,3);
        testBoard.movePiece(3,3,4,3);
        assertEquals(testQueenWhite,testBoard.pieceAt(4,3));
    }

    @Test
    public void WhiteQueenMoveRightMultipleValid(){
        testBoard.placePiece(testQueenWhite,3,3);
        testBoard.movePiece(3,3,7,3);
        assertEquals(testQueenWhite,testBoard.pieceAt(7,3));
    }
    @Test
    public void BlackQueenValidMoves(){
        testBoard.placePiece(testQueenBlack,3,3);
        testBoard.movePiece(3,3,5,3);
        assertEquals(testQueenBlack,testBoard.pieceAt(5,3));
        testBoard.movePiece(5,3,5,6);
        assertEquals(testQueenBlack,testBoard.pieceAt(5,6));
        testBoard.movePiece(5,6,2,6);
        assertEquals(testQueenBlack,testBoard.pieceAt(2,6));
        testBoard.movePiece(2,6,2,0);
        assertEquals(testQueenBlack,testBoard.pieceAt(2,0));

    }
    @Test
    public void WhiteQueenInvalidMoves(){
        testBoard.placePiece(testQueenWhite,3,3);
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
    public void BlackQueenInvalidMoves(){
        testBoard.placePiece(testQueenBlack,3,3);
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
    public void WhiteQueenCannotJumpSouth(){
        testBoard.placePiece(testQueenWhite,6,6);
        testBoard.placePiece(testQueenWhite,6,5);
        testBoard.movePiece(6,6,6,4);
        assertEquals(emptyPiece,testBoard.pieceAt(6,4));

    }
    @Test
    public void BlackQueenCannotJumpNorth(){
        testBoard.placePiece(testQueenWhite,0,0);
        testBoard.placePiece(testQueenWhite,0,5);
        testBoard.movePiece(0,0,0,7);
        assertEquals(emptyPiece,testBoard.pieceAt(0,7));

    }
    @Test
    public void WhiteQueenCanCaptureEnemyPieces(){
        testBoard.placePiece(testQueenWhite,3,3);
        testBoard.placePiece(testQueenBlack,5,3);
        testBoard.movePiece(3,3,5,3);
        assertEquals(testQueenWhite,testBoard.pieceAt(5,3));
    }
    @Test
    public void WhiteQueenCannotCaptureAllyPieces(){
        testBoard.placePiece(testQueenWhite,3,3);
        testBoard.placePiece(testQueenWhite,5,3);
        testBoard.movePiece(3,3,5,3);
        assertEquals(testQueenWhite,testBoard.pieceAt(3,3));
    }
    @Test
    public void BlackQueenCanCaptureEnemyPieces(){
        testBoard.placePiece(testQueenWhite,3,3);
        testBoard.placePiece(testQueenBlack,5,3);
        testBoard.movePiece(5,3,3,3);
        assertEquals(testQueenBlack,testBoard.pieceAt(3,3));
    }
    @Test
    public void BlackQueenCannotCaptureAllyPieces(){
        testBoard.placePiece(testQueenBlack,3,3);
        testBoard.placePiece(testQueenBlack,5,3);
        testBoard.movePiece(5,3,3,3);
        assertEquals(testQueenBlack,testBoard.pieceAt(5,3));
    }
    @Test
    public void QueenCannotMoveOffTheBoard(){
        testBoard.placePiece(testQueenWhite,0,0);
        testBoard.movePiece(0,0,-1,0);
        testBoard.movePiece(0,0,0,-1);
        testBoard.movePiece(0,0,8,0);
        testBoard.movePiece(0,0,0,8);
        testBoard.movePiece(0,0,-1,-1);
        testBoard.movePiece(0,0,8,8);
        testBoard.movePiece(0,0,-1,1);
        testBoard.movePiece(0,0,1,-1);
        assertEquals(testQueenWhite,testBoard.pieceAt(0,0));
    }
}
