package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/login_system";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static DBConnection instance;

    private DBConnection() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    public static DBConnection getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    // Returns a fresh connection every time
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
