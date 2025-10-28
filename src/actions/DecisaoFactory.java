package actions;

public class DecisaoFactory {
    public static DecisaoStrategy criar(String tipo){
        DecisaoStrategy strat = switch (tipo.toUpperCase()) {
            case "MARKETING"     -> new MarketingStrategy();
            case "EQUIPE"        -> new EquipeStrategy();
            case "PRODUTO"       -> new ProdutoStrategy();
            case "INVESTIDORES"  -> new InvestidoresStrategy();
            case "CORTAR_CUSTOS" -> new CortarCustosStrategy();
            default -> throw new IllegalArgumentException("Decisão desconhecida: " + tipo);
        };
        System.out.println("[Factory] criando: " + tipo + " -> " + strat.getClass().getName());
        return strat;
    }
}
