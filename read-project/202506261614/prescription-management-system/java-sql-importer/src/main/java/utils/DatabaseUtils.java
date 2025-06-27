package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class DatabaseUtils {

    private static final String DB_URL = "jdbc:sqlite:your_database_name.db"; // Change to your database name

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void executeSqlScript(String filePath) {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            String sql = new String(Files.readAllBytes(Paths.get(filePath)));
            stmt.execute(sql);
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}