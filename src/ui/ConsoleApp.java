package ui;

import config.Config;
import engine.GameEngine;    // 👈 adiciona este!
import model.Startup;
import model.vo.Dinheiro;
import model.vo.Humor;



public class ConsoleApp {
    private final Config config = new Config();

    public void start() {
    System.out.println("=== Startup Game ===");
    System.out.println("total.rodadas = " + config.totalRodadas());
    System.out.println("max.decisoes.por.rodada = " + config.maxDecisoesPorRodada());
    System.out.println();

    java.util.Scanner in = new java.util.Scanner(System.in);
    int opcao = 0;

    do {
        System.out.println("\n==== MENU PRINCIPAL ====");
        System.out.println("1 - Novo jogo");
        System.out.println("2 - Continuar (em breve)");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");

        try {
            opcao = Integer.parseInt(in.nextLine().trim());
        } catch (NumberFormatException e) {
            opcao = -1; // entrada inválida
        }

        switch (opcao) {
            case 1 -> iniciarNovoJogo();
            case 2 -> System.out.println("Função de continuar ainda não disponível!");
            case 0 -> System.out.println("Saindo do jogo...");
            default -> System.out.println("Opção inválida, tente novamente.");
        }

    } while (opcao != 0);
}
private void iniciarNovoJogo() {
    java.util.Scanner in = new java.util.Scanner(System.in);
    System.out.print("\nDigite o nome da sua startup: ");
    String nome = in.nextLine().trim();

    // Cria a startup
    model.Startup startup = new model.Startup(
        nome,
        new model.vo.Dinheiro(100_000),
        new model.vo.Dinheiro(10_000),
        new model.vo.Humor(50),
        new model.vo.Humor(50)
    );

    // Cria e roda o motor do jogo
    GameEngine engine = new GameEngine();
    engine.executar(startup, config.totalRodadas(), config.maxDecisoesPorRodada());
}



}
