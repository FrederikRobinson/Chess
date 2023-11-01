package restservice;

import java.sql.Connection;
import java.sql.*;
public class DatabaseManager {

        public static void main(String[] args) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","student","student");
                System.out.println("Success");
                Statement myStatement = connection.createStatement();
                myStatement.executeUpdate("insert into games (GameID, Board, PlayerTurn, PlayerOneID, PlayerTwoID, EnPassant, Castling) values (1, 'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX', 'W', 40, 37, 3, 2)");
                ResultSet myResult = myStatement.executeQuery("select * from games");
                while (myResult.next()) {
                System.out.println(myResult.getString("PlayerTurn"));}
            }
            catch(Exception e){
                System.out.println(e);
            }

        }
    }
