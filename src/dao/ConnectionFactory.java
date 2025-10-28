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
            // 1️⃣ Carrega o driver do H2
            Class.forName("org.h2.Driver");

            // 2️⃣ Cria a conexão inicial
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

                // 3️⃣ Executa o schema.sql (se existir)
                try (Statement st = conn.createStatement()) {
                    st.execute("""
                        CREATE TABLE IF NOT EXISTS startup (
                            id IDENTITY PRIMARY KEY,
                            nome VARCHAR(255),
                            caixa DOUBLE,
                            receita_base DOUBLE,
                            reputacao INT,
                            moral INT
                        )
                    """);
                }

                // 4️⃣ Garante que as tabelas de rodada e decisão existam
                new RodadaDAO().criarTabelaRodada();
                new RodadaDAO().criarTabelaDecisao();

            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver H2 não encontrado!", e);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inicializar o banco!", e);
        }
    }

    // Método que retorna uma nova conexão
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
