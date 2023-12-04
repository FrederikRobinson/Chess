package restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class RestServiceApplication {
	public static Connection connection;
	public static void main(String[] args) {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "student", "student");
			System.out.println("Successful connection to db");	}
		catch (SQLException e){
			System.out.println("Failed to connect");
			System.out.println(e);
		}
		SpringApplication.run(RestServiceApplication.class, args);
	}

}
