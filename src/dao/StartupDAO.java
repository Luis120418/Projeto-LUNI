package dao;

import model.Startup;
import model.vo.Dinheiro;
import model.vo.Humor;
import model.vo.Percentual;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Startup> listar() {
        List<Startup> lista = new ArrayList<>();
        String sql = "SELECT nome, caixa, receita_base, reputacao, moral FROM startup";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nome = rs.getString("nome");
                double caixa = rs.getDouble("caixa");
                double receita = rs.getDouble("receita_base");
                int reputacao = rs.getInt("reputacao");
                int moral = rs.getInt("moral");

                Startup s = new Startup(
                    nome,
                    new Dinheiro(caixa),
                    new Dinheiro(receita),
                    new Humor(reputacao),
                    new Humor(moral)
                );
                lista.add(s);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar startups: " + e.getMessage());
        }

        return lista;
    }
}
