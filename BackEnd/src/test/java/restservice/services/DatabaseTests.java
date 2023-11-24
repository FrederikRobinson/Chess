package restservice.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    @BeforeEach
    public void setUp(){
        testDatabaseManager = new DatabaseManager();
        mockConnection = mock(Connection.class);
        testDatabaseManager.setConnection(mockConnection);
    }

    @Test
    public void LoginReturnsExpectedUserIdOnValidDetails() throws Exception{
        UserDetails mockUserDetails = mock(UserDetails.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
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
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(mockResultSet.next()).thenReturn(false);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockConnection.prepareStatement("select * from users where username = ? AND password = ?")).thenReturn(mockPreparedStatement);

        assertEquals(testDatabaseManager.login(mockUserDetails),0);
    }
    @Test
    public void LoginReturnsZeroSetOnSQLException() throws Exception{
        UserDetails mockUserDetails = mock(UserDetails.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
        when(mockConnection.prepareStatement("select * from users where username = ? AND password = ?")).thenReturn(mockPreparedStatement);

        assertEquals(testDatabaseManager.login(mockUserDetails),0);
    }
    @Test
    public void SignUpReturnsNonZeroIntOnValidDetails() throws Exception{
        UserDetails mockUserDetails = mock(UserDetails.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        assertNotEquals(testDatabaseManager.signUp(mockUserDetails),0);
    }
    @Test
    public void SignUpReturnsZeroSetOnInvalidDetails() throws Exception{
        UserDetails mockUserDetails = mock(UserDetails.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(mockPreparedStatement.executeUpdate()).thenReturn(0);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        assertEquals(testDatabaseManager.signUp(mockUserDetails),0);
    }
    @Test
    public void SignUpReturnsZeroSetOnSQLException() throws Exception{
        UserDetails mockUserDetails = mock(UserDetails.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        assertEquals(testDatabaseManager.signUp(mockUserDetails),0);
    }
}
