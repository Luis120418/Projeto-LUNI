package dao;

import model.Startup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RodadaDAO {
    public void criarTabelaRodada() {
    String sql = """
        CREATE TABLE IF NOT EXISTS rodada (
            id IDENTITY PRIMARY KEY,
            startup_nome VARCHAR(255),
            numero_rodada INT,
            receita DOUBLE,
            caixa DOUBLE,
            receita_base DOUBLE
        )
    """;
    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.execute();
    } catch (SQLException e) {
        System.err.println("Erro ao criar tabela rodada: " + e.getMessage());
    }
}

public void criarTabelaDecisao() {
    String sql = """
        CREATE TABLE IF NOT EXISTS decisao (
            id IDENTITY PRIMARY KEY,
            startup_nome VARCHAR(255),
            numero_rodada INT,
            tipo_decisao VARCHAR(50)
        )
    """;
    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.execute();
    } catch (SQLException e) {
        System.err.println("Erro ao criar tabela decisao: " + e.getMessage());
    }
}

    
    public void salvarRodada(Startup s, int numeroRodada, double receita, double caixa, double receitaBase) {
        String sql = """
            INSERT INTO rodada (startup_nome, numero_rodada, receita, caixa, receita_base)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, s.getNome());
            stmt.setInt(2, numeroRodada);
            stmt.setDouble(3, receita);
            stmt.setDouble(4, caixa);
            stmt.setDouble(5, receitaBase);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao salvar rodada: " + e.getMessage());
        }
    }

    public void salvarDecisao(Startup s, int numeroRodada, String tipoDecisao) {
        String sql = """
            INSERT INTO decisao (startup_nome, numero_rodada, tipo_decisao)
            VALUES (?, ?, ?)
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, s.getNome());
            stmt.setInt(2, numeroRodada);
            stmt.setString(3, tipoDecisao);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao salvar decisão: " + e.getMessage());
        }
    }
}
