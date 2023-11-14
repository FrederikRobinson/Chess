package com.games;

public class ChessMove {
    private char playerColour;
    private char promotionType;
    private char pieceColour;

    public char getPlayerColour() {
        return playerColour;
    }

    public void setPlayerColour(char playerColour) {
        this.playerColour = playerColour;
    }

    public char getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(char promotionType) {
        this.promotionType = promotionType;
    }

    public char getPieceColour() {
        return pieceColour;
    }

    public void setPieceColour(char pieceColour) {
        this.pieceColour = pieceColour;
    }

    private int startXPos;
    private int startYPos;
    private int endXPos;
    private int endYPos;

    public int getStartXPos() {
        return startXPos;
    }

    public void setStartXPos(int startXPos) {
        this.startXPos = startXPos;
    }

    public int getStartYPos() {
        return startYPos;
    }

    public void setStartYPos(int startYPos) {
        this.startYPos = startYPos;
    }

    public int getEndXPos() {
        return endXPos;
    }

    public void setEndXPos(int endXPos) {
        this.endXPos = endXPos;
    }

    public int getEndYPos() {
        return endYPos;
    }

    public void setEndYPos(int endYPos) {
        this.endYPos = endYPos;
    }
}
