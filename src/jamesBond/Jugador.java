package jamesBond;

import java.util.Vector;

/**
 * Clase Jugador.
 * Representa una persona que juega James Bond.
 * Puede realizar acciones válidas expuestas en el diseño del juego.
 */
public class Jugador {
  private String nombre;
  private int maxPilas;  // cantidad máxima de pilas por jugador (pensado para versiones futuras del juego)
  private Vector<Pila> pilas;
  private int pilaActiva;  // indica la posición en el vector de la pila que se encuentra activa (la que el jugador posee en mano)

  /**
   * @brief Constructor. Inicializa atributos de la clase
   * @param nombre string que representa el nombre del jugador
   * @param maxPilas cantidad de pilas que puede poseer un jugador.
   */
  public Jugador(final String nombre, final int maxPilas) {
    this.nombre = nombre;
    this.maxPilas = maxPilas;
    this.pilas = new Vector<Pila>();
    this.pilaActiva = 0;
  }

  /**
   * @brief Constructor por omision. Inicializa atributos de la clase
   */
  public Jugador() {
    this("Player", 6);
  }

  /**
   * @brief Constructor por omision. Inicializa atributos de la clase
   */
  public Jugador(String nombre) {
    this(nombre, 6);
  }

  /**
   * @brief Método que setea un nuevo valor a la pila activa.
   * @param index int que representa el nuevo valor a asignar.
   */
  public void cambiarPila(int index) {
    this.pilaActiva = index;
  }

  /**
   * @brief Método que muestra (retorna al GUI) la pila activa.
   * @return null en caso de que las pila esté vacía, la Pila activa en caso contrario.
   */
  public Pila mostrarPila() {
    // Verifica si la pila no está vacía.
    return this.pilas.elementAt(this.pilaActiva).isEmpty() == false ? 
    this.pilas.elementAt(this.pilaActiva) : null;
  }

  /**
   * Metodo de visualizacion
   * @return pila que representa la que el jugador tiene en mano actualmente.
   */
  public Pila pilaActiva(){
    return this.pilas.elementAt(this.pilaActiva);
  }

  /**
   * @brief Método que esconde (retorna al GUI) la pila activa.
   * @return null en caso de que las pila esté vacía, la Pila activa en caso contrario.
   */
  public Pila esconderPila() {
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
    Carta cartaSaliente = pilaActiva().cambiarCarta(posicionSaliente, cartaEntrante);
    return cartaSaliente;
  }

  /**
   * @brief Método que recorre todas las pilas del jugador y retorna si son iguales.
   * @detail invoca al método de la clase Pila para verificar la igualdad del número en la carta.
   * @return boolean que representa si las pilas del jugador son iguales o no.
   */
  public boolean pilasIguales() {
    boolean pilasIguales = true;
    for (int pila = 0; pila < maxPilas && pilasIguales; pila++) {
      pilasIguales = pilas.elementAt(pila).cartasIguales();
    }
    return pilasIguales;
  }

  /**
   * Método get que devuelve el atributo de la clase
   * @return String que representa el nombre del jugador
   */
  public String getNombre(){
    return this.nombre;
  }

  /**
   * Método get que devuelve el atributo de la clase
   * @return int que representa la cantidad máxima de pilas que puede tener el jugador
   */
  public int getMaxPilas(){
    return this.maxPilas;
  }

  /**
   * Método que agrega una Pila al vector de pilas del jugador
   * @param pila que representa la pila que se quiere agregar a las pilas del jugador
   */
  public void agregarPila(Pila pila){
    this.pilas.add(pila);
  }

  /**
   * Método set que asigna el nombre al jugador.
   * @param nombre string que representa el nombre que se le asignará al jugador
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * Método get que accede a las pilas del jugador según su índice (para el controlador)
   * @param posicion int que representa la posición de la pila en el arreglo.
   * @return Pila que se quiere acceder.
   */
  public Pila getPila(int posicion){
    return pilas.get(posicion);
  }

  public int getIndexPilaActiva(){
    return this.pilaActiva;
  }
}
