package ProtectionProxy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArtistaProxyTest {

    private final String NOME_ARTISTA = "Pop Star";
    private final double CUSTO_SHOW = 150000.00;

    private final IArtistaContrato contratoProxy = new ProxyArtistaContrato(NOME_ARTISTA, CUSTO_SHOW);

    // 1: USUÁRIO COM PERMISSÃO TOTAL
    @Test
    void gerenteDeveTerAcessoTotalAosDadosConfidenciaisECusto() {
        String permissao = "GERENTE";

        assertTrue(contratoProxy.getDetalhesPublicos().contains(NOME_ARTISTA));

        assertEquals(CUSTO_SHOW, contratoProxy.getCustoContratacao(permissao), 0.001,
                "O Gerente deve acessar o custo real do show.");

        assertNotEquals("ACESSO_NEGADO_CONFIDENCIAL", contratoProxy.getDetalhesConfidenciais(permissao),
                "O Gerente deve ter acesso aos detalhes confidenciais.");
    }

    // 2: USUÁRIO COM PERMISSÃO PARCIAL (FINANCEIRO)
    @Test
    void financeiroDeveAcessarOCustoMasSerBloqueadoNosDetalhesConfidenciais() {
        String permissao = "FINANCEIRO";

        assertTrue(contratoProxy.getDetalhesPublicos().contains(NOME_ARTISTA));

        assertEquals(CUSTO_SHOW, contratoProxy.getCustoContratacao(permissao), 0.001,
                "O Financeiro deve acessar o custo real do show.");

        assertEquals("ACESSO_NEGADO_CONFIDENCIAL", contratoProxy.getDetalhesConfidenciais(permissao),
                "O Financeiro deve ser bloqueado ao tentar acessar detalhes confidenciais.");
    }

    // 3: USUÁRIO SEM PERMISSÃO
    @Test
    void recepcionistaDeveSerBloqueadoNoCustoENosDetalhesConfidenciais() {
        String permissao = "RECEPCIONISTA";

        assertTrue(contratoProxy.getDetalhesPublicos().contains(NOME_ARTISTA));

        assertEquals(0.0, contratoProxy.getCustoContratacao(permissao),
                "A Recepcionista deve ser bloqueada e receber 0.0 para o custo.");

        assertEquals("ACESSO_NEGADO_CONFIDENCIAL", contratoProxy.getDetalhesConfidenciais(permissao),
                "A Recepcionista deve ser bloqueada ao tentar acessar detalhes confidenciais.");
    }
}