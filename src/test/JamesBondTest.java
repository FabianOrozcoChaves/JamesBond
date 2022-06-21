package test;

import jamesBond.JamesBond;
import jamesBond.Jugador;
import jamesBond.Tablero;
import jamesBond.Pila;
import jamesBond.Carta;

import org.junit.Assert;
import org.junit.Test;

public class JamesBondTest {
  
  @Test
  public void repartirCartas() {
    JamesBond controlador = new JamesBond();
    controlador.inicializarTurnos("Luis", "Juan"
        , "Luis");
    controlador.repartirCartas();
    Assert.assertEquals(0, controlador.getCartasRestantesMazo());
  }

  @Test
  public void intercambiarCarta() {
    JamesBond controlador = new JamesBond();
    controlador.inicializarTurnos("Luis", "Juan"
        , "Luis");
    Pila pila = iniciarJugador(controlador.getJugador(1));
    iniciarTablero(controlador.getTablero());
    controlador.getJugador(1).cambiarPila(5);
    Carta cartaJugadorAntes = pila.getCarta(1);
    Carta cartaTableroAntes = controlador.getTablero().getCarta(2);
    controlador.intercambiarCarta(2, 1);
    Carta cartaJugadorDespues = pila.getCarta(1);
    Carta cartaTableroDespues = controlador.getTablero().getCarta(2);
    Assert.assertEquals(cartaJugadorAntes, cartaTableroDespues);
    Assert.assertEquals(cartaTableroAntes, cartaJugadorDespues);
  }

  private Pila iniciarJugador(Jugador jugador) {
    Pila aux = new Pila(4);
    for(int i = 0; i < jugador.getMaxPilas(); i++){
      Pila pila =  new Pila(4);
      iniciarPila(pila, i);
      jugador.agregarPila(pila);
      aux = pila;
    }
    return aux;
  }

  private void iniciarPila(Pila pila, int numero){
    char[] palos = {'P', 'D', 'C', 'T'};
    for (int i = 0; i < palos.length; i++) {
      pila.agregarCarta(new Carta(palos[i], numero+i, "path"));
    }
  }

  private void iniciarTablero(Tablero tablero) {
    char[] palos = {'P', 'D', 'C', 'T'};
    for (int i = 0; i < palos.length; i++) {
      tablero.agregarCarta(new Carta(palos[i], (int)Math.random()*10+1, "path"));
    }
  }
}
