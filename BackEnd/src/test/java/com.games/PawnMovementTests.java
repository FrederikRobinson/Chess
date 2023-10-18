package com.games;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PawnMovementTests {
    ChessBoard testBoard;
    ChessPiece testPawnWhite;
    ChessPiece testPawnBlack;
    ChessPiece emptyPiece;
    @BeforeEach
    public void setUp(){
        testBoard = new ChessBoard();
        testPawnWhite = new ChessPiece('P','W');
        testPawnBlack = new ChessPiece('P','B');
        emptyPiece = testBoard.getPiece('X','X');
    }


    @Test
    public void MovingWhitePawnOneSpaceUpIsValid(){
        testBoard.placePiece(testPawnWhite,0,1);
        testBoard.movePiece(0,1,0,2);
        assertEquals(testPawnWhite,testBoard.pieceAt(0,2));
    }
    @Test
    public void MovingWhitePawnTwoSpaceUpFromStartIsValid(){
        testBoard.placePiece(testPawnWhite,0,1);
        testBoard.movePiece(0,1,0,3);
        assertEquals(testPawnWhite,testBoard.pieceAt(0,3));
    }
    @Test
    public void MovingWhitePawnOneSpaceDownIsInvalid(){
        testBoard.placePiece(testPawnWhite,0,1);
        testBoard.movePiece(0,1,0,0);
        assertNotEquals(testPawnWhite,testBoard.pieceAt(0,0));
    }
    @Test
    public void MovingWhitePawnTwoSpaceUpElsewhereIsInvalid(){
        testBoard.placePiece(testPawnWhite,0,2);
        testBoard.movePiece(0,2,0,4);
        assertNotEquals(testPawnWhite,testBoard.pieceAt(0,4));
    }
    @Test
    public void MovingBlackPawnOneSpaceDownIsValid(){
        testBoard.placePiece(testPawnBlack,0,6);
        testBoard.movePiece(0,6,0,5);
        assertEquals(testPawnBlack,testBoard.pieceAt(0,5));
    }
    @Test
    public void MovingBlackPawnTwoSpaceDownFromStartIsValid(){
        testBoard.placePiece(testPawnBlack,0,6);
        testBoard.movePiece(0,6,0,4);
        assertEquals(testPawnBlack,testBoard.pieceAt(0,4));
    }
    @Test
    public void MovingBlackPawnOneSpaceUpIsInvalid(){
        testBoard.placePiece(testPawnBlack,0,6);
        testBoard.movePiece(0,6,0,7);
        assertNotEquals(testPawnBlack,testBoard.pieceAt(0,7));
    }
    @Test
    public void MovingBlackPawnTwoSpaceDownElsewhereIsInvalid(){
        testBoard.placePiece(testPawnBlack,0,5);
        testBoard.movePiece(0,5,0,3);
        assertNotEquals(testPawnBlack,testBoard.pieceAt(0,3));
    }


    @Test
    public void PieceIsNoLongerAtStartingPositionAfterMove(){
        testBoard.placePiece(testPawnWhite,0,1);
        testBoard.movePiece(0,1,0,2);
        assertNotEquals(testPawnWhite,testBoard.pieceAt(0,1));
    }

    @Test
    public void CaptureWorksForPawnDiagonalLeftForWhite(){
        testBoard.placePiece(testPawnWhite,2,3);
        testBoard.placePiece(testPawnBlack,3,4);
        testBoard.movePiece(2,3,3,4);
        assertEquals(testPawnWhite,testBoard.pieceAt(3,4));
    }
    @Test
    public void CaptureWorksForPawnDiagonalRightForWhite(){
        testBoard.placePiece(testPawnWhite,2,3);
        testBoard.placePiece(testPawnBlack,1,4);
        testBoard.movePiece(2,3,1,4);
        assertEquals(testPawnWhite,testBoard.pieceAt(1,4));
    }
    @Test
    public void CaptureWorksForPawnDiagonalLeftForBlack(){
        testBoard.placePiece(testPawnWhite,2,3);
        testBoard.placePiece(testPawnBlack,3,4);
        testBoard.movePiece(3,4,2,3);
        assertEquals(testPawnBlack,testBoard.pieceAt(2,3));
    }
    @Test
    public void CaptureWorksForPawnDiagonalRightForBlack(){
        testBoard.placePiece(testPawnWhite,2,3);
        testBoard.placePiece(testPawnBlack,1,4);
        testBoard.movePiece(1,4,2,3);
        assertEquals(testPawnBlack,testBoard.pieceAt(2,3));
    }

    @Test
    public void MovingWhitePawnForwardOnTopOfOtherPieceInvalid(){
        testBoard.placePiece(testPawnWhite,2,3);
        testBoard.placePiece(testPawnBlack,2,4);
        testBoard.movePiece(2,3,2,4);
        assertEquals(testPawnBlack,testBoard.pieceAt(2,4));
    }
    @Test
    public void MovingBlackPawnForwardOnTopOfOtherPieceInvalid(){
        testBoard.placePiece(testPawnWhite,2,3);
        testBoard.placePiece(testPawnBlack,2,4);
        testBoard.movePiece(2,4,2,3);
        assertEquals(testPawnWhite,testBoard.pieceAt(2,3));
    }
    @Test
    public void MovingWhitePawnForwardTwoOnTopOfOtherPieceInvalid(){
        testBoard.placePiece(testPawnWhite,2,3);
        testBoard.placePiece(testPawnBlack,2,5);
        testBoard.movePiece(2,3,2,5);
        assertEquals(testPawnBlack,testBoard.pieceAt(2,5));
    }
    @Test
    public void MovingBlackPawnForwardTwoOnTopOfOtherPieceInvalid(){
        testBoard.placePiece(testPawnWhite,2,3);
        testBoard.placePiece(testPawnBlack,2,5);
        testBoard.movePiece(2,5,2,3);
        assertEquals(testPawnWhite,testBoard.pieceAt(2,3));
    }
    @Test
    public void MovingWhitePawnForwardTwoOverOtherPieceInvalid(){
        testBoard.placePiece(testPawnWhite,2,3);
        testBoard.placePiece(testPawnBlack,2,4);
        testBoard.movePiece(2,3,2,5);
        assertEquals(testBoard.getPiece('X','X'),testBoard.pieceAt(2,5));
    }
    @Test
    public void MovingBlackPawnForwardTwoOnOverOtherPieceInvalid(){
        testBoard.placePiece(testPawnWhite,2,4);
        testBoard.placePiece(testPawnBlack,2,5);
        testBoard.movePiece(2,5,2,3);
        assertEquals(testBoard.getPiece('X','X'),testBoard.pieceAt(2,3));
    }

    @Test
    public void WhitePawnCannotMoveTwoSpacesSideways(){
        testBoard.placePiece(testPawnWhite,3,3);
        testBoard.movePiece(3,3,5,4);
        assertEquals(testPawnWhite,testBoard.pieceAt(3,3));
    }
    @Test
    public void BlackPawnCannotMoveTwoSpacesSideways(){
        testBoard.placePiece(testPawnBlack,3,3);
        testBoard.movePiece(3,3,1,2);
        assertEquals(testPawnBlack,testBoard.pieceAt(3,3));
    }
    @Test
    public void WhitePawnCannotMoveOffTheBoard(){
        testBoard.placePiece(testPawnWhite,0,7);
        testBoard.movePiece(0,7,0,8);
        testBoard.movePiece(0,7,0,9);
        testBoard.movePiece(0,7,-1,8);
        testBoard.movePiece(0,7,1,8);
        assertEquals(testPawnWhite,testBoard.pieceAt(0,7));
    }
    @Test
    public void BlackPawnCannotMoveOffTheBoard(){
        testBoard.placePiece(testPawnBlack,0,0);
        testBoard.movePiece(0,0,0,-1);
        testBoard.movePiece(0,0,0,-2);
        testBoard.movePiece(0,0,-1,-1);
        testBoard.movePiece(0,0,1,-1);
        assertEquals(testPawnBlack,testBoard.pieceAt(0,0));
    }
    @Test
    public void EnPassantIsValidForWhiteFromLeft(){
        testBoard.placePiece(testPawnBlack,4,6);
        testBoard.placePiece(testPawnWhite,3,4);
        testBoard.movePiece(4,6,4,4);
        testBoard.movePiece(3,4,4,5);
        assertEquals(testPawnWhite,testBoard.pieceAt(4,5));
        assertEquals(emptyPiece,testBoard.pieceAt(4,4));

    }
    @Test
    public void EnPassantIsValidForWhiteFromRight(){
        testBoard.placePiece(testPawnBlack,4,6);
        testBoard.placePiece(testPawnWhite,5,4);
        testBoard.movePiece(4,6,4,4);
        testBoard.movePiece(5,4,4,5);
        assertEquals(testPawnWhite,testBoard.pieceAt(4,5));
        assertEquals(emptyPiece,testBoard.pieceAt(4,4));

    }
    @Test
    public void EnPassantIsValidForBlackFromRight(){
        testBoard.placePiece(testPawnBlack,4,3);
        testBoard.placePiece(testPawnWhite,3,1);
        testBoard.movePiece(3,1,3,3);
        testBoard.movePiece(4,3,3,2);
        assertEquals(testPawnBlack,testBoard.pieceAt(3,2));
        assertEquals(emptyPiece,testBoard.pieceAt(3,3));

    }
    @Test
    public void EnPassantIsValidForBlackFromLeft(){
        testBoard.placePiece(testPawnBlack,2,3);
        testBoard.placePiece(testPawnWhite,3,1);
        testBoard.movePiece(3,1,3,3);
        testBoard.movePiece(2,3,3,2);
        assertEquals(testPawnBlack,testBoard.pieceAt(3,2));
        assertEquals(emptyPiece,testBoard.pieceAt(3,3));

    }
    @Test
    public void EnPassantPossibleForASingleStepWhite(){
        testBoard.placePiece(testPawnBlack,4,5);
        testBoard.placePiece(testPawnWhite,5,4);
        testBoard.movePiece(4,5,4,4);
        testBoard.movePiece(5,4,4,5);
        assertEquals(testPawnWhite,testBoard.pieceAt(5,4));
        assertEquals(testPawnBlack,testBoard.pieceAt(4,4));

    }
    @Test
    public void EnPassantPossibleForASingleStepBlack(){
        testBoard.placePiece(testPawnBlack,4,3);
        testBoard.placePiece(testPawnWhite,5,2);
        testBoard.movePiece(5,2,5,3);
        testBoard.movePiece(4,2,5,3);
        assertEquals(testPawnWhite,testBoard.pieceAt(5,3));
        assertEquals(testPawnBlack,testBoard.pieceAt(4,2));
    }
    @Test
    public void EnPassantIsInvalidAfterATurnForWhite(){
        testBoard.placePiece(testPawnBlack,4,6);
        testBoard.placePiece(testPawnWhite,5,4);
        testBoard.movePiece(4,6,4,4);
        testBoard.movePiece(5,4,4,5);
        assertEquals(testPawnWhite,testBoard.pieceAt(4,5));
        assertEquals(emptyPiece,testBoard.pieceAt(4,4));

    }
}