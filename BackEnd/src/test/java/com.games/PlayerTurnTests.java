package com.games;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class PlayerTurnTests {

    ChessGame testChessGame;
    @BeforeEach
    public void SetUp(){
        testChessGame = new ChessGame();
    }
    @Test
    public void NewGameStartsOnWhite(){
        assertEquals('W', testChessGame.getPlayerTurn());
    }
    @Test
    public void GetNextTurnFromWhiteIsBlack(){
        testChessGame.nextTurn();
        assertEquals('B', testChessGame.getPlayerTurn());
    }
    @Test
    public void GetNextTurnFromBlackIsWhite(){
        testChessGame.nextTurn();
        testChessGame.nextTurn();
        assertEquals('W', testChessGame.getPlayerTurn());
    }
    @Test
    public void TheBoardShouldStartInTheDefaultPosition(){

    }
}
