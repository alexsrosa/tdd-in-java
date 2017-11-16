package br.com.app.matematica;

import org.junit.Assert;
import org.junit.Test;

public class MatematicaMalucaTest {
    
    @Test
    public void maiorQueTrinta(){
        
        MatematicaMaluca maluca = new MatematicaMaluca();

        int i = maluca.contaMaluca(31);

        Assert.assertEquals(i,124);
    }

    @Test
    public void maiorQueDez(){

        MatematicaMaluca maluca = new MatematicaMaluca();

        int i = maluca.contaMaluca(11);

        Assert.assertEquals(i,33);
    }

    @Test
    public void MenorQueDez(){

        MatematicaMaluca maluca = new MatematicaMaluca();

        int i = maluca.contaMaluca(9);

        Assert.assertEquals(i,18);
    }
}
