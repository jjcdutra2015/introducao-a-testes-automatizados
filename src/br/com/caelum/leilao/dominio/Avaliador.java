package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Avaliador {


    private double maiorDeTodos = Double.NEGATIVE_INFINITY;
    private double menorDeTodos = Double.POSITIVE_INFINITY;
    private double media = 0;
    private List<Lance> maiores;

    public void avalia(Leilao leilao) {

        if (leilao.getLances().size() == 0) {
            throw new RuntimeException("Não é possível avaliar um leilão sem lances!");
        }

        double total = 0;
        for (Lance lance : leilao.getLances()) {
            if (lance.getValor() > maiorDeTodos) maiorDeTodos = lance.getValor();
            if (lance.getValor() < menorDeTodos) menorDeTodos = lance.getValor();

            total += lance.getValor();
        }

        if (total == 0) {
            media = 0;
            return;
        }
        media = total / leilao.getLances().size();

        pegaosMaioresNo(leilao);
    }

    private void pegaosMaioresNo(Leilao leilao) {
        maiores = new ArrayList<Lance>(leilao.getLances());
        Collections.sort(maiores, new Comparator<Lance>() {
            @Override
            public int compare(Lance o1, Lance o2) {
                if (o1.getValor() > o2.getValor()) return -1; //ordem decrescente
                if (o1.getValor() < o2.getValor()) return 1; //ordem crescente
                return 0;
            }
        });
        maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
    }

    public double getMaiorLance() {
        return maiorDeTodos;
    }

    public double getMenorLance() {
        return menorDeTodos;
    }

    public double getMedia() {
        return media;
    }

    public List<Lance> getTresMaiores() {
        return maiores;
    }
}
