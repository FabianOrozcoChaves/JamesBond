package test;

import jamesBond.JamesBond;
import jamesBond.Jugador;
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
    controlador.repartirCartasSinBarajar();
    Assert.assertEquals(0, controlador.getCartasRestantesMazo());
  }

  @Test
  public void intercambiarCarta() {
    JamesBond controlador = new JamesBond();
    controlador.inicializarTurnos("Luis", "Juan"
        , "Luis");
    controlador.repartirCartasSinBarajar();
    Jugador jugador1 = controlador.getJugador(1);
    controlador.getJugador(1).cambiarPila(5);
    Pila pila = jugador1.pilaActiva();
    Carta cartaJugadorAntes = pila.getCarta(1);
    Carta cartaTableroAntes = controlador.getTablero().getCarta(2);
    controlador.intercambiarCarta(2, 1);
    Carta cartaJugadorDespues = pila.getCarta(1);
    Carta cartaTableroDespues = controlador.getTablero().getCarta(2);
    Assert.assertEquals(cartaJugadorAntes, cartaTableroDespues);
    Assert.assertEquals(cartaTableroAntes, cartaJugadorDespues);
  }
}
