package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.MatematicaMaluca;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MatematicaMalucaTeste {

    @Test
    public void deveReceberNumeroMaiorQue30() {
        MatematicaMaluca matematicaMaluca = new MatematicaMaluca();
        assertEquals(35*4, matematicaMaluca.contaMaluca(35));
    }

    @Test
    public void deveReceberNumeroMaiorQue10MenorQue30() {
        MatematicaMaluca matematicaMaluca = new MatematicaMaluca();
        assertEquals(15*3, matematicaMaluca.contaMaluca(15));
    }

    @Test
    public void deveReceberNumeroMenorQue10() {
        MatematicaMaluca matematicaMaluca = new MatematicaMaluca();
        assertEquals(8*2, matematicaMaluca.contaMaluca(8));
    }
}
