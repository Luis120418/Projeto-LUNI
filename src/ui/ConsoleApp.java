package ui;

import config.Config;
import engine.GameEngine;
import model.Startup;
import model.vo.Dinheiro;
import model.vo.Humor;

import java.util.*;

public class ConsoleApp {
    private final Config config = new Config();
    private final List<Startup> startups = new ArrayList<>();

    public void start() {
        Scanner in = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n==== MENU PRINCIPAL ====");
            System.out.println("1 - Novo jogo (adicionar startup)");
            System.out.println("2 - Iniciar simulação");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(in.nextLine().trim());
            } catch (Exception e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1 -> criarStartup(in);
                case 2 -> executarSimulacao();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void criarStartup(Scanner in) {
        System.out.print("Nome da startup: ");
        String nome = in.nextLine().trim();

        Startup s = new Startup(
            nome,
            new Dinheiro(100_000),
            new Dinheiro(10_000),
            new Humor(50),
            new Humor(50)
        );

        startups.add(s);
        System.out.println("Startup '" + nome + "' adicionada!");
    }

    private void executarSimulacao() {
        if (startups.isEmpty()) {
            System.out.println("Nenhuma startup cadastrada!");
            return;
        }

        GameEngine engine = new GameEngine();

        for (Startup s : startups) {
            engine.executar(s, config.totalRodadas(), config.maxDecisoesPorRodada());
        }

        // === Ranking final ===
        System.out.println("\n====== RELATÓRIO FINAL ======");
        startups.sort(Comparator.comparingDouble(Startup::scoreFinal).reversed());

        int pos = 1;
        for (Startup s : startups) {
            System.out.printf(Locale.US,
                "%d) %s | SCORE: %.2f | Caixa: R$%.2f | ReceitaBase: R$%.2f | Rep: %d | Moral: %d%n",
                pos++, s.getNome(), s.scoreFinal(),
                s.getCaixa().valor(),
                s.getReceitaBase().valor(),
                s.getReputacao().valor(),
                s.getMoral().valor()
            );
        }
        System.out.println("==============================");
    }
}
