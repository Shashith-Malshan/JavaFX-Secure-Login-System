package Database;

import java.sql.Connection;
import java.sql.DriverManager;

import static java.lang.Class.forName;

public class TestDB {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL JDBC Driver is available!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL JDBC Driver NOT found!");
            e.printStackTrace();
        }
    }

}
