package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:h2:./database/startupdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    static {
    try {
        Class.forName("org.h2.Driver");
        try (Connection conn = getConnection()) {
            java.sql.Statement stmt = conn.createStatement();
            stmt.execute("RUNSCRIPT FROM 'src/dao/schema.sql'");
        }
    } catch (Exception e) {
        throw new RuntimeException("Erro ao carregar schema: " + e.getMessage());
    }
    }

}
