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
    Carta carta = new Carta(13, 'P');
    jugador.intercambiarCarta(carta, 0);
    Assert.assertEquals(jugador.pilasIguales(), false);
  }

  private void iniciarPilaIguales(Pila pila, int numero){
    char[] palos = {'P', 'D', 'C', 'T'};
    for (int i = 0; i < palos.length; i++) {
      pila.agregarCarta(new Carta(numero, palos[i]));
    }
  }

  private void iniciarPilaDiferente(Pila pila, int numero){
    char[] palos = {'P', 'D', 'C', 'T'};
    for (int i = 0; i < palos.length; i++) {
      pila.agregarCarta(new Carta(numero+i, palos[i]));
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