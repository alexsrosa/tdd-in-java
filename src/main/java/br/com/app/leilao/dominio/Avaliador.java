package br.com.app.leilao.dominio;

import java.util.OptionalDouble;

public class Avaliador {

    private double maiorDeTodos = Double.NEGATIVE_INFINITY;
    private double menorDeTodos = Double.POSITIVE_INFINITY;
    private double mediaDeTodos = Double.POSITIVE_INFINITY;

    public void avalia(Leilao leilao) {

        OptionalDouble media = leilao.getLances().stream().mapToDouble(Lance::getValor).average();
        mediaDeTodos = media.isPresent() ? media.getAsDouble(): 0;

        OptionalDouble maximo = leilao.getLances().stream().mapToDouble(Lance::getValor).max();
        maiorDeTodos = maximo.isPresent() ? maximo.getAsDouble(): 0;

        OptionalDouble menor = leilao.getLances().stream().mapToDouble(Lance::getValor).min();
        menorDeTodos = menor.isPresent() ? menor.getAsDouble(): 0;
    }

    public double getMaiorLance() { return maiorDeTodos; }
    public double getMenorLance() { return menorDeTodos; }

    public double getMediaDeTodos() {
        return mediaDeTodos;
    }
}
