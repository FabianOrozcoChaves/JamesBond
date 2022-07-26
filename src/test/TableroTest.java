package test;

import jamesBond.Tablero;
import marda.Carta;

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
    carta = new Carta(1, 'C');
    tablero.agregarCarta(carta);
    carta = tablero.getCarta(3);
    Assert.assertEquals(carta.getNumero(), 1);
    Assert.assertEquals(carta.getPalo(), 'C');
    carta = new Carta(2, 'D');
    tablero.cambiarCarta(3, carta);
    carta = tablero.getCarta(3);
    Assert.assertEquals(carta.getNumero(), 2);
    Assert.assertEquals(carta.getPalo(), 'D');
  }

  private void iniciarTablero(){
    this.tablero = Tablero.getInstance();
    tablero.quitarCartas();
    Carta carta1 = new Carta(1, 'P');
    Carta carta2 = new Carta(1, 'D');
    Carta carta3 = new Carta(1, 'T');
    tablero.agregarCarta(carta1);
    tablero.agregarCarta(carta2);
    tablero.agregarCarta(carta3);
  }
}