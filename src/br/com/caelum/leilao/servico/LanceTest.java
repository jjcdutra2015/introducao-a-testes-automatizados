package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Avaliador;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LanceTest {

    private Avaliador leiloeiro;
    private Usuario joao;
    private Usuario jose;
    private Usuario maria;

    @Before
    public void seTup() {
        this.leiloeiro = new Avaliador();
        this.joao = new Usuario("Joao");
        this.jose = new Usuario("Jose");
        this.maria = new Usuario("Maria");
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveAceitarLanceZerado() {
        new Lance(new Usuario("Julio"), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveAceitarLanceNegativado() {
        Leilao leilao = new CriadorDeLeilao().para("TV")
                .lance(jose, -1)
                .constroi();

        leiloeiro.avalia(leilao);
    }
}