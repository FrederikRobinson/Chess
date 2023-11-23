# Chess
Why
If you don't have time to set up a game on a real board or the person you want to play against is not nearby this app allows you to play chess anywhere fast.

How
Using Node for the frontend server,React for display,Stomp for websocket connection,Axios for RESTapi,Springboot for backend server,MySQL database

What
The login system uses a RESTapi to sign in where you can create or join games of chess against other users. The games themselves are handled using websockets for communicating moves

Config info
How to run


To Do List
Done:
- Display a board
- Add state to allow selecting tiles
- Skeleton of the api call for moves to the back end implemented
- Implemented most of the game logic
- Skeleton for receiving http requests to backend
- Skeleton response for http requests to backend
- Connect backend to a database
- - Create user sign up and login pages
- - Connect sign up and login to database

For this week:

- Create a "Start new game" page
- Allow creation of games tied to users
- Allow multiple users to play a game using web sockets

Goal for this week:
- Allow multiple users to play a game

ToDo:
- Implement promotion (The last piece of game logic)
- Improve the look of the css
- Add more tests for better coverage
- Refactor to clean up the backend
- Create win/loss screen
- Record a win loss record for each account

Possible extensions:
- Notification of your turn
- Create a basic AI opponent
- Allow other games to be played on the board