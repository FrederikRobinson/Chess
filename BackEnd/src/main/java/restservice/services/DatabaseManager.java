package restservice.services;

import com.games.ChessBoard;
import com.games.ChessPiece;
import restservice.resources.NewGameResponse;
import restservice.RestServiceApplication;
import restservice.resources.UserDetails;
import websocket.resources.JoinGameResponse;

import java.sql.Connection;
import java.sql.*;
import java.util.Random;

public class DatabaseManager {
    Connection connection = RestServiceApplication.connection;

    public void setConnection(Connection newConnection){
        connection=newConnection;
    }
    public int login(UserDetails user) {
        int result = 0;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from users where username = ? AND password = ?");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            ResultSet myResult = preparedStatement.executeQuery();// myStatement.executeQuery("select * from games");
            while (myResult.next()) {
                result = myResult.getInt("userId");
            }
        } catch (Exception e) {
            System.out.println(e);
            result = 0;
        }
        return result;
    }

    public int signUp(UserDetails user) {
        int userId = getNewId("users", "userId");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into users (userId, username, password, email, wins, losses,draws) values (?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setInt(5, 0);
            preparedStatement.setInt(6, 0);
            preparedStatement.setInt(7, 0);
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet == 1) {
                return userId;
            }
        } catch (Exception e) {
            System.out.println("Error creating new user");
            System.out.println(e);
        }
        return 0;
    }

    public NewGameResponse createGame(int userId) {
        Random random = new Random();
        NewGameResponse response = null;
        int gameId = getNewId("games", "GameID");
        int[] playerIds=new int[2];
        int mainPlayerTurnNumber = random.nextInt(2);
        playerIds[mainPlayerTurnNumber]=userId;
        try {

            ChessBoard game = new ChessBoard(1);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into games (Board, PlayerTurn, PlayerOneID, PlayerTwoID, EnPassant, Castling,GameID) values (?,?,?,?,?,?,?)");
            preparedStatement.setString(1, getBoardString(game.getBoard()));
            preparedStatement.setString(2, String.valueOf(game.getCurrentPlayer()));
            preparedStatement.setInt(3, playerIds[0]);
            preparedStatement.setInt(4, playerIds[1]);
            preparedStatement.setInt(5, -1);
            preparedStatement.setInt(6, 15);
            preparedStatement.setInt(7, gameId);
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet == 1) {
                response = new NewGameResponse(game.getBoard(), gameId,mainPlayerTurnNumber,'W');
            }
            return response;
        } catch (Exception e) {
            System.out.println("Error updating database");
            System.out.println(e);
            return null;
        }
    }

    private int getNewId(String databaseName, String idColumn) {
        Random random = new Random();
        ResultSet myResult;
        int gameId;
        try {
            // do {
            gameId = random.nextInt();
            // PreparedStatement preparedStatement = connection.prepareStatement("select *
            // from ? where ? = ?");
            // preparedStatement.setString(1, databaseName);
            // preparedStatement.setString(2, idColumn);
            // preparedStatement.setInt(3, gameId);

            // myResult = preparedStatement.executeQuery();
            // } while (myResult.next());
            return gameId;
        } catch (Exception e) {
            System.out.println("Error finding new Id for user");
            System.out.println(e);
            return 0;
        }
    }

    public boolean updateGame(ChessBoard game, int gameId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update games set Board = ?, playerTurn = ?, EnPassant = ?, Castling = ? where GameID = ?");
            preparedStatement.setString(1, getBoardString(game.getBoard()));
            preparedStatement.setString(2, String.valueOf(game.getCurrentPlayer()));
            preparedStatement.setInt(3, game.getEnPassant());
            preparedStatement.setInt(4, game.getCastlingCode());
            preparedStatement.setInt(5, gameId);
            int resultSet = preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error updating database");
            System.out.println(e);
            return false;
        }
    }

    public ChessBoard getGame(int gameId) {
        ChessBoard result = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from games where GameID = ?");
            preparedStatement.setInt(1, gameId);

            // Statement myStatement = connection.createStatement();
            // myStatement.executeUpdate("insert into games (GameID, Board, PlayerTurn,
            // PlayerOneID, PlayerTwoID, EnPassant, Castling) values (1,
            // 'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX',
            // 'W', 40, 37, 3, 2)");
            ResultSet resultSet = preparedStatement.executeQuery();// myStatement.executeQuery("select * from games");
            while (resultSet.next()) {
                result = convertToGame(resultSet);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    public JoinGameResponse joinGame(int userId, int gameId) {
        ChessBoard result = null;
        JoinGameResponse joinGameResponse = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from games where GameID = ?");
            preparedStatement.setInt(1, gameId);

            ResultSet resultSet = preparedStatement.executeQuery();// myStatement.executeQuery("select * from games");
            while (resultSet.next()) {
                int playerNumber = getPlayerNumber(resultSet,userId,gameId);
                result = convertToGame(resultSet);
                joinGameResponse = new JoinGameResponse(result.getBoard(),playerNumber, result.getCurrentPlayer());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return joinGameResponse;
    }
    private int getPlayerNumber(ResultSet resultSet,int userId,int gameId)throws Exception {
        if (resultSet.getInt("PlayerOneID")==userId){
            return 0;
        }
        if (resultSet.getInt("PlayerTwoID")==userId){
            return 1;
        }
        if(resultSet.getInt("PlayerOneID")==0){
            addPlayer(userId,gameId,0);
            return 0;
        }
        if(resultSet.getInt("PlayerTwoID")==0){
            addPlayer(userId,gameId,1);
            return 1;
        }
        return -1;
    }
    public void addPlayer(int userId,int gameId,int playerPosition){
        String playerIdColumn = playerPosition==0?"PlayerOneID":playerPosition==1?"PlayerTwoID":"";
        try{
        PreparedStatement preparedStatement = connection.prepareStatement(
                "update games set ? = ? where GameID = ?");
        preparedStatement.setString(1, playerIdColumn);
        preparedStatement.setInt(2, userId);
        preparedStatement.setInt(3, gameId);
        preparedStatement.executeUpdate();}
        catch (Exception e){
            System.out.println("error adding player to game");
            System.out.println(e);
        }
    }
//    public ChessBoard checkGame(int userId,int gameId) {
//        ChessBoard result = null;
//
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("select * from games where GameID = ?");
//            preparedStatement.setInt(1, gameId);
//            ResultSet resultSet = preparedStatement.executeQuery();// myStatement.executeQuery("select * from games");
//            while (resultSet.next()) {
//                if (resultSet.getInt("PlayerOneID")==userId&&resultSet.getString("PlayerTurn")=="W"){
//
//
//                }
//                System.out.println(resultSet.getString("PlayerTurn"));
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return result;
//    }

    private ChessBoard convertToGame(ResultSet resultSet) {
        ChessBoard newBoard = new ChessBoard();
        try {
            placePieces(newBoard, resultSet.getString("Board"));
            newBoard.setCurrentPlayer(resultSet.getString("PlayerTurn").charAt(0));
            newBoard.setEnPassant(resultSet.getInt("EnPassant"));
            newBoard.setCastling(resultSet.getInt("Castling"));
            return newBoard;
        } catch (Exception e) {
            System.out.println("Error recreating board");
            System.out.println(e);
            return new ChessBoard();
        }
    }

    private String getBoardString(ChessPiece[][] board) {
        StringBuilder result = new StringBuilder();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                result.append(board[x][y].getPieceType());
                result.append(board[x][y].getPlayerColour());
            }
        }
        return result.toString();
    }

    private void placePieces(ChessBoard game, String boardString) {
        int stringPosition = 0;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                game.placePiece(
                        game.getPiece(boardString.charAt(stringPosition++), boardString.charAt(stringPosition++)), x,
                        y);
            }
        }
    }

}
