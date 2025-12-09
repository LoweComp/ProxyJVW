package ProtectionProxy;

public class ArtistaContratoReal implements IArtistaContrato {

    private String nome;
    private double custo;
    private final String DETALHES_CONFIDENCIAIS = "Detalhes de Camarim: Sem glúten, água alcalina (PH 9.5).";

    public ArtistaContratoReal(String nome, double custo) {
        this.nome = nome;
        this.custo = custo;
    }

    @Override
    public String getDetalhesPublicos() {
        return "Artista: " + nome + ". Gênero: Afro House/Funk.";
    }

    @Override
    public double getCustoContratacao(String permissao) {
        return custo;
    }

    @Override
    public String getDetalhesConfidenciais(String permissao) {
        return DETALHES_CONFIDENCIAIS;
    }
}
