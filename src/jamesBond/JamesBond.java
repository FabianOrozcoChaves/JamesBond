package jamesBond;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;

import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.Json;

/**
 * Clase JamesBond.
 * Clase controladora
 * Encargada de control de las jugadas, turnos y determinar ganadores.
 */
public class JamesBond {
  private Jugador jugador1;
  private Jugador jugador2;
  private Tablero tablero;
  private Mazo mazo;
  private Jugador turnoActual;
  private int temporizador;

  /**
   * @brief Constructor. Inicializa atributos de la clase
   */
  public JamesBond() {
    tablero = Tablero.getInstance();
    mazo = Mazo.getInstance();
    temporizador = 0;
  }

  /**
   * @brief Genera las cartas del mazo, las baraja y luego las reparte.
   */
  public void repartirCartas() {
    mazo.generaCartas();
    mazo.barajar();
    repartir();
  }

  /**
   * @brief Reparte las cartas del mazo, 24 a cada jugador y 4 al tablero.
   */
  // TODO: agregar al UML
  private void repartir(){
    for (int i = 0; i < 6; i++) {
      Pila pilaJugador1 = new Pila(4);
      Pila pilaJugador2 = new Pila(4);
      for (int j = 0; j < 4; j++) {
        pilaJugador1.agregarCarta(mazo.getCarta());
        pilaJugador2.agregarCarta(mazo.getCarta());
      }
      jugador1.agregarPila(pilaJugador1);
      jugador2.agregarPila(pilaJugador2);
    }
    for(int i = 0; i < 4; i++){
      tablero.agregarCarta(mazo.getCarta());
    }
  }

  /**
   * @brief Genera las cartas del mazo y las reparte.
   */
  // TODO: agregar al UML
  public void repartirCartasSinBarajar(){
    mazo.generaCartasSinFX();
    repartir();
  }

   /**
   * @brief Método que intercambia las cartas entre el tablero y el jugador
   * @param posTablero la posicion de la carta en el tablero 
   * @param posJugador La posicion de la carta del jugador
   */
  public void intercambiarCarta(int posTablero, int posJugador) {
    Carta cartaJugador = tablero.getCarta(posTablero);
    Carta cartaTablero;

    if (turnoActual.equals(jugador1))
      cartaTablero = jugador1.intercambiarCarta(cartaJugador, posJugador);
    else
      cartaTablero = jugador2.intercambiarCarta(cartaJugador, posJugador);

    tablero.cambiarCarta(posTablero, cartaTablero);
  }

   /**
   * @brief Método encargado de inicializar los turnos y asignar los nombres a los jugadores
   * @param nombreJugador1 El nombre para el jugador 1 
   * @param nombreJugador2 El nombre para el jugador 2
   * @param turnoInicial El nombre del jugador que tendrá el turno inicial
   */
  public void inicializarTurnos(String nombreJugador1, String nombreJugador2, String turnoInicial) {
    jugador1 = new Jugador(nombreJugador1);
    jugador2 = new Jugador(nombreJugador2);
    tablero.quitarCartas();

    if (turnoInicial.equals(nombreJugador1))
      turnoActual = jugador1;
    else if (turnoInicial.equals(nombreJugador2))
      turnoActual = jugador2;
    else
      randomizarTurno();
  }

  /**
   * @brief Método que elige el primer turno al azar
   */
  public void randomizarTurno() {
    int turnoAleatorio = (int) (Math.random() * 2);
    if (turnoAleatorio == 0) {
      turnoActual = jugador1;
    } else {
      turnoActual = jugador2;
    }
  }

  /**
   * @brief Método que cambia los turnos entre los jugadores
   */
  public void cambiarTurno() {
    if (turnoActual.equals(jugador1))
      turnoActual = jugador2;
    else
      turnoActual = jugador1;
  }

  /**
   * @brief Método que revisa las pilas de un jugador
   * @return boolean true si las cartas del jugador son iguales, false en caso contrario
   */
  public boolean revisarPilas(Jugador jugador) {
    return jugador.pilasIguales();
  }

  /**
   * @brief Método que asigna el tiempo de duración de cada turno
   */
  public void setTemporizador(int tiempo) {
    temporizador = tiempo;
  }

  /**
   * @brief Método que devuelve el tiempo de duración de cada turno
   * @return int del tiempo en segundos
   */
  public int getTemporizador() {
    return temporizador;
  }

  /**
   * @brief Método que devuelve la cantidad de cartas restantes en el mazo
   * @return int cartas restantes en el mazo
   */
  public int getCartasRestantesMazo() {
    return mazo.getCartasRestantes();
  }

   /**
   * @brief Método que devuelve un jugador
   * @param numJugador el numero de jugador
   * @return objeto Jugador del jugador solicitado
   */
  public Jugador getJugador(int numJugador) {
    return numJugador == 1 ? jugador1 : jugador2;
  }

  /**
   * @brief Método que devuelve el tablero
   * @return objeto Tablero del tablero creado para la partida
   */
  public Tablero getTablero() {
    return tablero;
  }

  /**
   * @brief Método que devuelve el jugador el cual tiene el turno actual
   * @return objeto Jugador del jugador con el turno actual
   */
  public Jugador getTurnoActual() {
    return this.turnoActual;
  }

  /**
   * Método que guarda el estado del juego en un archivo.
   * @param constructor Constructor que serializa (guarda) el jamesBond.
   */
  public void guardar(ConstructorSerializadorAbstracto constructor) {
    constructor.serializarJamesBond(this);
    constructor.guardarSerializacion();
  }

  /**
   * @brief Método que carga el estado del juego desde un archivo guardado anteriormente.
   * @param constructor Constructor que deserializa (carga) el jamesBond por referencia.
   */
  public void cargar(ConstructorDeserializadorAbstracto constructor) {
    // Cambia la instancia por referencia
    constructor.deserializarJamesBond(this);
  }
}
