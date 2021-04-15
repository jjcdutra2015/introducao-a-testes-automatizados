package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTestBkp {

    @Test
    public void deveReceberUmLance() {
        Leilao leilao = new Leilao("TV");
        assertEquals(0, leilao.getLances().size());

        leilao.propoe(new Lance(new Usuario("Julio"), 2000));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void deveReceberVariosLances() {
        Leilao leilao = new Leilao("TV");

        leilao.propoe(new Lance(new Usuario("Julio"), 2000));
        leilao.propoe(new Lance(new Usuario("Jose"), 3000));

        assertEquals(2, leilao.getLances().size());
        assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
        assertEquals(3000, leilao.getLances().get(1).getValor(), 0.00001);
    }

    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
        Leilao leilao = new Leilao("TV");

        leilao.propoe(new Lance(new Usuario("Julio"), 2000));
        leilao.propoe(new Lance(new Usuario("Julio"), 3000));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
        Leilao leilao = new Leilao("Tv");

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
}