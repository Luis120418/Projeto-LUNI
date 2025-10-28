package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {

    private static final String URL = "jdbc:h2:./database/startupdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("org.h2.Driver");

            // Cria o banco e executa o schema.sql se ainda não existir
            try (Connection conn = getConnection()) {
                Statement stmt = conn.createStatement();
                stmt.execute("RUNSCRIPT FROM 'resources/schema.sql'");

            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inicializar o banco: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
