package com.games;

import java.time.Year;

public class ChessBoard {
    private final ChessPiece[][] BOARD = new ChessPiece[8][8];
    public ChessBoard(){
        setBoard();
    }
    private void setBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                BOARD[i][j]=new ChessPiece('X','X');
            }
        }
    }
    public void placePiece(ChessPiece piece,int xPos,int yPos){
        BOARD[xPos][yPos]=piece;
    }
    public ChessPiece pieceAt(int xPos,int yPos){
        return BOARD[xPos][yPos];
    }
}
