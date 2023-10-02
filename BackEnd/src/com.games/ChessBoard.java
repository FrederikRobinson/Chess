package com.games;

public class ChessBoard {
    private final ChessPiece[][] BOARD = new ChessPiece[8][8];
    private final ChessPiece EMPTY_PIECE = new ChessPiece('X','X');
    public ChessPiece getEmptyPiece(){
        return EMPTY_PIECE;
    }
    public ChessBoard(){
        setBoard();
    }
    private void setBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                BOARD[i][j]=EMPTY_PIECE;
            }
        }
    }
    public boolean placePiece(ChessPiece piece,int xPos,int yPos){
        if (checkPositionIsOnBoard(xPos,yPos)){
        BOARD[xPos][yPos]=piece;
        return true;
        }
        return false;
    }
    public ChessPiece pieceAt(int xPos,int yPos){
        return BOARD[xPos][yPos];
    }

    public boolean movePiece(int startXPos,int startYPos,int endXPos,int endYPos){
        if(!checkPositionIsOnBoard(startXPos, startYPos) || !checkPositionIsOnBoard(endXPos,endYPos)){
            return false;
        }
//        ChessPiece movingPiece = pieceAt(startXPos,startYPos);
        switch (pieceAt(startXPos,startYPos).getPieceType()) {
            case 'P':
                if (isMoveValidPawn(startXPos, startYPos, endXPos, endYPos)) {
                    performMove(startXPos, startYPos, endXPos, endYPos);
                    return true;
                }
                break;
            case 'R':
                if (isMoveValidRook(startXPos,startYPos,endXPos,endYPos)){
                    performMove(startXPos,startYPos,endXPos,endYPos);
                    return true;
                }
                break;
            case 'B':
                if(isMoveValidBishop(startXPos,startYPos,endXPos,endYPos)){
                    performMove(startXPos,startYPos,endXPos,endYPos);
                    return true;
                }
                break;
            case 'Q':
                if(isMoveValidQueen(startXPos,startYPos,endXPos,endYPos)){
                    performMove(startXPos,startYPos,endXPos,endYPos);
                    return true;
                }
                break;
            case 'K':
                if(isMoveValidKing(startXPos,startYPos,endXPos,endYPos)){
                    performMove(startXPos,startYPos,endXPos,endYPos);
                    return true;
                }
                break;
            case 'N':
                if(isMoveValidKnight(startXPos,startYPos,endXPos,endYPos)){
                    performMove(startXPos,startYPos,endXPos,endYPos);
                    return true;
                }
                break;
        }

        return false;
    }
private boolean isMoveValidKnight(int startXPos,int startYPos,int endXPos,int endYPos){
        if((Math.abs(startXPos-endXPos)==1 && Math.abs(startYPos-endYPos)==2) ||(Math.abs(startXPos-endXPos)==2 && Math.abs(startYPos-endYPos)==1)){
            return (isPositionEnemy(endXPos,endYPos,pieceAt(startXPos,startYPos).getPlayerColour())||isPositionEmpty(endXPos,endYPos));
        }
        return false;
}
    private boolean isMoveValidKing(int startXPos,int startYPos,int endXPos,int endYPos){
        if(Math.abs(startXPos-endXPos)>1 || Math.abs(startYPos-endYPos)>1){
            return false;
        }
        return isMoveValidStraightLine(startXPos,startYPos,endXPos,endYPos);
    }
private boolean isMoveValidQueen(int startXPos,int startYPos,int endXPos,int endYPos){
        if(startXPos!=endXPos && startYPos!=endYPos && Math.abs(startXPos-endXPos)!=Math.abs(startYPos-endYPos)){
            return false;
        }
    return isMoveValidStraightLine(startXPos,startYPos,endXPos,endYPos);
}
    private boolean isMoveValidBishop(int startXPos,int startYPos,int endXPos,int endYPos){
        if (Math.abs(startXPos-endXPos)!=Math.abs(startYPos-endYPos)){
            return false;
        }
        return isMoveValidStraightLine(startXPos,startYPos,endXPos,endYPos);
    }
    private boolean isMoveValidRook(int startXPos,int startYPos,int endXPos,int endYPos){
        if (startXPos!=endXPos && startYPos!=endYPos){
            return false;
        }
        return isMoveValidStraightLine(startXPos,startYPos,endXPos,endYPos);
    }

    private boolean isMoveValidStraightLine(int startXPos,int startYPos,int endXPos,int endYPos){
        int xDirection = (startXPos==endXPos) ? 0 : (startXPos>endXPos) ? -1 : 1;
        int yDirection = (startYPos==endYPos) ? 0 : (startYPos>endYPos) ? -1 : 1;
        int stepMax=Math.max(Math.abs(startXPos-endXPos),Math.abs(startYPos-endYPos));
        for (int stepCount = 1; stepCount <stepMax ; stepCount++) {
            if (!isPositionEmpty(startXPos+(xDirection*stepCount),startYPos+(yDirection*stepCount))){
                return false;
            }
        }
        return (isPositionEmpty(endXPos,endYPos)||isPositionEnemy(endXPos,endYPos,pieceAt(startXPos,startYPos).getPlayerColour()));

    }
    private void performMove(int startXPos,int startYPos,int endXPos,int endYPos) {
        placePiece(pieceAt(startXPos,startYPos),endXPos,endYPos);
        placePiece(EMPTY_PIECE,startXPos,startYPos);
}


    private boolean isMoveValidPawn(int startXPos,int startYPos,int endXPos, int endYPos) {
        switch (pieceAt(startXPos, startYPos).getPlayerColour()) {
            case 'W':
                return isMoveValidPawnColour(startXPos, startYPos, endXPos, endYPos,1);
            case 'B':
                return isMoveValidPawnColour(startXPos, startYPos, endXPos, endYPos,-1);
            default:
                return false;
        }
    }

    private boolean checkPositionIsOnBoard(int xPos,int yPos){
        return (xPos>=0 && yPos>=0 && xPos<8 && yPos<8);
    }
    private boolean isPositionEmpty(int xPos,int yPos){
        return (pieceAt(xPos,yPos).isEqual(EMPTY_PIECE));
    }
    private boolean isPositionEnemy(int xPos,int yPos,char playerColour){
        return pieceAt(xPos,yPos).getPlayerColour()!=playerColour;
    }
    private boolean isPositionEnemy(int xPos,int yPos,int direction){
        if (direction==1){
            return isPositionEnemy(xPos,yPos,'W');
        }
        if (direction==-1){
            return isPositionEnemy(xPos,yPos,'B');
        }
        return true;
    }
    private boolean isMoveValidPawnColour(int startXPos,int startYPos,int endXPos, int endYPos,int direction) {
        if (startXPos == endXPos) {
            return isMoveValidPawnForward(startXPos, startYPos, endXPos, endYPos, direction);
        }
        if (startXPos == endXPos + 1 || startXPos == endXPos - 1) {
            return isMoveValidPawnAttack(startXPos, startYPos, endXPos, endYPos, direction);
        }
        return false;
    }

    private boolean isMoveValidPawnForward(int startXPos,int startYPos,int endXPos, int endYPos,int direction) {
        if (endYPos == startYPos + direction) {
            return isPositionEmpty(endXPos, endYPos);
        }
        if (endYPos == startYPos + 2 * direction && isPawnOnFirstRow(startYPos,direction)) {
            return (isPositionEmpty(endXPos, endYPos) && isPositionEmpty(startXPos, startYPos + direction));
        }
        return false;
    }
    private boolean isPawnOnFirstRow(int yPos,int direction){
        if (direction==1 && yPos==1){
            return true;
        }
        if (direction==-1 && yPos==6){
            return true;
        }
        return false;
    }
    private boolean isMoveValidPawnAttack(int startXPos,int startYPos,int endXPos, int endYPos,int direction) {

    return isPositionEnemy(endXPos,endYPos,direction);}

}
