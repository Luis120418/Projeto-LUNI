import dao.StartupDAO;
import model.Startup;
import model.vo.Dinheiro;
import model.vo.Humor;

public class TestaConexao {
    public static void main(String[] args) {
        StartupDAO dao = new StartupDAO();
        dao.criarTabela();

        Startup s = new Startup("TesteCorp",
                new Dinheiro(50000),
                new Dinheiro(10000),
                new Humor(70),
                new Humor(80));

        dao.salvar(s);
        System.out.println("Startup salva com sucesso!");
    }
}
