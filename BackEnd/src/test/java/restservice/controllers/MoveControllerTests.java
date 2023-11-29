package restservice.controllers;
import com.games.ChessBoard;
import com.games.ChessPiece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import restservice.controllers.MoveController;
import restservice.resources.GameMove;
import restservice.resources.GameState;
import restservice.resources.NewGameRequest;
import restservice.services.DatabaseManager;


import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class MoveControllerTests {
    MoveController testMoveController;
    DatabaseManager mockDatabaseManager;
    ChessBoard mockChessBoard;
    ChessPiece[][] mockBoardData;
    NewGameRequest mockNewGameRequest;

    int exampleGameId=100;
    int exampleUserId=200;
        @BeforeEach
        public void setUp(){
            testMoveController = new MoveController();
            mockBoardData = new ChessPiece[1][1];
            mockChessBoard = mock(ChessBoard.class);
            mockDatabaseManager = mock(DatabaseManager.class);
            testMoveController.setDatabaseManager(mockDatabaseManager);
            mockNewGameRequest = mock(NewGameRequest.class);
        }

        @Test
        public void MoveCallsGetGameFromDatabaseManager() throws Exception{
            GameMove mockGameMove = mock(GameMove.class);

            when(mockGameMove.getGameId()).thenReturn(exampleGameId);
            when(mockDatabaseManager.getGame(exampleGameId)).thenReturn(null);
            testMoveController.move(mockGameMove);
            verify(mockDatabaseManager, times(1)).getGame(exampleGameId);
        }
        @Test
        public void MoveCallsUpdateGameFromDatabaseManagerOnValidGame() throws Exception{
            GameMove mockGameMove = mock(GameMove.class);

            when(mockGameMove.getGameId()).thenReturn(exampleGameId).thenReturn(exampleGameId);
            when(mockDatabaseManager.updateGame(mockChessBoard,exampleGameId)).thenReturn(true);
            when(mockDatabaseManager.getGame(exampleGameId)).thenReturn(mockChessBoard);
            testMoveController.move(mockGameMove);
            verify(mockDatabaseManager, times(1)).updateGame(mockChessBoard,exampleGameId);
        }
        @Test
        public void MoveReturnsExpectedValueOnValidGame() throws Exception{
            GameMove mockGameMove = mock(GameMove.class);

            when(mockGameMove.getGameId()).thenReturn(exampleGameId).thenReturn(exampleGameId);
            when(mockDatabaseManager.updateGame(mockChessBoard,exampleGameId)).thenReturn(true);
            when(mockDatabaseManager.getGame(exampleGameId)).thenReturn(mockChessBoard);
            when(mockChessBoard.getBoard()).thenReturn(mockBoardData);
            when(mockChessBoard.getCurrentPlayer()).thenReturn('W');
            GameState result = testMoveController.move(mockGameMove);
            assertEquals(result,new GameState(mockBoardData,'W'));
        }
        @Test
        public void MoveReturnsExpectedValueOnInvalidGame() throws Exception{
            GameMove mockGameMove = mock(GameMove.class);

            when(mockGameMove.getGameId()).thenReturn(exampleGameId).thenReturn(exampleGameId);
            when(mockDatabaseManager.getGame(exampleGameId)).thenReturn(null);
            GameState result = testMoveController.move(mockGameMove);
            assertEquals(result,new GameState(null,'X'));
        }

    @Test
    public void MakeGameCallsCreateGameFromDatabaseManager() throws Exception {
        when(mockNewGameRequest.getUserId()).thenReturn(exampleUserId);
        testMoveController.makeGame(mockNewGameRequest);
        verify(mockDatabaseManager, times(1)).createGame(exampleUserId);
    }

    @Test
    public void GetGameCallsGetGameFromDatabaseManager() throws Exception{

        when(mockDatabaseManager.getGame(exampleGameId)).thenReturn(null);
        testMoveController.getGame(exampleGameId);
        verify(mockDatabaseManager, times(1)).getGame(exampleGameId);
    }
    @Test
    public void GetGameReturnsExpectedValueOnValidGame() throws Exception{

        when(mockDatabaseManager.getGame(exampleGameId)).thenReturn(mockChessBoard);
        when(mockChessBoard.getBoard()).thenReturn(mockBoardData);
        when(mockChessBoard.getCurrentPlayer()).thenReturn('W');
        GameState result = testMoveController.getGame(exampleGameId);
        assertEquals(result,new GameState(mockBoardData,'W'));
    }
    @Test
    public void GetGameReturnsExpectedValueOnInvalidGame() throws Exception{

        when(mockDatabaseManager.getGame(exampleGameId)).thenReturn(null);
        GameState result = testMoveController.getGame(exampleGameId);
        assertEquals(result,new GameState(null,'X'));
    }
}