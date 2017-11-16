package br.com.app.leilao.dominio;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

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

    @Test
    public void deveTerSomenteUmLance(){

        Usuario joao = new Usuario("João");

        leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao,300.0));

        // executando a acao
        leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        maiorEsperado = 300.0;
        menorEsperado = 300.0;
        mediaEsperada = 300.0;

        Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
        Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
        Assert.assertEquals(mediaEsperada, leiloeiro.getMediaDeTodos(), 0.0001);

    }

    @Test
    public void recebeLancesDeFormaRodomica(){

        Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");
        Usuario jose = new Usuario("Jose");

        leilao = new Leilao("Playstation 4 Novo");

        leilao.propoe(new Lance(joao,200));
        leilao.propoe(new Lance(maria,450));
        leilao.propoe(new Lance(joao,120));
        leilao.propoe(new Lance(jose,700));
        leilao.propoe(new Lance(joao,630));
        leilao.propoe(new Lance(maria,230));

        // executando a acao
        leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        maiorEsperado = 700;
        menorEsperado = 120;
        mediaEsperada = 388.333333333333333;

        Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
        Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
        Assert.assertEquals(mediaEsperada, leiloeiro.getMediaDeTodos(), 0.0001);
    }

    @Test
    public void recebeLancesDeFormaDecrescente(){

        Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");

        leilao = new Leilao("Playstation 4 Novo");

        leilao.propoe(new Lance(joao,400));
        leilao.propoe(new Lance(maria,300));
        leilao.propoe(new Lance(joao,200));
        leilao.propoe(new Lance(joao,100));

        // executando a acao
        leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        maiorEsperado = 400;
        menorEsperado = 100;
        mediaEsperada = 250;

        Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
        Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
        Assert.assertEquals(mediaEsperada, leiloeiro.getMediaDeTodos(), 0.0001);
    }

    @Test
    public void leilaoComCincoLancesEncontraTresMaiores(){

        Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");

        leilao = new Leilao("Playstation 4 Novo");

        leilao.propoe(new Lance(joao,400));
        leilao.propoe(new Lance(maria,300));
        leilao.propoe(new Lance(joao,200));
        leilao.propoe(new Lance(joao,100));
        leilao.propoe(new Lance(maria,500));

        // executando a acao
        leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> tresMaiores = leiloeiro.getTresMaiores();

        Assert.assertEquals(3,tresMaiores.size());
    }

    @Test
    public void leilaoComDoisLancesEncontraTresMaiores(){

        Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");

        leilao = new Leilao("Playstation 4 Novo");

        leilao.propoe(new Lance(joao,400));
        leilao.propoe(new Lance(maria,300));

        // executando a acao
        leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> tresMaiores = leiloeiro.getTresMaiores();

        Assert.assertEquals(2,tresMaiores.size());
    }

    @Test
    public void leilaoComZeroLancesEncontraTresMaiores(){

        leilao = new Leilao("Playstation 4 Novo");

        // executando a acao
        leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> tresMaiores = leiloeiro.getTresMaiores();

        Assert.assertEquals(0,tresMaiores.size());
    }
}
