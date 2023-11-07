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
    @Test
    public void CastlingIsPossibleBlackKingSide(){
        testBoard.placePiece(testKingBlack,4,7);
        testBoard.placePiece(testRookBlack,7,7);
        testBoard.movePiece(4,7,6,7);
        assertTrue(testKingBlack.isEqual(testBoard.pieceAt(6,7)));
        assertTrue(testRookBlack.isEqual(testBoard.pieceAt(5,7)));
    }
    @Test
    public void CastlingIsPossibleBlackQueenSide(){
        testBoard.placePiece(testKingBlack,4,7);
        testBoard.placePiece(testRookBlack,0,7);
        testBoard.movePiece(4,7,2,7);
        assertTrue(testKingBlack.isEqual(testBoard.pieceAt(2,7)));
        assertTrue(testRookBlack.isEqual(testBoard.pieceAt(3,7)));
    }
    @Test
    public void CastlingIsPossibleWhiteQueenSide(){
        testBoard.placePiece(testKingWhite,4,0);
        testBoard.placePiece(testRookWhite,0,0);
        testBoard.movePiece(4,0,2,0);
        assertTrue(testKingWhite.isEqual(testBoard.pieceAt(2,0)));
        assertTrue(testRookWhite.isEqual(testBoard.pieceAt(3,0)));
    }
    @Test
    public void CastlingIsNotPossibleIfKingMovesWhiteQueenSide(){
        testBoard.placePiece(testKingWhite,4,0);
        testBoard.placePiece(testRookWhite,0,0);
        testBoard.movePiece(4,0,3,0);
        testBoard.movePiece(3,0,4,0);
        testBoard.movePiece(4,0,2,0);
        assertTrue(testKingWhite.isEqual(testBoard.pieceAt(4,0)));
        assertTrue(testRookWhite.isEqual(testBoard.pieceAt(0,0)));
    }
    @Test
    public void CastlingIsNotPossibleIfKingMovesBlackQueenSide(){
        testBoard.placePiece(testKingBlack,4,7);
        testBoard.placePiece(testRookBlack,0,7);
        testBoard.movePiece(4,7,3,7);
        testBoard.movePiece(3,7,4,7);
        testBoard.movePiece(4,7,2,7);
        assertTrue(testKingBlack.isEqual(testBoard.pieceAt(4,7)));
        assertTrue(testRookBlack.isEqual(testBoard.pieceAt(0,7)));
    }
    @Test
    public void CastlingIsNotPossibleIfKingMovesWhiteKingSide(){
        testBoard.placePiece(testKingWhite,4,0);
        testBoard.placePiece(testRookWhite,7,0);
        testBoard.movePiece(4,0,3,0);
        testBoard.movePiece(3,0,4,0);
        testBoard.movePiece(4,0,6,0);
        assertTrue(testKingWhite.isEqual(testBoard.pieceAt(4,0)));
        assertTrue(testRookWhite.isEqual(testBoard.pieceAt(7,0)));
    }
    @Test
    public void CastlingIsNotPossibleIfKingMovesBlackKingSide(){
        testBoard.placePiece(testKingBlack,4,7);
        testBoard.placePiece(testRookBlack,7,7);
        testBoard.movePiece(4,7,3,7);
        testBoard.movePiece(3,7,4,7);
        testBoard.movePiece(4,7,6,7);
        assertTrue(testKingBlack.isEqual(testBoard.pieceAt(4,7)));
        assertTrue(testRookBlack.isEqual(testBoard.pieceAt(7,7)));
    }
    @Test
    public void CastlingIsNotPossibleIfKingInCheckWhiteQueenSide(){
        testBoard.placePiece(testKingWhite,4,0);
        testBoard.placePiece(testRookWhite,0,0);
        testBoard.placePiece(testBishopBlack,3,1);
        testBoard.movePiece(4,0,2,0);
        assertTrue(testKingWhite.isEqual(testBoard.pieceAt(4,0)));
        assertTrue(testRookWhite.isEqual(testBoard.pieceAt(0,0)));
    }
    @Test
    public void CastlingIsNotPossibleIfKingInCheckBlackQueenSide(){
        testBoard.placePiece(testKingBlack,4,7);
        testBoard.placePiece(testRookBlack,0,7);
        testBoard.placePiece(testBishopWhite,3,6);
        testBoard.movePiece(4,7,2,7);
        assertTrue(testKingBlack.isEqual(testBoard.pieceAt(4,7)));
        assertTrue(testRookBlack.isEqual(testBoard.pieceAt(0,7)));
    }
    @Test
    public void CastlingIsNotPossibleIfQueenTileInCheckWhiteQueenSide(){
        testBoard.placePiece(testKingWhite,4,0);
        testBoard.placePiece(testRookWhite,0,0);
        testBoard.placePiece(testBishopBlack,4,1);
        testBoard.movePiece(4,0,2,0);
        assertTrue(testKingWhite.isEqual(testBoard.pieceAt(4,0)));
        assertTrue(testRookWhite.isEqual(testBoard.pieceAt(0,0)));
    }
    @Test
    public void CastlingIsNotPossibleIfQueenTileInCheckBlackQueenSide(){
        testBoard.placePiece(testKingBlack,4,7);
        testBoard.placePiece(testRookBlack,0,7);
        testBoard.placePiece(testBishopWhite,4,6);
        testBoard.movePiece(4,7,2,7);
        assertTrue(testKingBlack.isEqual(testBoard.pieceAt(4,7)));
        assertTrue(testRookBlack.isEqual(testBoard.pieceAt(0,7)));
    }
    @Test
    public void CastlingIsNotPossibleIfBishopTileInCheckWhiteQueenSide(){
        testBoard.placePiece(testKingWhite,4,0);
        testBoard.placePiece(testRookWhite,0,0);
        testBoard.placePiece(testBishopBlack,4,2);
        testBoard.movePiece(4,0,2,0);
        assertTrue(testKingWhite.isEqual(testBoard.pieceAt(4,0)));
        assertTrue(testRookWhite.isEqual(testBoard.pieceAt(0,0)));
    }
    @Test
    public void CastlingIsNotPossibleIfBishopTileInCheckBlackQueenSide(){
        testBoard.placePiece(testKingBlack,4,7);
        testBoard.placePiece(testRookBlack,0,7);
        testBoard.placePiece(testBishopWhite,4,5);
        testBoard.movePiece(4,7,2,7);
        assertTrue(testKingBlack.isEqual(testBoard.pieceAt(4,7)));
        assertTrue(testRookBlack.isEqual(testBoard.pieceAt(0,7)));
    }
    @Test
    public void CastlingIsNotPossibleIfKingInCheckWhiteKingSide(){
        testBoard.placePiece(testKingWhite,4,0);
        testBoard.placePiece(testRookWhite,7,0);
        testBoard.placePiece(testBishopBlack,3,1);
        testBoard.movePiece(4,0,6,0);
        assertTrue(testKingWhite.isEqual(testBoard.pieceAt(4,0)));
        assertTrue(testRookWhite.isEqual(testBoard.pieceAt(7,0)));
    }
    @Test
    public void CastlingIsNotPossibleIfKingInCheckBlackKingSide(){
        testBoard.placePiece(testKingBlack,4,7);
        testBoard.placePiece(testRookBlack,7,7);
        testBoard.placePiece(testBishopWhite,3,6);
        testBoard.movePiece(4,7,6,7);
        assertTrue(testKingBlack.isEqual(testBoard.pieceAt(4,7)));
        assertTrue(testRookBlack.isEqual(testBoard.pieceAt(7,7)));
    }
    @Test
    public void CastlingIsNotPossibleIfBishopTileInCheckWhiteKingSide(){
        testBoard.placePiece(testKingWhite,4,0);
        testBoard.placePiece(testRookWhite,7,0);
        testBoard.placePiece(testBishopBlack,4,1);
        testBoard.movePiece(4,0,6,0);
        assertTrue(testKingWhite.isEqual(testBoard.pieceAt(4,0)));
        assertTrue(testRookWhite.isEqual(testBoard.pieceAt(7,0)));
    }
    @Test
    public void CastlingIsNotPossibleIfBishopTileInCheckBlackKingSide(){
        testBoard.placePiece(testKingBlack,4,7);
        testBoard.placePiece(testRookBlack,7,7);
        testBoard.placePiece(testBishopWhite,4,6);
        testBoard.movePiece(4,7,6,7);
        assertTrue(testKingBlack.isEqual(testBoard.pieceAt(4,7)));
        assertTrue(testRookBlack.isEqual(testBoard.pieceAt(7,7)));
    }
    @Test
    public void CastlingIsNotPossibleIfQueenTileOccupiedWhiteQueenSide(){
        testBoard.placePiece(testKingWhite,4,0);
        testBoard.placePiece(testRookWhite,0,0);
        testBoard.placePiece(testBishopBlack,3,0);
        testBoard.movePiece(4,0,2,0);
        assertTrue(testKingWhite.isEqual(testBoard.pieceAt(4,0)));
        assertTrue(testRookWhite.isEqual(testBoard.pieceAt(0,0)));
    }
    @Test
    public void CastlingIsNotPossibleIfQueenTileOccupiedBlackQueenSide(){
        testBoard.placePiece(testKingBlack,4,7);
        testBoard.placePiece(testRookBlack,0,7);
        testBoard.placePiece(testBishopWhite,3,7);
        testBoard.movePiece(4,7,2,7);
        assertTrue(testKingBlack.isEqual(testBoard.pieceAt(4,7)));
        assertTrue(testRookBlack.isEqual(testBoard.pieceAt(0,7)));
    }
    @Test
    public void CastlingIsNotPossibleIfBishopTileOccupiedWhiteQueenSide(){
        testBoard.placePiece(testKingWhite,4,0);
        testBoard.placePiece(testRookWhite,0,0);
        testBoard.placePiece(testBishopBlack,2,0);
        testBoard.movePiece(4,0,2,0);
        assertTrue(testKingWhite.isEqual(testBoard.pieceAt(4,0)));
        assertTrue(testRookWhite.isEqual(testBoard.pieceAt(0,0)));
    }
    @Test
    public void CastlingIsNotPossibleIfBishopTileOccupiedBlackQueenSide(){
        testBoard.placePiece(testKingBlack,4,7);
        testBoard.placePiece(testRookBlack,0,7);
        testBoard.placePiece(testBishopWhite,2,7);
        testBoard.movePiece(4,7,2,7);
        assertTrue(testKingBlack.isEqual(testBoard.pieceAt(4,7)));
        assertTrue(testRookBlack.isEqual(testBoard.pieceAt(0,7)));
    }

    @Test
    public void CastlingIsNotPossibleIfBishopTileOccupiedWhiteKingSide(){
        testBoard.placePiece(testKingWhite,4,0);
        testBoard.placePiece(testRookWhite,7,0);
        testBoard.placePiece(testBishopBlack,5,0);
        testBoard.movePiece(4,0,6,0);
        assertTrue(testKingWhite.isEqual(testBoard.pieceAt(4,0)));
        assertTrue(testRookWhite.isEqual(testBoard.pieceAt(7,0)));
    }
    @Test
    public void CastlingIsNotPossibleIfBishopTileOccupiedBlackKingSide(){
        testBoard.placePiece(testKingBlack,4,7);
        testBoard.placePiece(testRookBlack,7,7);
        testBoard.placePiece(testBishopWhite,5,7);
        testBoard.movePiece(4,7,6,7);
        assertTrue(testKingBlack.isEqual(testBoard.pieceAt(4,7)));
        assertTrue(testRookBlack.isEqual(testBoard.pieceAt(7,7)));
    }
}