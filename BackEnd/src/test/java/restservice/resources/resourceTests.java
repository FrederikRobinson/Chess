package restservice.resources;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class resourceTests {
    @Test
    public void GameMoveGettersAndSetters(){
        GameMove testGameMove=new GameMove();
        testGameMove.setGameId(5);
        assertEquals(testGameMove.getGameId(),5);
        testGameMove.setPlayerColour('W');
        assertEquals(testGameMove.getPlayerColour(),'W');
        testGameMove.setStartXPos('a');
        assertEquals(testGameMove.getStartXPos(),0);
        testGameMove.setEndXPos('a');
        assertEquals(testGameMove.getEndXPos(),0);
        testGameMove.setStartXPos('h');
        assertEquals(testGameMove.getStartXPos(),7);
        testGameMove.setEndXPos('h');
        assertEquals(testGameMove.getEndXPos(),7);
        testGameMove.setStartYPos(5);
        assertEquals(testGameMove.getStartYPos(),4);
        testGameMove.setEndYPos(7);
        assertEquals(testGameMove.getEndYPos(),6);
    }
    @Test
    public void NewGameRequestGettersAndSetters(){
        NewGameRequest testNewGameRequest = new NewGameRequest();
        testNewGameRequest.setUserId(100);
        assertEquals(testNewGameRequest.getUserId(),100);

    }
    @Test
    public void UserDetailsGettersAndSetters(){
        UserDetails testUserDetails = new UserDetails();
        testUserDetails.setEmail("Email@email.com");
        assertEquals(testUserDetails.getEmail(),"Email@email.com");
        testUserDetails.setPassword("123");
        assertEquals(testUserDetails.getPassword(),"123");
        testUserDetails.setUsername("Alice");
        assertEquals(testUserDetails.getUsername(),"Alice");
    }

}
