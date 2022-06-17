package jamesBond;
import jamesBond.Pila;
import jamesBond.Carta;
import java.util.Vector;

/**
 * Clase Jugador.
 * Representa una persona que juega James Bond.
 * Puede realizar acciones válidas expuestas en el diseño del juego.
 */
public class Jugador {
  private String nombre;
  private int MAX_PILAS;
  private Vector<Pila> pilas;
  private int pilaActiva;

  /**
   * @brief Constructor. Inicializa atributos de la clase
   * @param nombre string que representa el nombre del jugador
   * @param maxPilas cantidad de pilas que puede poseer un jugador.
   */
  Jugador(final String nombre, final int maxPilas) {
    init(nombre, maxPilas);
  }

  /**
   * @brief Constructor por omision. Inicializa atributos de la clase
   */
  Jugador() {
    init("Player", 6);
  }

  /**
   * @brief Constructor por omision. Inicializa atributos de la clase
   */
  Jugador(String nombre) {
    init(nombre, 6);
  }

  /**
   * @brief Inicializa atributos de la clase. Modulariza constructores.
   * @param nombre string que representa el nombre del jugador
   * @param maxPilas cantidad de pilas que puede poseer un jugador.
   */
  private void init(String nombre, int maxPilas) {
    this.nombre = nombre;
    this.MAX_PILAS = maxPilas;
    this.pilas = new Vector<Pila>();
    this.pilaActiva = 0;
  }

  /**
   * @brief Método que setea un nuevo valor a la pila activa.
   * @param index int que representa el nuevo valor a asignar.
   */
  private void cambiarPila(int index) {
    this.pilaActiva = index;
  }

  /**
   * @brief Método que muestra (retorna al GUI) la pila activa.
   */
  private Pila mostrarPila() {
    // Verifica si la pila no está vacía.
    return this.pilas.elementAt(this.pilaActiva).isEmpty() == false ? 
    this.pilas.elementAt(this.pilaActiva) : null;
  }

  /**
   * Metodo de visualizacion
   * @return pila que representa la que el jugador tiene en mano actualmente.
   */
  private Pila pilaActiva(){
    return this.pilas.elementAt(this.pilaActiva);
  }

  /**
   * @brief Método que escode (retorna al GUI) la pila activa.
   */
  private Pila esconderPila() {
    // Verifica si la pila no está vacía.
    return this.pilaActiva().isEmpty() ? null : pilaActiva();
  }

  /**
   * Método que intercambia una carta comun (que está en la mesa), con una de la pila activa del jugador.
   * @param cartaEntrante carta que será introducida en la pila actual del jugador. Viene desde la mesa.
   * @param posicionSaliente posicion de la carta que será sacada de la pila actual del jugador para ponerla en las cartas comunes (en la mesa)
   * @return Carta que representa la carta que fue sacada de la mesa
   */
  public Carta intercambiarCarta(Carta cartaEntrante, int posicionSaliente) {
    // TODO agregar método getCarta a UML de Pila.
    Carta cartaSaliente = pilaActiva().getCarta(posicionSaliente);
    pilaActiva().eliminarCarta(posicionSaliente);
    pilaActiva().agregarCarta(cartaEntrante);
    return cartaSaliente;
  }

  /**
   * @brief Método que recorre todas las pilas del jugador y retorna si son iguales.
   * @detail invoca al método de la clase Pila para verificar la igualdad del número en la carta.
   * @return boolean que representa si las pilas del jugador son iguales o no.
   */
  private boolean pilasIguales() {
    boolean pilasIguales = true;
    for (int pila = 0; pila < this.pilas.size() && pilasIguales; pila++) {
      // TODO cambiar nombre de método UML de pila > todasIguales a cartas iguales.
      pilasIguales = pila.cartasIguales();
    }
    return pilasIguales;
  }

  public static void main(String[] args) {
    System.out.println("crea jugador");
  }
}