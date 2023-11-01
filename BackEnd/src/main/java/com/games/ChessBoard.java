package com.games;

import java.util.Arrays;

public class ChessBoard {
    private final char WHITE = 'W';
    private final char BLACK = 'B';
    private final char ROOK = 'R';
    private final char KNIGHT = 'N';
    private final char KING = 'K';
    private final char QUEEN = 'Q';
    private final char PAWN = 'P';
    private final char BISHOP = 'B';
    private final char EMPTY = 'X';
    private final char STALEMATE = 'S';

    private final char[] PIECE_ORDER = {ROOK,KNIGHT,BISHOP,QUEEN,KING,BISHOP,KNIGHT,ROOK};
    private final ChessPiece[][] BOARD = new ChessPiece[8][8];
    private final ChessPiece EMPTY_PIECE = new ChessPiece(EMPTY,EMPTY);
    private final ChessPiece WHITE_KING = new ChessPiece(KING,WHITE);
    private final ChessPiece BLACK_KING = new ChessPiece(KING,BLACK);
    private final ChessPiece WHITE_ROOK = new ChessPiece(ROOK,WHITE);
    private final ChessPiece BLACK_ROOK = new ChessPiece(ROOK,BLACK);
    private final ChessPiece WHITE_QUEEN = new ChessPiece(QUEEN,WHITE);
    private final ChessPiece BLACK_QUEEN = new ChessPiece(QUEEN,BLACK);
    private final ChessPiece WHITE_PAWN = new ChessPiece(PAWN,WHITE);
    private final ChessPiece BLACK_PAWN = new ChessPiece(PAWN,BLACK);
    private final ChessPiece WHITE_BISHOP = new ChessPiece(BISHOP,WHITE);
    private final ChessPiece BLACK_BISHOP = new ChessPiece(BISHOP,BLACK);
    private final ChessPiece WHITE_KNIGHT = new ChessPiece(KNIGHT,WHITE);
    private final ChessPiece BLACK_KNIGHT = new ChessPiece(KNIGHT,BLACK);

    private char currentPlayer;


    private final int BOARD_SIZE_X = 8;
    private final int BOARD_SIZE_Y = 8;

    private final boolean[] enPassant = new boolean[BOARD_SIZE_X];
    private boolean kingUnmovedWhite=true;
    private boolean kingUnmovedBlack=true;
    private final boolean[][] rookUnmoved={{true,true},{true,true}};
    public ChessBoard(){
        this(0);
    }
    public ChessBoard(int boardCode){
        switch (boardCode){
            case 0 -> setEmptyBoard();
            default -> setBoard();
        }
        setCurrentPlayer(WHITE);
    }
    public ChessPiece[][] getBoard(){
        return BOARD;
    }
    public char isGameOver(){
        if (isStalemate(currentPlayer)){
            return STALEMATE;
        }
        if (isCheckmate(currentPlayer)){
            return getOtherPlayer(currentPlayer);
        }
        return EMPTY;
    }
    public boolean takeTurn(char playerColour,int startXPos, int startYPos,int endXPos, int endYPos){
        System.out.println(playerColour);
        System.out.println(currentPlayer);
        if (playerColour==currentPlayer&&movePiece(startXPos,startYPos,endXPos,endYPos)){
           nextTurn();
           return true;
        }
        return false;
    }
    public char getCurrentPlayer(){
        return currentPlayer;
    }
    private void setCurrentPlayer(char player){
        currentPlayer=player;
    }
    private char getOtherPlayer(char player){
        return player==WHITE?BLACK:player==BLACK?WHITE:EMPTY;
    }
    private void nextTurn(){
        setCurrentPlayer(getOtherPlayer(currentPlayer));
    }
    private void setEmptyBoard(){
        for (int x = 0; x < BOARD_SIZE_X; x++) {
            for (int y = 0; y < BOARD_SIZE_Y; y++) {
                placePiece(EMPTY_PIECE,x,y);
            }
        }
    }
    private void setBoard(){
        for (int x = 0; x < BOARD_SIZE_X; x++) {
            setColumn(x);
        }
    }
    private void setColumn(int columnNumber){
        setPieces(columnNumber);
        setEmptySpaces(columnNumber);
    }

    private void setPieces( int columnNumber) {
        placePiece(getPiece(PIECE_ORDER[columnNumber],WHITE), columnNumber,0);
        placePiece(getPiece(PAWN,WHITE), columnNumber,1);
        placePiece(getPiece(PAWN,BLACK), columnNumber,6);
        placePiece(getPiece(PIECE_ORDER[columnNumber],BLACK), columnNumber,7);
    }

    private void setEmptySpaces(int columnNumber) {
        for (int y = 2; y <BOARD_SIZE_Y-2 ; y++) {
            placePiece(getPiece(EMPTY,EMPTY), columnNumber,y);
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
        System.out.println(""+startXPos+startYPos+endXPos+endYPos);
        if (isMoveValid(startXPos,startYPos,endXPos,endYPos)){
            performMove(startXPos, startYPos, endXPos, endYPos);
            return true;
        }
        return false;
    }

    public boolean isMoveValid(int startXPos,int startYPos,int endXPos,int endYPos){
        if (positionChecks(startXPos, startYPos, endXPos, endYPos)) return false;
        boolean moveValid = switch (pieceAt(startXPos, startYPos).getPieceType()) {
            case PAWN -> isMoveValidPawn(startXPos, startYPos, endXPos, endYPos);
            case ROOK -> isMoveValidRook(startXPos, startYPos, endXPos, endYPos);
            case BISHOP -> isMoveValidBishop(startXPos, startYPos, endXPos, endYPos);
            case QUEEN -> isMoveValidQueen(startXPos, startYPos, endXPos, endYPos);
            case KING -> isMoveValidKing(startXPos, startYPos, endXPos, endYPos);
            case KNIGHT -> isMoveValidKnight(startXPos, startYPos, endXPos, endYPos);
            default -> false;
        };
        return moveValid && doesMoveAvoidSelfCheck(startXPos,startYPos,endXPos,endYPos);
    }

    private boolean positionChecks(int startXPos, int startYPos, int endXPos, int endYPos) {
        return (!checkPositionIsOnBoard(startXPos, startYPos) || !checkPositionIsOnBoard(endXPos, endYPos) || (startXPos==endXPos && startYPos==endYPos));
    }

    private boolean isMoveValidKnight(int startXPos,int startYPos,int endXPos,int endYPos){
        if((Math.abs(startXPos-endXPos)==1 && Math.abs(startYPos-endYPos)==2) ||(Math.abs(startXPos-endXPos)==2 && Math.abs(startYPos-endYPos)==1)){
            return (isPositionEnemy(endXPos,endYPos,pieceAt(startXPos,startYPos).getPlayerColour())||isPositionEmpty(endXPos,endYPos));
        }
        return false;
}
    private boolean isMoveValidKing(int startXPos,int startYPos,int endXPos,int endYPos){
        if(Math.abs(startXPos-endXPos)<=1 && Math.abs(startYPos-endYPos)<=1){
            return isPositionEmpty(endXPos,endYPos)||isPositionEnemy(endXPos,endYPos,pieceAt(startXPos,startYPos).getPlayerColour());
        }
        return isCastling(startXPos,startYPos,endXPos,endYPos);
    }
    private boolean isCastling(int startXPos,int startYPos,int endXPos,int endYPos){
        char playerColour = pieceAt(startXPos,startYPos).getPlayerColour();
        if (playerColour==WHITE){
            return isCastlingWhite(startXPos,startYPos,endXPos,endYPos);
        }
        return isCastlingBlack(startXPos,startYPos,endXPos,endYPos);
    }
    private boolean isCastlingWhite(int startXPos,int startYPos,int endXPos,int endYPos){
        if (!kingUnmovedWhite){
            return false;
        }
        if (rookUnmoved[0][0] && startXPos==4 && startYPos==0 && endXPos==2 && endYPos==0){
            return isCastlingLeftWhite();
        }
        if (rookUnmoved[1][0] && startXPos==4 && startYPos==0 && endXPos==6 && endYPos==0){
            return isCastlingRightWhite();
        }
        return false;
    }
    private boolean isCastlingBlack(int startXPos,int startYPos,int endXPos,int endYPos){
        if (!kingUnmovedBlack){
            return false;
        }
        if (rookUnmoved[0][1] && startXPos==4 && startYPos==7 && endXPos==2 && endYPos==7){
            return isCastlingLeftBlack();
        }
        if (rookUnmoved[1][1] && startXPos==4 && startYPos==7 && endXPos==6 && endYPos==7){
            return isCastlingRightBlack();
        }
        return false;
    }
    private boolean isCastlingLeftWhite(){
        if (!isPositionEmpty(1,0)||!isPositionEmpty(2,0)||isPositionEmpty(3,0)){
            return false;
        }
        if (isCheck(currentPlayer)){
            return false;
        }
        return !(isTileInCheck(2,0,currentPlayer)||isTileInCheck(3,0,currentPlayer));
    }
    private boolean isCastlingRightWhite(){
        if (!isPositionEmpty(5,0)||!isPositionEmpty(6,0)){
            return false;
        }
        if (isCheck(currentPlayer)){
            return false;
        }
        return !(isTileInCheck(5,0,currentPlayer)||isTileInCheck(6,0,currentPlayer));
    }
    private boolean isCastlingLeftBlack(){
        if (!isPositionEmpty(1,7)||!isPositionEmpty(2,7)||isPositionEmpty(3,7)){
            return false;
        }
        if (isCheck(currentPlayer)){
            return false;
        }
        return !(isTileInCheck(2,7,currentPlayer)||isTileInCheck(3,7,currentPlayer));
    }
    private boolean isCastlingRightBlack(){
        if (!isPositionEmpty(5,7)||!isPositionEmpty(6,7)){
            return false;
        }
        if (isCheck(currentPlayer)){
            return false;
        }
        return !(isTileInCheck(5,7,currentPlayer)||isTileInCheck(6,7,currentPlayer));
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
        int xDirection = Integer.compare(endXPos,startXPos);
        int yDirection = Integer.compare(endYPos,startYPos);
        int stepMax=Math.max(Math.abs(startXPos-endXPos),Math.abs(startYPos-endYPos));
        for (int stepCount = 1; stepCount <stepMax ; stepCount++) {
            if (!isPositionEmpty(startXPos+(xDirection*stepCount),startYPos+(yDirection*stepCount))){
                return false;
            }
        }
        return (isPositionEmpty(endXPos,endYPos)||isPositionEnemy(endXPos,endYPos,pieceAt(startXPos,startYPos).getPlayerColour()));

    }
    private void performMove(int startXPos,int startYPos,int endXPos,int endYPos) {
        specialMove(startXPos,startYPos,endXPos,endYPos);
        performMoveSingle(startXPos,startYPos,endXPos,endYPos);
}
private void performMoveSingle(int startXPos, int startYPos, int endXPos, int endYPos){
    placePiece(pieceAt(startXPos,startYPos),endXPos,endYPos);
    placePiece(getPiece(EMPTY,EMPTY),startXPos,startYPos);
}
private void specialMove(int startXPos,int startYPos,int endXPos,int endYPos){
        char playerColour = pieceAt(startXPos,startYPos).getPlayerColour();
        switch(pieceAt(startXPos,startYPos).getPieceType()){
            case PAWN-> {
                if(isEnPassant(endXPos,endYPos,playerColour)){
                    placePiece(EMPTY_PIECE,endXPos,endYPos-getDirectionFromColour(playerColour));
                    }
                resetEnPassant();
                if (startXPos==endXPos && Math.abs(startYPos-endYPos)==2){
                    setEnPassant(startXPos);
                }
            }
            case KING->{if(isCastling(startXPos,startYPos,endXPos,endYPos)){
                performCastling(endXPos,endYPos);}
                updateUnmovedKing();
                resetEnPassant();
            }
            case ROOK->{updateUnmovedRook(startXPos,startYPos);
            resetEnPassant();}
            default -> resetEnPassant();
        }
}
private void updateUnmovedKing(){
        if (currentPlayer==WHITE){
            kingUnmovedWhite=false;
        }
        if (currentPlayer==BLACK){
            kingUnmovedBlack=false;
        }
}
private void updateUnmovedRook(int xPos,int yPos){
        if (xPos==0 && yPos==0){
            rookUnmoved[0][0]=false;
        }
    if (xPos==0 && yPos==7){
        rookUnmoved[0][1]=false;
    }
    if (xPos==7 && yPos==0){
        rookUnmoved[1][0]=false;
    }
    if (xPos==7 && yPos==7){
        rookUnmoved[1][1]=false;
    }
}
private void performCastling(int endXPos,int endYPos){
        if (endYPos==0 && endXPos==2){
            performMove(0,0,0,3);
        }
        if (endYPos==0 && endXPos==6){
            performMove(0,7,0,5);
        }
    if (endYPos==7 && endXPos==2){
        performMove(7,0,7,3);
    }
    if (endYPos==7 && endXPos==6){
        performMove(7,7,7,5);
    }

}
private void resetEnPassant(){
    Arrays.fill(enPassant, false);
}
private void setEnPassant(int xPos){
        enPassant[xPos]=true;
}
    private boolean checkPositionIsOnBoard(int xPos,int yPos){
        return (xPos>=0 && yPos>=0 && xPos<BOARD_SIZE_X && yPos<BOARD_SIZE_Y);
    }
    private boolean isPositionEmpty(int xPos,int yPos){
        return (pieceAt(xPos,yPos).isEqual(EMPTY_PIECE));
    }
    private boolean isPositionEnemy(int xPos,int yPos,char playerColour){
        return getOtherPlayer(pieceAt(xPos,yPos).getPlayerColour())==playerColour;
    }
    private boolean isMoveValidPawn(int startXPos,int startYPos,int endXPos, int endYPos) {
        if (startXPos == endXPos) {
            return isMoveValidPawnForward(startXPos, startYPos, endXPos, endYPos);
        }
        if (Math.abs(startXPos-endXPos)==  1) {
            return isMoveValidPawnAttack(endXPos, endYPos, pieceAt(startXPos,startYPos).getPlayerColour());
        }
        return false;
    }
    private int getDirectionFromColour(char playerColour){
        return playerColour==WHITE ? 1 : -1;
}
    private boolean isMoveValidPawnForward(int startXPos,int startYPos,int endXPos, int endYPos) {
        int direction = getDirectionFromColour(pieceAt(startXPos,startYPos).getPlayerColour());
        if (endYPos == startYPos + direction) {
            return isPositionEmpty(endXPos, endYPos);
        }
        if (endYPos == startYPos + 2 * direction && isPawnOnFirstRow(startYPos,direction)) {
            return (isPositionEmpty(endXPos, endYPos) && isPositionEmpty(startXPos, startYPos + direction));
        }
        return false;
    }
    private boolean isPawnOnFirstRow(int yPos,int direction){
        return (direction == 1 && yPos == 1) || (direction == -1 && yPos == BOARD_SIZE_Y - 2);
    }
    private boolean isMoveValidPawnAttack(int endXPos, int endYPos,char playerColour) {
        if (isPositionEnemy(endXPos, endYPos, playerColour)) {
            return true;
        }
        return isEnPassant(endXPos,endYPos,playerColour);
    }
    private boolean isEnPassant(int endXPos, int endYPos,char playerColour){
        if (isPositionEmpty(endXPos,endYPos) && ((endYPos==2 && playerColour==BLACK)||(endYPos==BOARD_SIZE_Y-3 && playerColour==WHITE))){
            return enPassant[endXPos];
        }
        return false;
    }
    public boolean isCheck(char playerColour){
        int[] kingLocation= getKingLocation(playerColour);
        if (kingLocation[0]==-1) return false;
        return isTileInCheck(kingLocation[0],kingLocation[1],playerColour);
}
private boolean isTileInCheck(int targetXPos, int targetYPos, char playerColour){
    for (int xPos = 0; xPos < BOARD_SIZE_X; xPos++) {
        for (int yPos = 0; yPos < BOARD_SIZE_Y; yPos++) {
            if(isPositionEnemy(xPos,yPos,playerColour) && isMoveValid(xPos,yPos,targetXPos,targetYPos)){
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
    public ChessPiece getPiece(char pieceType,char playerColour){
    return switch (pieceType) {
        case PAWN -> playerColour == WHITE ? WHITE_PAWN : BLACK_PAWN;
        case QUEEN -> playerColour == WHITE ? WHITE_QUEEN : BLACK_QUEEN;
        case KING -> playerColour == WHITE ? WHITE_KING : BLACK_KING;
        case ROOK -> playerColour == WHITE ? WHITE_ROOK : BLACK_ROOK;
        case KNIGHT -> playerColour == WHITE ? WHITE_KNIGHT : BLACK_KNIGHT;
        case BISHOP -> playerColour == WHITE ? WHITE_BISHOP : BLACK_BISHOP;
        default -> EMPTY_PIECE;
    };
}
    public boolean isCheckmate(char playerColour){
        if (!isCheck(playerColour)){
            return false;
        }
    for (int xPos = 0; xPos < BOARD_SIZE_X; xPos++) {
        for (int yPos = 0; yPos < BOARD_SIZE_Y; yPos++) {
            if(isMoveAvailable(xPos,yPos,playerColour)){
                return false;
            }
        }
    }
    return true;
}
    public boolean isStalemate(char playerColour){
        if (isCheck(playerColour)){
            return false;
        }
        for (int xPos = 0; xPos < BOARD_SIZE_X; xPos++) {
            for (int yPos = 0; yPos < BOARD_SIZE_Y; yPos++) {
                if(isMoveAvailable(xPos,yPos,playerColour)){
                    return false;
                }
            }
        }
        return true;
    }
    private boolean isMoveAvailable(int startXPos,int startYPos, char playerColour){
        if(pieceAt(startXPos,startYPos).getPlayerColour()!=playerColour){
            return false;
        }
        for (int endXPos = 0; endXPos < BOARD_SIZE_X; endXPos++) {
            for (int endYPos = 0; endYPos < BOARD_SIZE_Y; endYPos++) {
                if(isMoveValid(startXPos,startYPos,endXPos,endYPos)&& doesMoveAvoidSelfCheck(startXPos,startYPos,endXPos,endYPos)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean doesMoveAvoidSelfCheck(int startXPos, int startYPos, int endXPos, int endYPos){
        return !performTempMove(startXPos,startYPos,endXPos,endYPos);
    }
    private boolean performTempMove(int startXPos, int startYPos, int endXPos, int endYPos){
        if (isCastling(startXPos, startYPos, endXPos, endYPos)){
            return performTempCastling(startXPos,startYPos,endXPos,endYPos);
        }
        if (pieceAt(startXPos,startYPos).getPieceType()==PAWN &&isEnPassant(endXPos,endYPos,pieceAt(startXPos,startYPos).getPlayerColour())){
            return performTempEnPassant(startXPos,startYPos,endXPos,endYPos);
        }
        return performTempMoveSingle(startXPos,startYPos,endXPos,endYPos);
    }
    private boolean performTempMoveSingle(int startXPos, int startYPos, int endXPos, int endYPos){
        ChessPiece endPiece = pieceAt(endXPos,endYPos);
        performMoveSingle(startXPos,startYPos,endXPos,endYPos);
        boolean result = isCheck(pieceAt(endXPos,endYPos).getPlayerColour());
        performMoveSingle(endXPos,endYPos,startXPos,startYPos);
        placePiece(endPiece,endXPos,endYPos);
        return result;
    }
    private boolean performTempEnPassant(int startXPos, int startYPos, int endXPos, int endYPos){
        boolean result =false;
        switch(endYPos){
            case 2-> {
                placePiece(getPiece(EMPTY,EMPTY),endXPos,3);
                result=performTempMoveSingle(startXPos,startYPos,endXPos,endYPos);
                placePiece(getPiece(PAWN,WHITE),endXPos,3);
            }
            case BOARD_SIZE_Y-3 ->{
                placePiece(getPiece(EMPTY,EMPTY),endXPos,4);
                result=performTempMoveSingle(startXPos,startYPos,endXPos,endYPos);
                placePiece(getPiece(PAWN,WHITE),endXPos,4);
            }
        }
        return result;
    }

    private boolean performTempCastling(int startXPos, int startYPos, int endXPos, int endYPos){
        boolean result=false;
        if (endXPos==2 && endYPos==7){
            performMoveSingle(0,7,3,7);
            result = performTempMoveSingle(startXPos,startYPos,endXPos,endYPos);
            performMoveSingle(3,7,0,7);
        }
        if (endXPos==6 && endYPos==7){
            performMoveSingle(7,7,5,7);
            result = performTempMoveSingle(startXPos,startYPos,endXPos,endYPos);
            performMoveSingle(5,7,7,7);
        }
        if (endXPos==2 && endYPos==0){
            performMoveSingle(0,0,3,0);
            result = performTempMoveSingle(startXPos,startYPos,endXPos,endYPos);
            performMoveSingle(3,0,0,0);
        }
        if (endXPos==6 && endYPos==0){
            performMoveSingle(7,0,5,0);
            result = performTempMoveSingle(startXPos,startYPos,endXPos,endYPos);
            performMoveSingle(5,0,7,0);
        }
        return result;
    }
}
