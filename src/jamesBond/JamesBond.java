package jamesBond;

/**
 * Clase JamesBond.
 * Clase controladora
 */
public class JamesBond {
  private Jugador jugador1;
  private Jugador jugador2;
  private Tablero tablero;
  private Mazo mazo;
  private Jugador turnoActual;
  private int temporizador;

  public JamesBond() {
    tablero = Tablero.getInstance();
    mazo = Mazo.getInstance();
    temporizador = 0;
  }

  public void repartirCartas() {
    mazo.generaCartas();
    mazo.barajas();

    for (int i = 0; i < 4; i++) {
      Pila pilaJugador1 = new Pila(6);
      Pila pilaJugador2 = new Pila(6);
      for (int j = 0; j < 6; j++) {
        pilaJugador1.agregarCarta(mazo.getCarta());
        pilaJugador2.agregarCarta(mazo.getCarta());
      }
      jugador1.agregarPila(pilaJugador1);
      jugador2.agregarPila(pilaJugador2);
      tablero.agregarCarta(mazo.getCarta());
    }
  }

  public void intercambiarCarta(int posTablero, int posJugador) {
    //Carta cartaJugador = tablero.eliminarCarta(posTablero);
    Carta cartaJugador = tablero.getCarta(posTablero);
    Carta cartaTablero;

    if (turnoActual.equals(jugador1))
      cartaTablero = jugador1.intercambiarCarta(cartaJugador, posJugador);
    else
      cartaTablero = jugador2.intercambiarCarta(cartaJugador, posJugador);

    tablero.cambiarCarta(posTablero, cartaTablero);
  }

  public void inicializarTurnos(String nombreJugador1, String nombreJugador2, String turnoInicial) {
    jugador1 = new Jugador(nombreJugador1);
    jugador2 = new Jugador(nombreJugador2);

    if (turnoInicial.equals(nombreJugador1))
      turnoActual = jugador1;
    else if (turnoInicial.equals(nombreJugador2))
      turnoActual = jugador2;
    else
      randomizarTurno();
  }

  public void randomizarTurno() {
    int turnoAleatorio = (int) Math.random() * 2;
    if (turnoAleatorio == 0) {
      turnoActual = jugador1;
    } else {
      turnoActual = jugador2;
    }
  }

  public void cambiarTurno() {
    if (turnoActual.equals(jugador1))
      turnoActual = jugador2;
    else
      turnoActual = jugador1;
  }

  public boolean revisarPilas(Jugador jugador) {
    return jugador.pilasIguales();
  }

  public void setTemporizador(int tiempo) {
    temporizador = tiempo;
  }

  public int getTemporizador() {
    return temporizador;
  }

  public int getCartasRestantesMazo() {
    return mazo.getCartasRestantes();
  }

  public Jugador getJugador(int numJugador) {
    return numJugador == 1 ? jugador1 : jugador2;
  }

  public Tablero getTablero() {
    return tablero;
  }
}
