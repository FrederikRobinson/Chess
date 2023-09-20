package com.games;

public class ChessPiece {
    private final char PIECE_TYPE;
    private final char PLAYER_COLOUR;
    public ChessPiece(char pieceType,char playerColour){
        PIECE_TYPE = pieceType;
        PLAYER_COLOUR = playerColour;
    }
    public char getPieceType(){
        return PIECE_TYPE;
    }
    public char getPlayerColour(){
        return PLAYER_COLOUR;
    }
}
