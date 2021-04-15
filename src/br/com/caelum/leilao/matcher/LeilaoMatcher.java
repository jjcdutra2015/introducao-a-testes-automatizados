package br.com.caelum.leilao.matcher;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class LeilaoMatcher extends TypeSafeMatcher<Leilao> {

    private Lance lance;

    public LeilaoMatcher(Lance lance) {
        this.lance = lance;
    }

    @Override
    protected boolean matchesSafely(Leilao leilao) {
        return leilao.getLances().contains(lance);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Leilão com o lance " + lance.getValor());
    }

    public static Matcher<Leilao> temUmLance(Lance lance) {
        return new LeilaoMatcher(lance);
    }
}
