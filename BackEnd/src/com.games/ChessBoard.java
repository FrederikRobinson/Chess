package com.games;

import java.time.Year;

public class ChessBoard {
    private final ChessPiece[][] BOARD = new ChessPiece[8][8];
    private final ChessPiece EMPTY_PIECE = new ChessPiece('X','X');
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
    public boolean placePiece(ChessPiece piece,int xPos,int yPos){
        if (xPos>=0 && yPos>=0 && xPos<8 && yPos<8){
        BOARD[xPos][yPos]=piece;
        return true;
        }
        return false;
    }
    public ChessPiece pieceAt(int xPos,int yPos){
        return BOARD[xPos][yPos];
    }

    public boolean movePiece(int startXPos,int startYPos,int endXPos,int endYPos){
        ChessPiece movingPiece = pieceAt(startXPos,startYPos);
        switch (movingPiece.getPieceType()) {
            case 'P':
                if (isMoveValidPawn(startXPos, startYPos, endXPos, endYPos)) {
                    performMove(startXPos, startYPos, endXPos, endYPos);
                    return true;
                }
        }

        return false;
    }
    public void performMove(int startXPos,int startYPos,int endXPos,int endYPos) {
        placePiece(pieceAt(startXPos,startYPos),endXPos,endYPos);
        placePiece(EMPTY_PIECE,startXPos,startYPos);
}


    public boolean isMoveValidPawn(int startXPos,int startYPos,int endXPos, int endYPos){
        if (startXPos!=endXPos){
            return false;
        }
        if(pieceAt(startXPos,startYPos).getPlayerColour()=='W') {
            if ((startYPos + 1) == endYPos) {
                return true;
            }
            if (endYPos == 3) {
                return true;
            }
        }
        else if(pieceAt(startXPos,startYPos).getPlayerColour()=='B'){
            if ((startYPos - 1) == endYPos) {
                return true;
            }
            if (endYPos == 4) {
                return true;
            }
        }
        return false;

    }
}
