package ProtectionProxy;

public interface IArtistaContrato {
    String getDetalhesPublicos();
    double getCustoContratacao(String permissao);
    String getDetalhesConfidenciais(String permissao);
}
