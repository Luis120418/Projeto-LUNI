package actions;

import model.Startup;
import model.Deltas;

public class InvestidoresStrategy implements DecisaoStrategy {
    @Override
    public Deltas aplicar(Startup s) {
        System.out.println("Executando estratégia de Investidores...");
        s.registrar("Investidores: +R$20k caixa, -3 moral, +2 reputação");
        // (caixaDelta, reputacaoDelta, moralDelta, bonusDelta)
        return new Deltas(+20_000.0, 2, -3, 0.0);
    }
}
