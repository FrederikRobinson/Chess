package com.games;

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


    private final int BOARD_SIZE_X = 8;
    private final int BOARD_SIZE_Y = 8;

    public ChessBoard(){
        setBoard();
    }
    private void setBoard(){
        for (int x = 0; x < BOARD_SIZE_X; x++) {
            setColumn(x);
        }
    }
    private void setColumn(int columnNumber){
        SetPieces(columnNumber);
        setEmptySpaces(columnNumber);
    }

    private void SetPieces( int columnNumber) {
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
        if (isMoveValid(startXPos,startYPos,endXPos,endYPos)){
            performMove(startXPos, startYPos, endXPos, endYPos);
            return true;
        }
        return false;
    }

    public boolean isMoveValid(int startXPos,int startYPos,int endXPos,int endYPos){
        if(!checkPositionIsOnBoard(startXPos, startYPos) || !checkPositionIsOnBoard(endXPos,endYPos)){
            return false;
        }
        return switch (pieceAt(startXPos, startYPos).getPieceType()) {
            case PAWN -> isMoveValidPawn(startXPos, startYPos, endXPos, endYPos);
            case ROOK -> isMoveValidRook(startXPos, startYPos, endXPos, endYPos);
            case BISHOP -> isMoveValidBishop(startXPos, startYPos, endXPos, endYPos);
            case QUEEN -> isMoveValidQueen(startXPos, startYPos, endXPos, endYPos);
            case KING -> isMoveValidKing(startXPos, startYPos, endXPos, endYPos);
            case KNIGHT -> isMoveValidKnight(startXPos, startYPos, endXPos, endYPos);
            default -> false;
        };
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
        placePiece(pieceAt(startXPos,startYPos),endXPos,endYPos);
        placePiece(EMPTY_PIECE,startXPos,startYPos);
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

    return isPositionEnemy(endXPos,endYPos,playerColour);}
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
                if(isMoveValid(startXPos,startYPos,endXPos,endYPos)&&!doesMoveCauseCheck(startXPos,startYPos,endXPos,endYPos)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean doesMoveCauseCheck(int startXPos,int startYPos,int endXPos, int endYPos){
        ChessPiece startPiece = pieceAt(startXPos,startYPos);
        ChessPiece endPiece = pieceAt(endXPos,endYPos);
        performMove(startXPos,startYPos,endXPos,endYPos);
        boolean result = isCheck(startPiece.getPlayerColour());
        placePiece(startPiece,startXPos,startYPos);
        placePiece(endPiece,endXPos,endYPos);
        return result;
    }
}
