package jamesBond;

import java.util.Vector;

import marda.Carta;
import marda.GrupoDeCartasMarda;
import marda.JugadorMarda;

/**
 * Clase Jugador.
 * Representa una persona que juega James Bond.
 * Puede realizar acciones válidas expuestas en el diseño del juego.
 */
public class Jugador extends JugadorMarda {
  private int pilaActiva;  // indica la posición en el vector de la pila que se encuentra activa (la que el jugador posee en mano)

  /**
   * @brief Constructor. Inicializa atributos de la clase
   * @param nombre string que representa el nombre del jugador
   * @param maxPilas cantidad de pilas que puede poseer un jugador.
   */
  public Jugador(final String nombre, final int maxPilas) {
    super();
    super.nombre = nombre;
    this.pilaActiva = 0;
  }

  /**
   * @brief Constructor por omision. Inicializa atributos de la clase
   */
  public Jugador() {
    this("Player");
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
   * Metodo de visualizacion
   * @return pila que representa la que el jugador tiene en mano actualmente.
   */
  public GrupoDeCartasMarda getPilaActiva(){
    return this.getGrupoDeCartas(this.pilaActiva);
  }

  /**
   * Método que intercambia una carta comun (que está en la mesa), con una de la pila activa del jugador.
   * @param cartaEntrante carta que será introducida en la pila actual del jugador. Viene desde la mesa.
   * @param posicionSaliente posicion de la carta que será sacada de la pila actual del jugador para ponerla en las cartas comunes (en la mesa)
   * @return Carta que representa la carta que fue sacada de la mesa
   */
  public Carta intercambiarCarta(Carta cartaEntrante, int posicionSaliente) {
    return getPilaActiva().cambiarCarta(posicionSaliente, cartaEntrante);
  }

  /**
   * @brief Método que recorre todas las pilas del jugador y retorna si son iguales.
   * @detail invoca al método de la clase Pila para verificar la igualdad del número en la carta.
   * @return boolean que representa si las pilas del jugador son iguales o no.
   */
  public boolean pilasIguales() {
    boolean pilasIguales = true;
    for (int pila = 0; pila < super.size() && pilasIguales; pila++) {
      GrupoDeCartasMarda pilaActual = super.getGrupoDeCartas(pila);
      for (int carta = 0; carta < pilaActual.getCartas().size() && pilasIguales; ++carta) {
        pilasIguales = pilaActual.getCarta(0).getNumero() == pilaActual.getCarta(carta).getNumero();
      }
    }
    return pilasIguales;
  }

  /**
   * Método que agrega una Pila al vector de pilas del jugador
   * @param pila que representa la pila que se quiere agregar a las pilas del jugador
   */
  public void agregarPila(GrupoDeCartasMarda pila){
    super.agregarGrupoDeCartas(pila);
  }

  /**
   * Método get que accede a las pilas del jugador según su índice (para el controlador)
   * @param posicion int que representa la posición de la pila en el arreglo.
   * @return Pila que se quiere acceder.
   */
  public GrupoDeCartasMarda getPila(int posicion){
    return super.getGrupoDeCartas(posicion);
  }

  /**
   * Método get que devuelve la posicionde la pila activa
   * @return int que representa la posicion de la pila activa
   */
  public int getIndexPilaActiva() {
    return this.pilaActiva;
  }
}
