package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseChecker {

    private static final String DB_URL = "jdbc:oracle:thin:@anburguer-database.c4mykgibfvue.us-east-1.rds.amazonaws.com:1521:orcl";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "123456789";

    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            System.out.println("Database is accessible.");

            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }
}
