package br.com.app.leilao.dominio;

import java.util.*;

public class Avaliador {

    private double maiorDeTodos = Double.NEGATIVE_INFINITY;
    private double menorDeTodos = Double.POSITIVE_INFINITY;
    private double mediaDeTodos = Double.POSITIVE_INFINITY;
    private List<Lance> maiores;

    public void avalia(Leilao leilao) {

        OptionalDouble media = leilao.getLances().stream().mapToDouble(Lance::getValor).average();
        mediaDeTodos = media.isPresent() ? media.getAsDouble(): 0;

        OptionalDouble maximo = leilao.getLances().stream().mapToDouble(Lance::getValor).max();
        maiorDeTodos = maximo.isPresent() ? maximo.getAsDouble(): 0;

        OptionalDouble menor = leilao.getLances().stream().mapToDouble(Lance::getValor).min();
        menorDeTodos = menor.isPresent() ? menor.getAsDouble(): 0;

        pegaOsMaioresNo(leilao);
    }

    private void pegaOsMaioresNo(Leilao leilao) {
        maiores = new ArrayList<Lance>(leilao.getLances());
        Collections.sort(maiores, new Comparator<Lance>() {
            public int compare(Lance o1, Lance o2) {
                if(o1.getValor() < o2.getValor()) return 1;
                if(o1.getValor() > o2.getValor()) return -1;
                return 0;
            }
        });
        maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
    }

    public List<Lance> getTresMaiores() {
        return this.maiores;
    }
    public double getMaiorLance() { return maiorDeTodos; }
    public double getMenorLance() { return menorDeTodos; }
    public double getMediaDeTodos() {
        return mediaDeTodos;
    }
}
