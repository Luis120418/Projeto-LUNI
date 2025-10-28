package actions;

import model.Startup;
import model.Deltas;

public class MarketingStrategy implements DecisaoStrategy {

    @Override
    public Deltas aplicar(Startup s) {
        System.out.println("Executando estratégia de Marketing...");
        s.registrar("Marketing: -R$10k caixa, +5 rep, +3% receita");
        return new Deltas(-10_000.0, 5, 0, 0.03); // (caixa, reputação, moral, bonus%)
    }
}
