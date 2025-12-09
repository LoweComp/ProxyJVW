package ProtectionProxy;

public class ProxyArtistaContrato implements IArtistaContrato {

    private ArtistaContratoReal artistaReal;

    public ProxyArtistaContrato(String nome, double custo) {
        this.artistaReal = new ArtistaContratoReal(nome, custo);
    }

    @Override
    public String getDetalhesPublicos() {
        return this.artistaReal.getDetalhesPublicos();
    }

    @Override
    public double getCustoContratacao(String permissao) {
        if (permissao.equals("FINANCEIRO") || permissao.equals("GERENTE")) {
            return this.artistaReal.getCustoContratacao(permissao);
        } else {
            // Nega o acesso ao valor real
            return 0.0;
        }
    }

    @Override
    public String getDetalhesConfidenciais(String permissao) {
        if (permissao.equals("GERENTE")) {
            return this.artistaReal.getDetalhesConfidenciais(permissao);
        } else {
            // Retorna mensagem de negação
            return "ACESSO_NEGADO_CONFIDENCIAL";
        }
    }
}
