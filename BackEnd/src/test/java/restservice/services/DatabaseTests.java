package restservice.services;
import com.games.ChessBoard;
import com.games.ChessPiece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import restservice.resources.UserDetails;

import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTests {
    DatabaseManager testDatabaseManager;
    Connection mockConnection;
    PreparedStatement mockPreparedStatement;
    @BeforeEach
    public void setUp(){
        testDatabaseManager = new DatabaseManager();
        mockConnection = mock(Connection.class);
        testDatabaseManager.setConnection(mockConnection);
        mockPreparedStatement = mock(PreparedStatement.class);
    }

    @Test
    public void LoginReturnsExpectedUserIdOnValidDetails() throws Exception{
        UserDetails mockUserDetails = mock(UserDetails.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("userId")).thenReturn(5);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockConnection.prepareStatement("select * from users where username = ? AND password = ?")).thenReturn(mockPreparedStatement);

        assertEquals(testDatabaseManager.login(mockUserDetails),5);
    }
    @Test
    public void LoginReturnsZeroSetOnInvalidDetails() throws Exception{
        UserDetails mockUserDetails = mock(UserDetails.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.next()).thenReturn(false);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockConnection.prepareStatement("select * from users where username = ? AND password = ?")).thenReturn(mockPreparedStatement);

        assertEquals(testDatabaseManager.login(mockUserDetails),0);
    }
    @Test
    public void LoginReturnsZeroSetOnSQLException() throws Exception{
        UserDetails mockUserDetails = mock(UserDetails.class);
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
        when(mockConnection.prepareStatement("select * from users where username = ? AND password = ?")).thenReturn(mockPreparedStatement);

        assertEquals(testDatabaseManager.login(mockUserDetails),0);
    }
    @Test
    public void SignUpReturnsNonZeroIntOnValidDetails() throws Exception{
        UserDetails mockUserDetails = mock(UserDetails.class);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        assertNotEquals(testDatabaseManager.signUp(mockUserDetails),0);
    }
    @Test
    public void SignUpReturnsZeroSetOnInvalidDetails() throws Exception{
        UserDetails mockUserDetails = mock(UserDetails.class);
        when(mockPreparedStatement.executeUpdate()).thenReturn(0);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        assertEquals(testDatabaseManager.signUp(mockUserDetails),0);
    }
    @Test
    public void SignUpReturnsZeroSetOnSQLException() throws Exception{
        UserDetails mockUserDetails = mock(UserDetails.class);
        when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        assertEquals(testDatabaseManager.signUp(mockUserDetails),0);
    }
    @Test
    public void CreateGameReturnsNullOnFailedGameCreation() throws Exception{
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(0);
        assertEquals(testDatabaseManager.createGame(100),null);
    }
    @Test
    public void CreateGameReturnsNotNullOnSuccessfulGameCreation() throws Exception{
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        assertNotEquals(testDatabaseManager.createGame(100),null);
    }
    @Test
    public void CreateGameReturnsNullOnExceptionGameCreation() throws Exception{
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
        assertEquals(testDatabaseManager.createGame(100),null);
    }
    @Test
    public void UpdateGameReturnsFalseOnFailedUpdate() throws Exception{
        ChessBoard exampleChessBoard = new ChessBoard();
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(0);
        assertEquals(testDatabaseManager.updateGame(exampleChessBoard,100),false);
    }
    @Test
    public void UpdateGameReturnsTrueOnSuccessfulUpdate() throws Exception{
        ChessBoard exampleChessBoard = new ChessBoard();
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        assertEquals(testDatabaseManager.updateGame(exampleChessBoard,100),true);
    }
    @Test
    public void UpdateGameReturnsFalseOnSQLExceptionUpdate() throws Exception{
        ChessBoard exampleChessBoard = new ChessBoard();
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
        assertEquals(testDatabaseManager.updateGame(exampleChessBoard,100),false);
    }
    @Test
    public void GetGameReturnsExpectedGameOnValidDetails() throws Exception{
        DatabaseManager partMockedDatabaseManager = Mockito.spy(testDatabaseManager);
        ChessBoard mockChessBoard = mock(ChessBoard.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        Mockito.doReturn(mockChessBoard).when(partMockedDatabaseManager).convertToGame(mockResultSet);

        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        assertEquals(partMockedDatabaseManager.getGame(100),mockChessBoard);
    }
    @Test
    public void GetGameReturnsNullGameOnInvalidDetails() throws Exception{
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockResultSet.next()).thenReturn(false);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        assertEquals(testDatabaseManager.getGame(100),null);
    }
    @Test
    public void GetGameReturnsNullGameOnSQLException() throws Exception{
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        assertEquals(testDatabaseManager.getGame(100),null);
    }

}
