package com.games;

public class ChessGame {
    private char playerTurn;


    private final ChessBoard gameBoard;

    public ChessGame(){
        playerTurn='W';
        gameBoard=new ChessBoard();
    }
    public void nextTurn(){
        playerTurn=playerTurn=='W'?'B':'W';
    }
    public char getPlayerTurn(){
        return playerTurn;
    }
    public boolean makeMove(int startXPos,int startYPos, int endXPos, int endYPos){
        if (gameBoard.movePiece(startXPos,startYPos,endXPos,endYPos)){
            nextTurn();
            return true;
        }
        return false;
    }
}
