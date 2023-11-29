package restservice.controllers;

import com.games.ChessBoard;
import com.games.ChessPiece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import restservice.controllers.MoveController;
import restservice.controllers.UserController;
import restservice.resources.GameMove;
import restservice.resources.GameState;
import restservice.resources.NewGameRequest;
import restservice.resources.UserDetails;
import restservice.services.DatabaseManager;


import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
public class UserControllerTests {
    UserController testUserController;
    DatabaseManager mockDatabaseManager;
    UserDetails mockUserDetails;
    @BeforeEach
    public void setUp(){
        testUserController = new UserController();
        mockDatabaseManager = mock(DatabaseManager.class);
        testUserController.setDatabaseManager(mockDatabaseManager);
        mockUserDetails= mock(UserDetails.class);
    }
    @Test
    public void LoginCallsLoginFromDatabaseManager() throws Exception {
        testUserController.login(mockUserDetails);
        verify(mockDatabaseManager, times(1)).login(mockUserDetails);
    }
    @Test
    public void SignUpCallsSignUpFromDatabaseManager() throws Exception {
        testUserController.signUp(mockUserDetails);
        verify(mockDatabaseManager, times(1)).signUp(mockUserDetails);
    }
}
