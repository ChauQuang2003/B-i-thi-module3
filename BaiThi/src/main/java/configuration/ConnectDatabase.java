package configuration;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/productmanagement";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}

