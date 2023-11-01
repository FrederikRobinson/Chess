package restservice;

import com.games.ChessBoard;
import com.games.ChessPiece;

import java.sql.Connection;
import java.sql.*;
public class DatabaseManager {

    public NewGameResponse createGame(){
        int gameId = 3;
        NewGameResponse response = null;
        Connection connection = RestServiceApplication.connection;
        try{
            ChessBoard game = new ChessBoard(1);
            PreparedStatement preparedStatement = connection.prepareStatement("insert into games (Board, PlayerTurn, PlayerOneID, PlayerTwoID, EnPassant, Castling,GameID) values (?,?,?,?,?,?,?)");
            preparedStatement.setString(1,getBoardString(game.getBoard()));
            preparedStatement.setString(2,String.valueOf(game.getCurrentPlayer()));
            preparedStatement.setInt(3,7);
            preparedStatement.setInt(4,8);
            preparedStatement.setInt(5,-1);
            preparedStatement.setInt(6,15);
            preparedStatement.setInt(7,gameId);
            int resultSet = preparedStatement.executeUpdate();
            System.out.println(resultSet);
            if (resultSet==1){
                response = new NewGameResponse(game.getBoard(),gameId);
            }
            return response;
        }
        catch (Exception e){
            System.out.println("Error updating database");
            System.out.println(e);
            return response;
        }
    }
public boolean updateGame(ChessBoard game, int gameId){
    Connection connection = RestServiceApplication.connection;
    try{
    PreparedStatement preparedStatement = connection.prepareStatement("update games set Board = ?, playerTurn = ?, EnPassant = ?, Castling = ? where GameID = ?");
        preparedStatement.setString(1,getBoardString(game.getBoard()));
        preparedStatement.setString(2,String.valueOf(game.getCurrentPlayer()));
        preparedStatement.setInt(3,game.getEnPassant());
        preparedStatement.setInt(4,game.getCastlingCode());
        preparedStatement.setInt(5,gameId);
        int resultSet = preparedStatement.executeUpdate();
        System.out.println(resultSet);
        return true;
}
    catch (Exception e){
        System.out.println("Error updating database");
        System.out.println(e);
        return false;
    }
}
    public ChessBoard getGame(int gameId) {
        Connection connection = RestServiceApplication.connection;
        ChessBoard result = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from games where GameID = ?");
            preparedStatement.setInt(1,gameId);

           // Statement myStatement = connection.createStatement();
            //myStatement.executeUpdate("insert into games (GameID, Board, PlayerTurn, PlayerOneID, PlayerTwoID, EnPassant, Castling) values (1, 'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX', 'W', 40, 37, 3, 2)");
            ResultSet myResult = preparedStatement.executeQuery();//myStatement.executeQuery("select * from games");
            while (myResult.next()) {
                result = convertToGame(myResult);
                System.out.println(myResult.getString("PlayerTurn"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    private ChessBoard convertToGame(ResultSet resultSet){
        ChessBoard newBoard = new ChessBoard();
        try {
            placePieces(newBoard, resultSet.getString("Board"));
            newBoard.setCurrentPlayer(resultSet.getString("PlayerTurn").charAt(0));
            newBoard.setEnPassant(resultSet.getInt("EnPassant"));
            newBoard.setCastling(resultSet.getInt("Castling"));
            return newBoard;
        }
        catch(Exception e){
            System.out.println("Error recreating board");
            System.out.println(e);
            return new ChessBoard();
        }
    }
    private String getBoardString(ChessPiece[][] board)
    {
        String result="";
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                result+=board[x][y].getPieceType();
                result+=board[x][y].getPlayerColour();
            }
        }
        System.out.println(result);
        return result;
    }
    private void placePieces(ChessBoard game,String boardString){
        int stringPosition = 0;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                game.placePiece(game.getPiece(boardString.charAt(stringPosition++),boardString.charAt(stringPosition++)),x,y);
            }
        }
    }

    }
