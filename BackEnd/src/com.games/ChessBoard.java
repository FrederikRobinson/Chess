package com.games;

public class ChessBoard {
    private final ChessPiece[][] BOARD = new ChessPiece[8][8];
    private final ChessPiece EMPTY_PIECE = new ChessPiece('X','X');
    private final ChessPiece WHITE_KING = new ChessPiece('K','W');
    private final ChessPiece BLACK_KING = new ChessPiece('K','B');
    private final ChessPiece WHITE_ROOK = new ChessPiece('R','W');
    private final ChessPiece BLACK_ROOK = new ChessPiece('R','B');
    private final ChessPiece WHITE_QUEEN = new ChessPiece('Q','W');
    private final ChessPiece BLACK_QUEEN = new ChessPiece('Q','B');
    private final ChessPiece WHITE_PAWN = new ChessPiece('P','W');
    private final ChessPiece BLACK_PAWN = new ChessPiece('P','B');
    private final ChessPiece WHITE_BISHOP = new ChessPiece('B','W');
    private final ChessPiece BLACK_BISHOP = new ChessPiece('B','B');
    private final ChessPiece WHITE_KNIGHT = new ChessPiece('N','W');
    private final ChessPiece BLACK_KNIGHT = new ChessPiece('N','B');

    private final char WHITE = 'W';
    private final char BLACK = 'B';
    private final char ROOK = 'R';
    private final char KNIGHT = 'N';
    private final char KING = 'K';
    private final char QUEEN = 'Q';
    private final char PAWN = 'P';
    private final char BISHOP = 'B';
    private final int BOARD_SIZE_X = 8;
    private final int BOARD_SIZE_Y = 8;
    public ChessPiece getEmptyPiece(){
        return EMPTY_PIECE;
    }
    public ChessBoard(){
        setBoard();
    }
    private void setBoard(){
        for (int x = 0; x < BOARD_SIZE_X; x++) {
            for (int y = 0; y < BOARD_SIZE_Y; y++) {
                BOARD[x][y]=EMPTY_PIECE;
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

    public void movePiece(int startXPos,int startYPos,int endXPos,int endYPos){
        if (isMoveValid(startXPos,startYPos,endXPos,endYPos)){
            performMove(startXPos, startYPos, endXPos, endYPos);
        }
    }

    public boolean isMoveValid(int startXPos,int startYPos,int endXPos,int endYPos){
        if(!checkPositionIsOnBoard(startXPos, startYPos) || !checkPositionIsOnBoard(endXPos,endYPos)){
            return false;
        }
        switch (pieceAt(startXPos,startYPos).getPieceType()) {
            case PAWN:
                return isMoveValidPawn(startXPos, startYPos, endXPos, endYPos);
            case ROOK:
                return isMoveValidRook(startXPos,startYPos,endXPos,endYPos);
            case BISHOP:
                return isMoveValidBishop(startXPos,startYPos,endXPos,endYPos);
            case QUEEN:
                return isMoveValidQueen(startXPos,startYPos,endXPos,endYPos);
            case KING:
                return isMoveValidKing(startXPos,startYPos,endXPos,endYPos);
            case KNIGHT:
                return isMoveValidKnight(startXPos,startYPos,endXPos,endYPos);
            default:
                return false;
        }
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
            case WHITE:
                return isMoveValidPawnColour(startXPos, startYPos, endXPos, endYPos,1);
            case BLACK:
                return isMoveValidPawnColour(startXPos, startYPos, endXPos, endYPos,-1);
            default:
                return false;
        }
    }

    private boolean checkPositionIsOnBoard(int xPos,int yPos){
        return (xPos>=0 && yPos>=0 && xPos<BOARD_SIZE_X && yPos<BOARD_SIZE_Y);
    }
    private boolean isPositionEmpty(int xPos,int yPos){
        return (pieceAt(xPos,yPos).isEqual(EMPTY_PIECE));
    }
    private boolean isPositionEnemy(int xPos,int yPos,char playerColour){
        return pieceAt(xPos,yPos).getPlayerColour()!=playerColour;
    }
    private boolean isPositionEnemy(int xPos,int yPos,int direction){
        if (direction==1){
            return isPositionEnemy(xPos,yPos,WHITE);
        }
        if (direction==-1){
            return isPositionEnemy(xPos,yPos,BLACK);
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
        if (direction==-1 && yPos==BOARD_SIZE_Y-2){
            return true;
        }
        return false;
    }
    private boolean isMoveValidPawnAttack(int startXPos,int startYPos,int endXPos, int endYPos,int direction) {

    return isPositionEnemy(endXPos,endYPos,direction);}
public boolean isCheck(char playerColour){
        int[] kingLocation= getKingLocation(playerColour);
        if (kingLocation[0]==-1) return false;
    for (int xPos = 0; xPos < BOARD_SIZE_X; xPos++) {
        for (int yPos = 0; yPos < BOARD_SIZE_Y; yPos++) {
            if(isPositionEnemy(xPos,yPos,playerColour) && isMoveValid(xPos,yPos,kingLocation[0],kingLocation[1])){
                return true;
            }
        }
    }
    return false;
}
private int[] getKingLocation(char playerColour){
    for (int xPos = 0; xPos < BOARD_SIZE_X; xPos++) {
        for (int yPos = 0; yPos < BOARD_SIZE_Y; yPos++) {
            if(checkIfKing(xPos,yPos,playerColour)){
                return new int[] {xPos,yPos};
            }
        }
    }
    return new int[] {-1,-1};
}

private boolean checkIfKing(int xPos,int yPos, char playerColour){
        return pieceAt(xPos,yPos).isEqual(getPiece(KING,playerColour));
}
private ChessPiece getPiece(char pieceType,char playerColour){
        switch (pieceType){
            case PAWN: return playerColour==WHITE ? WHITE_PAWN:BLACK_PAWN;
            case QUEEN: return playerColour==WHITE ? WHITE_QUEEN:BLACK_QUEEN;
            case KING: return playerColour==WHITE ? WHITE_KING:BLACK_KING;
            case ROOK: return playerColour==WHITE ? WHITE_ROOK:BLACK_ROOK;
            case KNIGHT: return playerColour==WHITE ? WHITE_KNIGHT:BLACK_KNIGHT;
            case BISHOP: return playerColour==WHITE ? WHITE_BISHOP:BLACK_BISHOP;
            default: return EMPTY_PIECE;
        }
}
}
