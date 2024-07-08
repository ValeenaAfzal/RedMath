package com.project.first.redMath;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class RedMathApplication {

	public static void main(String[] args) throws Exception{

        String url = "jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12717841?useSSL=false";
        String username = "sql12717841";
        String password = "hqS2keUFcZ";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                System.out.println("Connection to the database is successful!");
            } else {
                System.out.println("Failed to connect to the database.");
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found.");
            e.printStackTrace();
        } catch (SQLException e){
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
        SpringApplication.run(RedMathApplication.class, args);
	}
}
