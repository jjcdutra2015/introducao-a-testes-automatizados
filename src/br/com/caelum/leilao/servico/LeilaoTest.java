package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static br.com.caelum.leilao.matcher.LeilaoMatcher.temUmLance;

public class LeilaoTest {

    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
        Leilao leilao = new Leilao("TV");

        leilao.propoe(new Lance(new Usuario("Julio"), 2000));
        leilao.propoe(new Lance(new Usuario("Julio"), 3000));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void naoDeveAceitarMaisDoQue5LancesDoMesmoUsuario() {
        Leilao leilao = new Leilao("TV");

        Usuario julio = new Usuario("Julio");
        Usuario jose = new Usuario("Jose");

        leilao.propoe(new Lance(julio, 2000));
        leilao.propoe(new Lance(jose, 3000));

        leilao.propoe(new Lance(julio, 4000));
        leilao.propoe(new Lance(jose, 5000));

        leilao.propoe(new Lance(julio, 6000));
        leilao.propoe(new Lance(jose, 7000));

        leilao.propoe(new Lance(julio, 8000));
        leilao.propoe(new Lance(jose, 9000));

        leilao.propoe(new Lance(julio, 10000));
        leilao.propoe(new Lance(jose, 11000));

        leilao.propoe(new Lance(julio, 12000));

        assertEquals(10, leilao.getLances().size());
        assertEquals(11000, leilao.getLances().get(leilao.getLances().size() - 1).getValor(), 0.00001);
    }

    @Test
    public void deveDobrarOUltimoLanceDadoPeloUsuario() {
        Leilao leilao = new Leilao("tv");

        Usuario julio = new Usuario("Julio");
        Usuario jose = new Usuario("Jose");

        leilao.propoe(new Lance(jose, 100));
        leilao.propoe(new Lance(julio, 200));

        leilao.dobraLance(julio);

        assertEquals(400, leilao.getLances().get(1).getValor(), 0.00001);
    }

    @Test
    public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
        Leilao leilao = new Leilao("tv");
        Usuario julio = new Usuario("Julio");

        leilao.dobraLance(julio);

        assertEquals(0, leilao.getLances().size());
    }

    @Test
    public void deveAceitarUmLance() {
        Leilao leilao = new CriadorDeLeilao().para("TV").constroi();

        Lance lance = new Lance(new Usuario("julio"), 200);
        leilao.propoe(lance);

        assertThat(leilao.getLances().size(), equalTo(1));
        assertThat(leilao, temUmLance(lance));
    }
}