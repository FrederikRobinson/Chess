package restservice;

import com.games.ChessBoard;

import java.sql.Connection;
import java.sql.*;
public class DatabaseManager {


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
        return new ChessBoard();
    }

    }
