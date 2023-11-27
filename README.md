# Chess

If you don't have time to set up a game on a real board or the person you want to play against is not nearby this app allows you to play chess anywhere fast.

## What technologies were used?
Written in Java and JavaScript using
- Node for the frontend server
- React for display
- Bootstrap for styling
- Axios for RESTapi
- Stomp for websocket connection
- Springboot for the backend server
- MySQL for the database

## What is the project?

After creating an account and signing in you can create a new game of chess. Currently you are able to play against yourself on a single tab, but soon will be able to join games against other people.


![Image](/preview.png)


## How to run

A mySQL database running on "jdbc:mysql://localhost:3306/testdb" with a username and password of student. This will be altered in later versions.

To start the backend run  ./mvnw spring-boot:run from within the BackEnd folder
This will run on localhost port 8080

To start the frontend run npm run dev from within the FrontEnd folder
This will run on localhost port 5173

To access the webpage go to http://localhost:5173/


## Project structure

The FrontEnd and BackEnd folder splits the project between the Front end Node server with react and the Back end Springboot server built with maven and connected to a mySQL database

The front end src folder contains the application itself, along with the components it is comprised of, in the Components folder, and the utility files for useful functions such as the api calls in the Utils folder

The back end is built with maven and mostly follows the standard structure with the packages of code under src/main/java and the unit tests under src/test/java. The code is divided into game logic under the games package and the springboot RESTapi under the restservice package.

## To Do List

### Done:

- Display a board
- Add state to allow selecting tiles
- Skeleton of the api call for moves to the back end implemented
- Implemented most of the game logic
- Receiving and responding to http requests to backend
- Connect backend to a database
- Create user sign up and login pages
- Connect sign up and login to database
- Create a "Start new game" page

### For this week:

- Allow multiple users to play a game using web sockets
- Allow creation of games tied to users


### To do:

- Clearer error messages
- Create Join game page
- Replace login system with more security
- More verification on inputs to database
- Implement promotion (The last piece of game logic)
- Improve the look of the css
- Add more tests for better coverage
- Refactor to clean up the backend
- Create win/loss screen
- Record a win loss record for each account
- Display win/loss record to user

### Possible extensions:

- Notification of your turn
- Create a basic AI opponent
- Allow other games to be played on the board