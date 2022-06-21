package test;

import jamesBond.Carta;
import jamesBond.Jugador;
import jamesBond.Pila;

import org.junit.Assert;
import org.junit.Test;

public class JugadorTest {
	
  @Test
  public void pilasIguales() {
    Jugador jugador = new Jugador();
    inciarJugador(jugador, true);
    Assert.assertEquals(jugador.pilasIguales(), true); 
  }

  @Test
  public void pilasDiferentes() {
    Jugador jugador = new Jugador();
    inciarJugador(jugador, false);
    Assert.assertEquals(jugador.pilasIguales(), false);
  }

  @Test
  public void intercambiarCarta() {
    Jugador jugador = new Jugador();
    inciarJugador(jugador, true);
    Assert.assertEquals(jugador.pilasIguales(), true);
    Carta carta = new Carta('P', 13, "path");
    jugador.intercambiarCarta(carta, 0);
    Assert.assertEquals(jugador.pilasIguales(), false);
  }

  private void iniciarPilaIguales(Pila pila, int numero){
    char[] palos = {'P', 'D', 'C', 'T'};
    for (int i = 0; i < palos.length; i++) {
      pila.agregarCarta(new Carta(palos[i], numero, "path"));
    }
  }

  private void iniciarPilaDiferente(Pila pila, int numero){
    char[] palos = {'P', 'D', 'C', 'T'};
    for (int i = 0; i < palos.length; i++) {
      pila.agregarCarta(new Carta(palos[i], numero+i, "path"));
    }
  }

  private void inciarJugador(Jugador jugador, boolean pilasIguales){
    for(int i = 0; i < jugador.getMaxPilas(); i++){
      Pila pila =  new Pila(4);
      if(pilasIguales){
        iniciarPilaIguales(pila, i);
      } else {
        iniciarPilaDiferente(pila, i);
      }
      jugador.agregarPila(pila);
    }
    
  }
}