package actions;

import model.Startup;
import model.Deltas;

public class CortarCustosStrategy implements DecisaoStrategy {
    @Override
    public Deltas aplicar(Startup s) {
        System.out.println("Executando estratégia de Cortar Custos...");
        s.registrar("Cortar Custos: +R$5k caixa, -5 moral, -2 reputação");
        // (caixaDelta, reputacaoDelta, moralDelta, bonusDelta)
        return new Deltas(+5_000.0, -2, -5, 0.0);
    }
}
