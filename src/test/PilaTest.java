package test;

import jamesBond.Carta;
import jamesBond.Pila;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PilaTest {
  Pila pila;
    
  @Before
  public void setUp() throws Exception {
    this.pila = new Pila(4);
    Carta carta1 = new Carta('P', 1);
    Carta carta2 = new Carta('D', 1);
    Carta carta3 = new Carta('T', 1);
    this.pila.agregarCarta(carta1);
    this.pila.agregarCarta(carta2);
    this.pila.agregarCarta(carta3);
  }
	
  @Test
  public void cartasIguales() {
    Assert.assertEquals(pila.cartasIguales(), false);
    Carta carta = new Carta('C', 1);
    this.pila.agregarCarta(carta);
    Carta ultima = this.pila.getCarta(3);
    Assert.assertEquals(ultima.toString(), "A de C");
    Assert.assertEquals(pila.cartasIguales(), true);
    carta = new Carta('C', 2);
    Carta eliminada = pila.cambiarCarta(0, carta);
    Assert.assertEquals(eliminada.toString(), "A de P");
    
    
  }

  @Test
  public void cartasDiferentes() {
    Carta carta = new Carta('C', 2);
    this.pila.agregarCarta(carta);
    Assert.assertEquals(pila.cartasIguales(), false); 
  }
    
}