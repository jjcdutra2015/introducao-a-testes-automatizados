package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Avaliador;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.Assert.assertEquals;

public class AvaliadorTeste {

    private Avaliador leiloeiro;
    private Usuario joao;
    private Usuario jose;
    private Usuario maria;

    @Before
    public void setUp() {
        this.leiloeiro = new Avaliador();
        this.joao = new Usuario("Joao");
        this.jose = new Usuario("Jose");
        this.maria = new Usuario("Maria");
    }

    @After
    public void finaliza() {
    }

    @BeforeClass
    public static void testandoBeforeClass() {
    }

    @AfterClass
    public static void testandoAfterClass() {
    }

    @Test
    public void deveEntenderLancesEmOrdemCrescente() {
        // parte 1: cenário
        Leilao leilao = new CriadorDeLeilao().para("TV")
                .lance(joao, 250)
                .lance(jose, 300)
                .lance(maria, 400)
                .constroi();

        // parte 2: ação
        leiloeiro.avalia(leilao);

        // parte 3: validação

        assertThat(leiloeiro.getMaiorLance(), equalTo(400.0));
        assertThat(leiloeiro.getMenorLance(), equalTo(250.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void mediaIgualAZero() {
        Leilao leilao = new CriadorDeLeilao().para("TV")
                .lance(joao, 0)
                .constroi();

        leiloeiro.avalia(leilao);

//        assertThat(leiloeiro.getMedia(), equalTo(0));
    }

    @Test
    public void deveCalcularAMedia() {
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(joao, 300)
                .lance(jose, 400)
                .lance(maria, 500)
                .constroi();

        leiloeiro.avalia(leilao);

        assertThat(leiloeiro.getMedia(), equalTo(400.0));
    }

    @Test
    public void deveEntenderLeilaoComApenasUmLance() {
        Leilao leilao = new CriadorDeLeilao().para("TV")
                .lance(joao, 200)
                .constroi();

        leiloeiro.avalia(leilao);

        assertThat(leiloeiro.getMaiorLance(), equalTo(200.0));
        assertThat(leiloeiro.getMenorLance(), equalTo(200.0));
    }

    @Test
    public void deveEntederLeilaoComLancesEmOrdemAleatoria() {
        Leilao leilao = new Leilao("TV");
        leilao.propoe(new Lance(joao, 200));
        leilao.propoe(new Lance(jose, 450));
        leilao.propoe(new Lance(maria, 120));
        leilao.propoe(new Lance(joao, 700));
        leilao.propoe(new Lance(jose, 630));
        leilao.propoe(new Lance(maria, 230));

        leiloeiro.avalia(leilao);

        assertThat(leiloeiro.getMenorLance(), equalTo(120.0));
        assertThat(leiloeiro.getMaiorLance(), equalTo(700.0));
    }

    @Test
    public void deveEntenderLeilaoComLancesEmOrdermDecrescente() {
        Leilao leilao = new CriadorDeLeilao().para("TV")
                .lance(joao, 400)
                .lance(jose, 300)
                .lance(joao, 200)
                .lance(jose, 100)
                .constroi();

        leiloeiro.avalia(leilao);

        assertThat(leiloeiro.getMaiorLance(), equalTo(400.0));
        assertThat(leiloeiro.getMenorLance(), equalTo(100.0));
    }

    @Test
    public void deveEntenderLeilaoComOsTresMaioresLances() {
        Leilao leilao = new CriadorDeLeilao().para("TV")
                .lance(joao, 100)
                .lance(jose, 200)
                .lance(joao, 300)
                .lance(jose, 400)
                .lance(joao, 500)
                .constroi();

        leiloeiro.avalia(leilao);

        assertEquals(3, leiloeiro.getTresMaiores().size());
        assertThat(leiloeiro.getTresMaiores().get(0).getValor(), equalTo(500.0));
        assertThat(leiloeiro.getTresMaiores().get(1).getValor(), equalTo(400.0));
        assertThat(leiloeiro.getTresMaiores().get(2).getValor(), equalTo(300.0));
    }

    @Test
    public void deveEntenderLeilaoComMenosDeTresLances() {
        Leilao leilao = new CriadorDeLeilao().para("TV")
                .lance(joao, 100)
                .lance(jose, 200)
                .constroi();

        leiloeiro.avalia(leilao);

        assertEquals(2, leiloeiro.getTresMaiores().size());
        assertThat(leiloeiro.getTresMaiores().get(0).getValor(), equalTo(200.0));
        assertThat(leiloeiro.getTresMaiores().get(1).getValor(), equalTo(100.0));
    }

   @Test(expected = RuntimeException.class)
    public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
        Leilao leilao = new CriadorDeLeilao().para("TV").constroi();

        leiloeiro.avalia(leilao);
    }
}
