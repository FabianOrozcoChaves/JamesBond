import jamesBond.Mazo;
import jamesBond.Carta;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MazoTest {
  Mazo mazo;

  @Before
  public void setUp() throws Exception {
    math = getInstance();
    generaCartas();
  }
	
  @Test
  public void testGenerar() {
    Assert.assertEquals(52, mazo.getCartasRestantes());
    Carta carta = mazo.getCarta();
    Assert.assertEquals(1, carta.getNumero());
    Assert.assertEquals('P', carta.getPalo());
    Assert.assertEquals(carta.toString(), "A de P");
    Assert.assertEquals(51, mazo.getCartasRestantes());
  }
}