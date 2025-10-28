package actions;

import model.Startup;
import model.Deltas;

public class EquipeStrategy implements DecisaoStrategy {
    @Override
    public Deltas aplicar(Startup s) {
        System.out.println("Executando estratégia de Equipe...");
        s.registrar("Equipe: -R$5k caixa, +7 moral");
        // (caixaDelta, reputacaoDelta, moralDelta, bonusDelta)
        return new Deltas(-5_000.0, 0, 7, 0.0);
    }
}
