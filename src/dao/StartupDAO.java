package dao;

import model.Startup;
import model.vo.Dinheiro;
import model.vo.Humor;
import model.vo.Percentual;
import java.sql.*;

public class StartupDAO {

    public void salvar(Startup s) {
        String sql = "INSERT INTO startup (nome, caixa, receita_base, reputacao, moral) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getNome());
            ps.setDouble(2, s.getCaixa().valor());
            ps.setDouble(3, s.getReceitaBase().valor());
            ps.setInt(4, s.getReputacao().valor());
            ps.setInt(5, s.getMoral().valor());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar startup", e);
        }
    }

    public void criarTabela() {
        String sql = """
            CREATE TABLE IF NOT EXISTS startup (
                id IDENTITY PRIMARY KEY,
                nome VARCHAR(255),
                caixa DOUBLE,
                receita_base DOUBLE,
                reputacao INT,
                moral INT
            )
        """;
        try (Connection conn = ConnectionFactory.getConnection();
             Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela startup", e);
        }
    }
}
