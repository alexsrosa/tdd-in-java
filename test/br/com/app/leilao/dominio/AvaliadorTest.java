package br.com.app.leilao.dominio;

import org.junit.Assert;
import org.junit.Test;

public class AvaliadorTest {

    private Leilao leilao;
    private Avaliador leiloeiro;
    private double maiorEsperado;
    private double menorEsperado;
    private double mediaEsperada;

    private void inicia(){
        // cenario: 3 lances em ordem crescente
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(maria,250.0));
        leilao.propoe(new Lance(joao,300.0));
        leilao.propoe(new Lance(jose,400.0));

        // executando a acao
        leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        // comparando a saida com o esperado
        maiorEsperado = 400;
        menorEsperado = 250;
        mediaEsperada = 316.666666666666667;
    }

    @Test
    public void deveEntenderLancesEmOrdemCrescente() {

        this.inicia();

        Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
        Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
    }

    @Test
    public void deveTerUmaMediaValida(){

        this.inicia();

        Assert.assertEquals(mediaEsperada, leiloeiro.getMediaDeTodos(), 0.0001);

    }
}
