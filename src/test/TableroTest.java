/***
package test;

import jamesBond.Tablero;
import jamesBond.Carta;

import org.junit.Assert;
import org.junit.Test;

public class TableroTest {
  Tablero tablero;
	
  @Test
  public void testClase() {
    iniciarTablero();
    this.tablero = Tablero.getInstance();
    Carta carta = tablero.getCarta(0);
    Assert.assertEquals(carta.getNumero(), 1);
    Assert.assertEquals(carta.getPalo(), 'P');
    carta = tablero.eliminarCarta(3);
    Assert.assertEquals(carta.getNumero(), 1);
    Assert.assertEquals(carta.getPalo(), 'C');
    carta = new Carta('D', 2, "../img/back.png");
    tablero.agregarCarta(carta);
    carta = tablero.getCarta(3);
    Assert.assertEquals(carta.getNumero(), 2);
    Assert.assertEquals(carta.getPalo(), 'D');
  }

  private void iniciarTablero(){
    this.tablero = Tablero.getInstance();
    Carta carta1 = new Carta('P', 1, "../img/back.png");
    Carta carta2 = new Carta('D', 1, "../img/back.png");
    Carta carta3 = new Carta('T', 1, "../img/back.png");
    Carta carta4 = new Carta('C', 1, "../img/back.png");
    tablero.agregarCarta(carta1);
    tablero.agregarCarta(carta2);
    tablero.agregarCarta(carta3);
    tablero.agregarCarta(carta4);
  }
}
***/