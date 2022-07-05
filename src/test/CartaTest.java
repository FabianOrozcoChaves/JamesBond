package test;

import jamesBond.Carta;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CartaTest {
  Carta carta1;

  @Before
  public void setUp() throws Exception {
    carta1 = new Carta('P', 1);
  }
	
  @Test
  public void constructores() {
    Carta carta2 = new Carta('C',2);
    Assert.assertEquals(carta2.getNumero(), 2);
    Assert.assertEquals(carta2.getPalo(), 'C');
    Assert.assertEquals(carta1.getNumero(), 1);
    Assert.assertEquals(carta1.getPalo(), 'P');
  }

  @Test
  public void toStringYCartasEspeciales(){
    Carta carta2 = new Carta('D', 13);
    Carta carta3 = new Carta('C', 9);
    Assert.assertEquals(carta1.toString(), "A de P");
    Assert.assertEquals(carta2.toString(), "K de D");
    Assert.assertEquals(carta3.toString(), "9 de C");
  }
}