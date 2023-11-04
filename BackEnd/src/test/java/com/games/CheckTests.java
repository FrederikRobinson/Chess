package com.games;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class CheckTests {
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
    public void AKingOnAnEmptyBoardIsNotCheckInCheck(){
        testBoard.placePiece(testKingWhite,2,2);
        assertFalse(testBoard.isCheck('W'));
    }
    @Test
    public void BlackRookCanCauseWhiteCheck(){
        testBoard.placePiece(testRookBlack,3,3);
        testBoard.placePiece(testKingWhite,3,4);
        assertTrue(testBoard.isCheck('W'));
    }
    @Test
    public void NotInCheckIfNoKingOnBoard(){
        assertFalse(testBoard.isCheck('W'));
        assertFalse(testBoard.isCheck('B'));
    }
    @Test
    public void AlliesCannotCauseCheck(){
        testBoard.placePiece(testKingWhite,0,0);
        testBoard.placePiece(testRookWhite,0,1);
        assertFalse(testBoard.isCheck('W'));
    }
    @Test
    public void CannotMovePieceIfItPutsPlayerIntoCheckWhite(){
        testBoard.placePiece(testKingWhite,0,0);
        testBoard.placePiece(testRookWhite,0,1);
        testBoard.placePiece(testRookBlack,0,2);
        testBoard.movePiece(0,1,1,1);
        assertTrue(testRookWhite.isEqual(testBoard.pieceAt(0,1)));
    }
    @Test
    public void CannotMovePieceIfItPutsPlayerIntoCheckBlack(){
        testBoard.placePiece(testKingBlack,0,0);
        testBoard.placePiece(testRookBlack,0,1);
        testBoard.placePiece(testRookWhite,0,2);
        testBoard.movePiece(0,1,1,1);
        assertTrue(testRookBlack.isEqual(testBoard.pieceAt(0,1)));
    }
    @Test
    public void CannotMovePieceIfItPutsPlayerIntoCheckEnPassantWhite(){
        testBoard.placePiece(testKingWhite,0,0);
        testBoard.placePiece(testRookWhite,0,1);
        testBoard.placePiece(testRookBlack,0,2);
        testBoard.movePiece(0,1,1,1);
        assertTrue(testRookWhite.isEqual(testBoard.pieceAt(0,1)));
    }
    @Test
    public void CannotMoveIfInCheckPawnWhite(){
        testBoard.placePiece(testKingWhite,3,4);
        testBoard.placePiece(testPawnBlack,4,5);
        testBoard.placePiece(testPawnWhite,0,1);
        testBoard.movePiece(0,1,0,2);
        assertTrue(testPawnWhite.isEqual(testBoard.pieceAt(0,1)));

    }
    @Test
    public void CannotMoveIfInCheckPawnBlack(){
        testBoard.placePiece(testKingBlack,4,5);
        testBoard.placePiece(testPawnWhite,3,4);
        testBoard.placePiece(testPawnBlack,0,6);
        testBoard.movePiece(0,6,0,5);
        assertTrue(testPawnBlack.isEqual(testBoard.pieceAt(0,6)));
    }
    @Test
    public void CannotMoveIfInCheckBishopWhite(){
        testBoard.placePiece(testKingWhite,4,5);
        testBoard.placePiece(testBishopBlack,3,4);
        testBoard.placePiece(testPawnWhite,0,1);
        testBoard.movePiece(0,1,0,2);
        assertTrue(testPawnWhite.isEqual(testBoard.pieceAt(0,1)));
    }
    @Test
    public void CannotMoveIfInCheckBishopBlack(){
        testBoard.placePiece(testKingBlack,4,5);
        testBoard.placePiece(testBishopWhite,3,4);
        testBoard.placePiece(testPawnBlack,0,6);
        testBoard.movePiece(0,6,0,5);
        assertTrue(testPawnBlack.isEqual(testBoard.pieceAt(0,6)));
    }
    @Test
    public void CannotMoveIfInCheckRookWhite(){
        testBoard.placePiece(testKingWhite,3,4);
        testBoard.placePiece(testRookBlack,4,4);
        testBoard.placePiece(testPawnWhite,0,1);
        testBoard.movePiece(0,1,0,2);
        assertTrue(testPawnWhite.isEqual(testBoard.pieceAt(0,1)));

    }
    @Test
    public void CannotMoveIfInCheckRookBlack(){
        testBoard.placePiece(testKingBlack,4,5);
        testBoard.placePiece(testRookWhite,4,4);
        testBoard.placePiece(testPawnBlack,0,6);
        testBoard.movePiece(0,6,0,5);
        assertTrue(testPawnBlack.isEqual(testBoard.pieceAt(0,6)));
    }
    @Test
    public void CannotMoveIfInCheckQueenWhite(){
        testBoard.placePiece(testKingWhite,3,4);
        testBoard.placePiece(testQueenBlack,7,4);
        testBoard.placePiece(testPawnWhite,0,1);
        testBoard.movePiece(0,1,0,2);
        assertTrue(testPawnWhite.isEqual(testBoard.pieceAt(0,1)));

    }
    @Test
    public void CannotMoveIfInCheckQueenBlack(){
        testBoard.placePiece(testKingBlack,4,5);
        testBoard.placePiece(testQueenWhite,4,0);
        testBoard.placePiece(testPawnBlack,0,6);
        testBoard.movePiece(0,6,0,5);
        assertTrue(testPawnBlack.isEqual(testBoard.pieceAt(0,6)));
    }
    @Test
    public void CannotMoveIfInCheckKnightWhite(){
        testBoard.placePiece(testKingWhite,3,4);
        testBoard.placePiece(testKnightBlack,4,6);
        testBoard.placePiece(testPawnWhite,0,1);
        testBoard.movePiece(0,1,0,2);
        assertTrue(testPawnWhite.isEqual(testBoard.pieceAt(0,1)));

    }
    @Test
    public void CannotMoveIfInCheckKnightBlack(){
        testBoard.placePiece(testKingBlack,4,5);
        testBoard.placePiece(testKnightWhite,2,4);
        testBoard.placePiece(testPawnBlack,0,6);
        testBoard.movePiece(0,6,0,5);
        assertTrue(testPawnBlack.isEqual(testBoard.pieceAt(0,6)));
    }

    @Test
    public void CannotMoveKingNextToKing(){
        testBoard.placePiece(testKingBlack,4,4);
        testBoard.placePiece(testKingWhite,4,2);
        testBoard.movePiece(4,4,4,3);
        testBoard.movePiece(4,2,4,3);
        assertTrue(testKingBlack.isEqual(testBoard.pieceAt(4,4)));
        assertTrue(testKingWhite.isEqual(testBoard.pieceAt(4,2)));
    }
}
