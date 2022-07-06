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
    carta = new Carta('C', 1);
    tablero.agregarCarta(carta);
    carta = tablero.getCarta(3);
    Assert.assertEquals(carta.getNumero(), 1);
    Assert.assertEquals(carta.getPalo(), 'C');
    carta = new Carta('D', 2);
    tablero.cambiarCarta(3, carta);
    carta = tablero.getCarta(3);
    Assert.assertEquals(carta.getNumero(), 2);
    Assert.assertEquals(carta.getPalo(), 'D');
  }

  private void iniciarTablero(){
    this.tablero = Tablero.getInstance();
    Carta carta1 = new Carta('P', 1);
    Carta carta2 = new Carta('D', 1);
    Carta carta3 = new Carta('T', 1);
    tablero.agregarCarta(carta1);
    tablero.agregarCarta(carta2);
    tablero.agregarCarta(carta3);
  }
}