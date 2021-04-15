package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.AnoBissexto;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnoBissextoTest {

    @Test
    public void deveSerAnoBissexto() {
        AnoBissexto bissexto = new AnoBissexto();

        assertEquals(true, bissexto.ehBissexto(2020));
        assertEquals(true, bissexto.ehBissexto(2024));
    }

    @Test
    public void naoDeveSerAnoBissexto() {
        AnoBissexto bissexto = new AnoBissexto();

        assertEquals(false, bissexto.ehBissexto(2021));
        assertEquals(false, bissexto.ehBissexto(2035));
    }
}